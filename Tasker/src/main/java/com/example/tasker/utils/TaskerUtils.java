package com.example.tasker.utils;

import android.util.Log;

import com.example.tasker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by victorm on 11/6/13.
 */
public class TaskerUtils {

    public void TaskerUtils() {
    }

    public List<String> populateHeader(List<String> mListDataHeader, String headerItems[]) {
        if (headerItems == null || headerItems.length == 0) {
            return null;
        }
        try {
            String childItems[];
            if (mListDataHeader == null) {
                mListDataHeader = new ArrayList<String>();
            } else {
                mListDataHeader.clear();
            }

            for (int i = 0; i < headerItems.length; i++) {
                mListDataHeader.add(headerItems[i]);
            }
            return mListDataHeader;
        } catch (Exception e) {
            //TODO: print out the exception.
            return null;
        }
    }

    public HashMap<String, List<String>> populateChildren(HashMap<String, List<String>> mListDataChild, String index, String childItems[]) {
        //TODO: modify to convert the DB fields to the List<String> items required for the child items.
        if (childItems == null || childItems.length == 0) {
            Log.d("Child", "childItems = null");
            return null;
        }
        try {
            Log.d("Child", childItems.toString());
            Log.d("Child", String.valueOf(childItems.length));
            List<String> temp = null;
            if (mListDataChild == null) {
                mListDataChild = new HashMap<String, List<String>>();
            } else {
                mListDataChild.clear();
            }

            for (int i = 0; i < childItems.length; i++) {
                temp.add(childItems[i]);
            }
            mListDataChild.put(index, temp);
            Log.d("Child", mListDataChild.toString());

            return mListDataChild;
        } catch (Exception e) {
            //TODO: print out the exception.
            return null;
        }

    }
}
