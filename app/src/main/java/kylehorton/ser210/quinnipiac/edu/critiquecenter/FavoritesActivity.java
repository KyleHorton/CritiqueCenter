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
    private String rating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mListView = (ListView) findViewById(R.id.listView);
        sql = new FavoritesSQL(this);
        delete = (Button) findViewById(R.id.delete);
        deleteAll = (Button) findViewById(R.id.deleteAll);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sql.populate();

        if (FavoritesSQL.listData.size() == 0){
            mListView.setEmptyView(findViewById(R.id.empty));
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, FavoritesSQL.listData);
            mListView.setAdapter(adapter);
        }
        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteAll();
                sql.populate();
                recreate();
                Log.d(TAG, "All items deleted ");
            }
        });

        //what to do if item clicked in list
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rating = mListView.getItemAtPosition(position).toString();
                Intent intent = new Intent(FavoritesActivity.this, DisplayFavActivity.class);
                intent.putExtra("rating", rating);
                startActivity(intent);
                finish();
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
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(FavoritesActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(FavoritesActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}