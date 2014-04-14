package com.example.tasker.utils;

/**
 * Created by victorm on 4/14/2014.
 */
public class IdGeneratorFactory {
    private static IdGeneratorFactory ourInstance = new IdGeneratorFactory();

    public static IdGeneratorFactory getInstance() {
        return ourInstance;
    }

    private IdGeneratorFactory() {
    }
}
