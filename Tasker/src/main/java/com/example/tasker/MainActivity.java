package com.example.tasker;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tasker.controller.dao.TasksDAO;
import com.example.tasker.model.ExpandableListGroup;
import com.example.tasker.model.Task;
import com.example.tasker.utils.TaskerUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class MainActivity
        extends FragmentActivity {
    ExpandableListAdapter _ExpListAdapter;
    ExpandableListView _ExpListView;
    private String[] _DrawerItems;
    private ListView _DrawerList;
    private DrawerLayout _DrawerLayout;
    private ActionBarDrawerToggle _DrawerToggle;
    private CharSequence _DrawerTitle = "Drawer Title";
    private CharSequence _Title = "Title";
    private TaskerUtils utils = new TaskerUtils();
    private TasksDAO tasksDAO;
    private EditText editText;
    private SparseArray<ExpandableListGroup> groups = new SparseArray<ExpandableListGroup>();

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            tasksDAO = new TasksDAO(this);
            tasksDAO.open();
        } catch (SQLException ex) {
        }

        editText = (EditText) findViewById(R.id.etf_new_item);
        //Nav drawer
        _DrawerItems = getResources().getStringArray(R.array.tasksByDate);
        _DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        _DrawerList = (ListView) findViewById(R.id.left_drawer);
        _DrawerToggle = new ActionBarDrawerToggle(this, _DrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(_Title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(_DrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        _DrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, _DrawerItems));
        _DrawerList.setOnItemClickListener(new DrawerItemClickListener());
        _DrawerLayout.setDrawerListener(_DrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //Main list view
        populateListView();
        _ExpListAdapter = new ExpandableListAdapter(this, groups);
        _ExpListView.setAdapter(_ExpListAdapter);
    }

    private void populateListView() {
        _ExpListView = (ExpandableListView) findViewById(R.id.lvExp);
        List<Task> childItemTitles = tasksDAO.getAllTasks();
        Log.d("[> List with child items: ", childItemTitles.toString());
        for (String header : getResources().getStringArray(R.array.tasksByDate)) {
            Log.d("[> Adding group: ", header);
            ExpandableListGroup group = new ExpandableListGroup(header);
            group.setHeader(header);
            for (Task task : childItemTitles) {
                Log.d("[> Adding child: ", task.getTitle());
                if (task.getDueDate().toString() == group.getHeader()) {
                    group.getChildren().add(task.getTitle());
                }
            }
            groups.append(groups.size(), group);
        }
        Log.d("[> The sparse array of groups: ", groups.toString());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        _DrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        _DrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: should implement propper UP vs Back logic
        if (_DrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = _DrawerLayout.isDrawerOpen(_DrawerList);
        //menu.findItem(R.id.action_share).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        _ExpListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
        //TODO: implement proper swapping of fragments for main View.
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setTitle(CharSequence title) {
        _Title = title;
        getActionBar().setTitle(_Title);
    }

    public void addTask(View newItemButton){
        utils.quickAddTask(tasksDAO, String.valueOf(editText.getText()));
    }

    public void deleteTask(View newItemButton){
        utils.deleteAllTasks(tasksDAO);
    }

    public void getAllTasks(View newItemButton){
        List<Task> childItemTitles = utils.getAllTasks(tasksDAO);
    }


    public static class DrawerFragment extends Fragment {
        public static final String DRAWER_ITEM_NUMBER = "planet_number";

        public DrawerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
            int i = getArguments().getInt(DRAWER_ITEM_NUMBER);
            String planet = getResources().getStringArray(R.array.tasksByDate)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);
            return rootView;
        }
    }
}