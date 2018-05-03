package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {
    private Spinner spinner;
    public static String genre = "";
    public static String search = "";
    private String reviewText = "";
    private Button searchButton;
    private TextView searchTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.genres));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genre = spinner.getSelectedItem().toString(); //converts selected genre to string
                Log.d(genre, "onClick: " + genre);

                SearchView searchedItem = (SearchView) findViewById(R.id.search_View);
                search = searchedItem.getQuery().toString();
                searchedItem.setIconified(false);

                if (!search.equals("") && search.length() > 3) {
                    AsyncClass async = new AsyncClass();
                    try {
                        reviewText = async.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SearchActivity.this, ReviewActivity.class);
                    intent.putExtra("reviewText", reviewText);
                    intent.putExtra("genreText", genre);
                    intent.putExtra("searchedText", search);
                    startActivity(intent);
                    finish();

                } else if (search.length() <=3 && search.length() >0){
                    Toast.makeText(SearchActivity.this, "Search query should be at least three characters!", // if search query less than 3 characters
                            Toast.LENGTH_LONG).show();

                }

                else {
                    Toast.makeText(SearchActivity.this, "Please enter an item to search!", // if no item is entered but button still pressed
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        searchTopic = (TextView) findViewById(R.id.searchTopic);
        searchTopic.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
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
            Intent intent = new Intent(SearchActivity.this, FavoritesActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(SearchActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(SearchActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
