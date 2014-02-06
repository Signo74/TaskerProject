package com.example.tasker;

/**
 * Created by victorm on 10/21/13.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasker.model.ExpandableListGroup;
import com.example.tasker.model.Task;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter
        extends BaseExpandableListAdapter {

    private final SparseArray<ExpandableListGroup> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public ExpandableListAdapter(Activity act, SparseArray<ExpandableListGroup> groups) {
        this.groups = groups;
        inflater = act.getLayoutInflater();
        activity = act;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildren().get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;

        if (convertView == null) {
           convertView = inflater.inflate(R.layout.list_item, null);
        }

        text = (TextView) convertView.findViewById(R.id.lblListHeader);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_group, null);
        }
        ExpandableListGroup group = (ExpandableListGroup) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.getHeader());
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
