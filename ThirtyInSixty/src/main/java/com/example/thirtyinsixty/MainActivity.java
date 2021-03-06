package com.example.thirtyinsixty;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thirtyinsixty.App_0.App_0;
import com.example.thirtyinsixty.App_1.CrossfitTimerActivity;
import com.example.thirtyinsixty.App_2.ShakeIt;
import com.example.thirtyinsixty.App_3.TaskList;
import com.example.thirtyinsixty.App_4.ParseTaskList;
import com.example.thirtyinsixty.App_5.NotifyYourself;

public class MainActivity extends ListActivity {

    private static final String APP_0 = "Whack-a-Fragment";
    private static final String APP_1 = "Crossfit Timer";
    private static final String APP_2 = "Shake It";
    private static final String APP_3 = "Task List";
    private static final String APP_4 = "Task List + Parse";
    private static final String APP_5 = "Notify Yourself";


    private String[] apps = { APP_0, APP_1, APP_2, APP_3, APP_4, APP_5 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = getListView();
        setListAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, apps));
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        String appName = apps[position];
        Intent in;

        switch (position) {
            case 0:
                in = new Intent(this, App_0.class);
                startActivity(in);
                break;
            case 1:
                in = new Intent(this, CrossfitTimerActivity.class);
                startActivity(in);
                break;
            case 2:
                in = new Intent(this, ShakeIt.class);
                startActivity(in);
                break;
            case 3:
                in = new Intent(this, TaskList.class);
                startActivity(in);
                break;
            case 4:
                in = new Intent(this, ParseTaskList.class);
                startActivity(in);
                break;
            case 5:
                in = new Intent(this, NotifyYourself.class);
                startActivity(in);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
