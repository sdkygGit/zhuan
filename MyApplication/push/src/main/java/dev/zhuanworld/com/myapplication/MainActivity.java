package dev.zhuanworld.com.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        List<Map<String, String>> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "sfsjfskfssfsfssfksjfksfiesfsja  :" + i);
            strings.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this,strings,android.R.layout.simple_list_item_1,new String[]{"name"},new int[]{android.R.id.text1}));
    }

}
