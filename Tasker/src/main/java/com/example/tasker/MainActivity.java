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
import android.widget.Toast;

import com.example.tasker.controller.dao.TasksDAO;
import com.example.tasker.model.ExpandableListGroup;
import com.example.tasker.model.Task;
import com.example.tasker.utils.TaskerUtils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity
        extends FragmentActivity {
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    private String[] drawerItems;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle = "Drawer Title";
    private CharSequence title = "Title";
    private TaskerUtils utils = new TaskerUtils();
    private TasksDAO tasksDAO;
    private EditText editText;
    private SparseArray<ExpandableListGroup> groups = new SparseArray<ExpandableListGroup>();
    private Calendar taskCalendar = Calendar.getInstance();
    private Calendar todayCalendar = Calendar.getInstance();

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
        drawerItems = getResources().getStringArray(R.array.tasksByDate);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerItems));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //Main list view
        populateListView();
        expandableListAdapter = new ExpandableListAdapter(this, groups);
        expandableListView.setAdapter(expandableListAdapter);

        todayCalendar.setTime(new Date());
    }

    private boolean searchGroups(String title){
        for(int i = 0; i < groups.size() ; i++) {
            if (groups.get(i).getHeader() == title) {
                return true;
            }
        }
        return false;
    }

    private ExpandableListGroup getListGroupByTitle(String title) {
        for(int i = 0; i < groups.size() ; i++) {
            if (groups.get(i).getHeader() == title) {
                return groups.get(i);
            }
        }
        return null;
    }

    private void populateListView() {
        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        List<Task> childItemTitles = tasksDAO.getAllTasks();
        String title = getResources().getStringArray(R.array.tasksByDate)[3];
        Log.d("Main.populateListView", "List with child items" + childItemTitles);

        //This is how you can get Day and Month.
        //if (taskCalendar.DAY_OF_MONTH == todayCalendar.DAY_OF_MONTH && taskCalendar.MONTH == todayCalendar.MONTH) {}
        for (Task task : childItemTitles) {
            Log.d("Main.populateListView", "Adding task:" + task.toString());
            if (!searchGroups(title)) {
                Log.d("Main.populateListView", "Adding group:" + title);
                ExpandableListGroup group = new ExpandableListGroup(title);
                group.setHeader(title);
                group.getChildren().add(task.getTitle());
                groups.append(groups.size(), group);
            } else {
                try {
                    getListGroupByTitle(title).getChildren().add(task.getTitle());
                } catch (Exception ex){
                    Log.e("Main.populateListView", "Adding task failed because no such group exists: " + title);
                }
            }
        }

        Log.d("Main.populateListView", "The sparse array of groups " + groups);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: should implement propper UP vs Back logic
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
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
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //TODO: implement proper swapping of fragments for main View.
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        getActionBar().setTitle(this.title);
    }

    public void addTask(View newItemButton){
        utils.quickAddTask(tasksDAO, String.valueOf(editText.getText()));
    }

    public void deleteTask(View newItemButton){
        utils.deleteAllTasks(tasksDAO);
    }

    public void getAllTasks(View newItem
    ){
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