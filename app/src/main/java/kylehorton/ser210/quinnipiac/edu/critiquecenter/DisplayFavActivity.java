package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Critique Center Application
 * Authors : Mark Russo, Kyle Horton
 * May 2, 2018
 * SER210
 */

public class DisplayFavActivity extends AppCompatActivity {
    private TextView rating;
    private Button delete;
    private String lastRating = "";
    private FavoritesSQL database;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = new FavoritesSQL(this);

        //changes text and theme
        rating = (TextView)findViewById(R.id.textRating);
        rating.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        rating.setMovementMethod(new ScrollingMovementMethod());
        rating.setText(getIntent().getStringExtra("rating"));
        lastRating = rating.getText().toString();
        delete = (Button) findViewById(R.id.delete);

        // allows for back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // restores the data
        if (savedInstanceState != null) {
            rating.setText(savedInstanceState.getString("rating"));
        }

        // what to do when delete button is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteRating(lastRating);
                database.populate();
                Intent intent = new Intent(DisplayFavActivity.this, FavoritesActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    // saves data if orientation flips
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("joke", rating.getText().toString());
    }

    // adds the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    // sends the joke using an intent
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    // tells you which item from the toolbar is clicked on
    // each item has a specific id
    // each item has a specific action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.favorites){
            Intent intent = new Intent(DisplayFavActivity.this, FavoritesActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.settings){
            Intent intent3 = new Intent(this, SettingsActivity.class);
            startActivity(intent3);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.share){
            setShareActionIntent("You should check out the " + SearchActivity.genre + " " + SearchActivity.search + ". It got a " +
                    "rating of " + getIntent().getStringExtra("reviewText") + " from Metacritics!");

        }
        return super.onOptionsItemSelected(item);
    }
}
