package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    private TextView genre, searched, review;
    private int rating;
    private String ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        genre = (TextView) findViewById(R.id.genreSearched);
        genre.setText(getIntent().getStringExtra("genreText"));
        searched = (TextView) findViewById(R.id.itemSearched);
        searched.setText(getIntent().getStringExtra("searchedText"));
        review = (TextView) findViewById(R.id.review);
        review.setText("Rating: " + getIntent().getStringExtra("reviewText"));
        review.setMovementMethod(new ScrollingMovementMethod());
    }

    // adds menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // sends the review using an intent
    private void setShareActionIntent(String text) {
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, text);
//        shareActionProvider.setShareIntent(intent);
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

        }
        if (item.getItemId() == R.id.add_favorites){

        }
        if (item.getItemId() == R.id.share){
        }
        return super.onOptionsItemSelected(item);
    }
}
