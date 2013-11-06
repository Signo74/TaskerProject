package com.example.tasker.utils;

import android.support.v7.appcompat.R;

/**
 * Created by victorm on 11/6/13.
 */
public class Logger {
    private static Logger instance = null;
    private static String mode;

    private Logger(){
    }

    public Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }
    //TODO: create a logging method based on the mode.
}
