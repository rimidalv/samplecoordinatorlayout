package com.anews.samplecoordinatorlayout.logger;

import android.util.Log;


public class LB {

    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;
    public static final int ASSERT = Log.ASSERT;
    public static final int DISABLE = -1;


    private int logLevel = VERBOSE;
    private boolean isMeasuring = true;
    private boolean isProcessing = true;
    private boolean isShowStart = true;
    private String logSymbol = "=";

    public int getLogLevel() {
        return logLevel;
    }

    public LB setLogLevel(int logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public LB setIsMeasuring(boolean value) {
        this.isMeasuring = value;
        return this;
    }

    public boolean isMeasuring() {
        return this.isMeasuring;
    }

    public LB setIsProcessing(boolean isCounting) {
        this.isProcessing = isCounting;
        return this;
    }

    public boolean isProcessing() {
        return this.isProcessing;
    }

    public LB setShowStart(boolean isShow) {
        this.isShowStart = isShow;
        return this;
    }

    public boolean isShowStart() {
        return this.isShowStart;
    }

    public LB setLogSymbol(String symbol) {
        this.logSymbol = symbol;
        return this;
    }

    public String getLogSymbol() {
        return this.logSymbol;
    }
}
