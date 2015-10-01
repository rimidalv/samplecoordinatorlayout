package com.anews.samplecoordinatorlayout.logger;

public class LogCounterProcessor extends LogAbstractProcessor {

    private int count = 0;

    public LogCounterProcessor(String name) {
        super(name);
    }

    @Override
    public void run() {
        count++;
    }

    @Override
    public String toString() {
        return String.format("%s %s processed", count, getKey());
    }

}
