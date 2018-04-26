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

        genre = (TextView) findViewById(R.id.genreSearched);
        genre.setText(getIntent().getStringExtra("genreText"));
        genre.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        searched = (TextView) findViewById(R.id.itemSearched);
        searched.setText(getIntent().getStringExtra("searchedText"));
        searched.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        review = (TextView) findViewById(R.id.review);
        review.setText("Rating: " + getIntent().getStringExtra("reviewText"));
        review.setMovementMethod(new ScrollingMovementMethod());
        review.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry1 = searched.getText().toString();
                String newEntry = review.getText().toString();
                if (review.length() != 0 && searched.length() != 0) {
                    AddData("Title: " + newEntry1 + "  " + newEntry);
                } else {
                    toastMessage("You must put something in this text field!");
                }
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
        if (item.getItemId() == R.id.favorites){
            Intent intent = new Intent(ReviewActivity.this, FavoritesActivity.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(ReviewActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.add_fav){

        }
        if (item.getItemId() == R.id.share){
            setShareActionIntent("You should check out the " + SearchActivity.genre + " " + SearchActivity.search + ". It got a " +
                    "rating of " + getIntent().getStringExtra("reviewText") + " from Metacritics!");
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddData(String newEntry) {
        boolean insertData = sql.addData(newEntry);
        if (insertData) {
            toastMessage("Review added to your favorites!");
        } else {
            toastMessage("ERROR: Value not found!");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}