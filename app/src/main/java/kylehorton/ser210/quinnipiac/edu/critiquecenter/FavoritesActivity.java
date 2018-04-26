package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";
    FavoritesSQL sql;
    private ListView mListView;
    private Button delete;
    private Button deleteAll;
    private String itemDeleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mListView = (ListView) findViewById(R.id.listView);
        sql = new FavoritesSQL(this);
        delete = (Button) findViewById(R.id.delete);
        deleteAll = (Button) findViewById(R.id.deleteAll);
        populateListView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteEntry();
                recreate();
                populateListView();
                Log.d(TAG, "Item deleted ");
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteAll();
                recreate();
                populateListView();
                Log.d(TAG, "All items deleted ");
            }
        });


    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        //get the data and append to a list
        Cursor data = sql.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = sql.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }

            }
        });
    }

    // adds menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // tells you which item from the toolbar is clicked on
    // each item has a specific id
    // each item has a specific action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.favorites){

        }
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(FavoritesActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.add_fav){
            Toast.makeText(FavoritesActivity.this, "No review to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(FavoritesActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}