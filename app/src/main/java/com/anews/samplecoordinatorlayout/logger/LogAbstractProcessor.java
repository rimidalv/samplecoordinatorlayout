package com.anews.samplecoordinatorlayout.logger;

public abstract class LogAbstractProcessor {

    private String key;

    public LogAbstractProcessor(String name) {
        this.key = name;
    }


    public final String getKey() {
        return this.key;
    }

    /**
     * do some calculation during the logging
     */
    public abstract void run();

    /**
     * called at the end of logging, when you call stop() of Logger
     */
    public abstract String toString();

}
