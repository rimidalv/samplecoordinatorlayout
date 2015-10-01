package com.anews.samplecoordinatorlayout.logger;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class L {

    private static final String TAG = "anews.com";

    private long time = 0L;
    private Map<String, LogAbstractProcessor> processorsMap;
    private LB logBuilder;
    private int nestLevel;


    public enum LoggerType {
        ALL, DB, NETWORK, VIEW
    }


    public L() {
        this(getDefaultLogBuilder());
    }

    public L(LB logBuilder) {
        this.logBuilder = logBuilder;
        nestLevel = 0;
    }

    /**
     * set logBuilder
     *
     * @param logBuilder
     */
    public void setLogBuilder(LB logBuilder) {
        this.logBuilder = logBuilder;
    }

    private boolean isLogBuilderNotNull() {
        if (logBuilder == null)
            return false;
        return true;
    }

    /**
     * start logging, but before you must call setLogBuilder(LogBuilder)
     *
     * @param str
     */
    public void start(String str) {
        if (isLogBuilderNotNull())
            start(logBuilder, str, true);

    }

    public void start(String str, boolean showStart) {
        if (isLogBuilderNotNull())
            start(logBuilder, str, showStart);
    }

    /**
     * stop logging, but before you must call start(str) and
     * setLogBuilder(LogBuilder)
     *
     * @param str
     */
    public void end(String str) {
        if (isLogBuilderNotNull())
            end(logBuilder, str);

    }

    /**
     * start logging with logBuilder
     *
     * @param logBuilder
     * @param str
     */
    public void start(LB logBuilder, String str, boolean showStart) {
        nestLevel++;
        if (logBuilder.isMeasuring())
            startMeasure();
        if (logBuilder.isShowStart() && showStart) {
            String nestLevelStr = repeat(logBuilder.getLogSymbol(), nestLevel);
            log(logBuilder.getLogLevel(), String.format("%s [%s] started ============", nestLevelStr, str));
        }
    }

    /**
     * stop logging with logbuilder
     *
     * @param logBuilder
     * @param str
     */
    public void end(LB logBuilder, String str) {
        String s = null;
        String nestLevelStr = repeat(logBuilder.getLogSymbol(), nestLevel);
        if (logBuilder.isMeasuring()) {
            long count = endMeasure();
            s = String.format("%s [%s] ended in %s milliseconds ", nestLevelStr, str, count);
        } else
            s = String.format("%s [%s] ended", nestLevelStr, str);
        log(logBuilder.getLogLevel(), s);
        nestLevel--;
    }

    public static String repeat(String s, int times) {
        if (times <= 0)
            return "";
        else if (times % 2 == 0)
            return repeat(s + s, times / 2);
        else
            return s + repeat(s + s, times / 2);
    }

    /**
     * executing logging, call between start() and stop()
     *
     * @param alp
     */
    public void logExecute(LogAbstractProcessor alp) {
        logExecute(logBuilder, alp);
    }

    /**
     * executing logging with LogBuilder, call between start() and stop()
     *
     * @param logBuilder
     * @param alp
     */
    public void logExecute(LB logBuilder, LogAbstractProcessor alp) {
        if (logBuilder.isProcessing()) {
            if (processorsMap == null)
                processorsMap = new LinkedHashMap<String, LogAbstractProcessor>();
            if (!processorsMap.containsKey(alp.getKey())) {
                processorsMap.put(alp.getKey(), alp);
            }
            alp.run();
        }
        log(logBuilder.getLogLevel(), "logger execute");
    }

    public static LB getDefaultLogBuilder() {
        LB logBuilder = new LB();
        logBuilder.setLogLevel(LB.VERBOSE).setShowStart(false).setShowStart(true);
        return logBuilder;
    }

    private void startMeasure() {
        if (logBuilder != null)
            startMeasure(logBuilder);
    }

    private void startMeasure(LB logBuilder) {
        time = System.currentTimeMillis();
        // log(logBuilder.getLogLevel(), "====== starting measuring");
    }

    private long endMeasure() {
        if (logBuilder != null)
            return endMeasure(logBuilder);
        return -1;
    }

    private long endMeasure(LB logBuilder) {
        if (processorsMap != null) {
            Iterator<Entry<String, LogAbstractProcessor>> it = processorsMap.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, LogAbstractProcessor> pairs = (Entry<String, LogAbstractProcessor>) it.next();
                log(logBuilder.getLogLevel(), pairs.getValue().toString());
            }
        }
        long endTime = System.currentTimeMillis();
        long differ = endTime - time;
        return differ;
    }

    // ==================== static methods ========================

    public static void e(Throwable t) {
        if (t != null) {
            String msg = Log.getStackTraceString(t);
            print(Log.ERROR, msg);
        }
    }

    public static void w(String str) {
        print(Log.WARN, str);
    }

    public static void d(String str) {
        print(Log.DEBUG, str);
    }

    public static void i(String str) {
        print(Log.INFO, str);
    }

    private void log(int priority, String str) {
        if (priority != LB.DISABLE )
            Log.println(priority, getTag(true, false), str);
    }

    private static void print(int priority, String str) {
        if (priority != LB.DISABLE )
        Log.println(priority, getTag(true, true), str);
    }

    private static String getTag(boolean debug, boolean isStatic) {
        if (debug) {
            int index = 6;
            if (isStatic)
                index = 5;
            StackTraceElement caller = Thread.currentThread().getStackTrace()[index];
            String c = caller.getClassName();
            String className = c.substring(c.lastIndexOf(".") + 1, c.length());
            StringBuilder sb = new StringBuilder(5);
            sb.append(className);
            sb.append(".");
            sb.append(caller.getMethodName());
            sb.append("():");
            sb.append(caller.getLineNumber());
            return sb.toString();
        }
        return TAG;
    }

}
