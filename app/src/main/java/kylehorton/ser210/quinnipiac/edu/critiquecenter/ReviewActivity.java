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
import android.widget.Toast;

/**
 * Critique Center Application
 * Authors : Mark Russo, Kyle Horton
 * May 2, 2018
 * SER210
 */
public class ReviewActivity extends AppCompatActivity {
    private TextView genre, searched, review;
    private ShareActionProvider shareActionProvider;
    private Button favs;
    FavoritesSQL sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sql = new FavoritesSQL(this);
        favs = (Button) findViewById(R.id.add_favorite);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //sets set and themes for all text and backgrounds
        genre = (TextView) findViewById(R.id.genreSearched);
        genre.setText(getIntent().getStringExtra("genreText"));
        genre.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        searched = (TextView) findViewById(R.id.itemSearched);
        searched.setText(getIntent().getStringExtra("searchedText"));
        searched.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        review = (TextView) findViewById(R.id.review);
        review.setText(getIntent().getStringExtra("reviewText"));
        review.setMovementMethod(new ScrollingMovementMethod());
        review.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(review.getText().toString());
            }
        });
    }

    // adds menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    // sends the review using an intent
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
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (item.getItemId() == R.id.favorites) {
            sql.populate();
            Intent intent = new Intent(ReviewActivity.this, FavoritesActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(ReviewActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.share) {
            setShareActionIntent("You should check out the " + SearchActivity.genre + " " + SearchActivity.search + ". It got a " +
                    "rating of " + getIntent().getStringExtra("reviewText") + " from Metacritics!");
        }
        return super.onOptionsItemSelected(item);
    }

    // adds data to the database and notifies the user if successful
    public void addData(String item) {
        boolean dataInserted = sql.addData(item);

        if (dataInserted) {
            Toast.makeText(ReviewActivity.this, "Rating added to favorites!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ReviewActivity.this, "Oops! Something went wrong!",
                    Toast.LENGTH_LONG).show();
        }
    }
}