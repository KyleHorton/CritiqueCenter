package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Critique Center Application
 * Authors : Mark Russo, Kyle Horton
 * May 2, 2018
 * SER210
 */

public class AboutActivity extends AppCompatActivity {
    private TextView about1, about2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //sets the text theme
        about1 = (TextView) findViewById(R.id.authors);
        about1.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        about2 = (TextView) findViewById(R.id.about2);
        about2.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));

        AboutFragment frag = (AboutFragment) getFragmentManager().findFragmentById(R.id.about_frag);
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
            Intent intent = new Intent(AboutActivity.this, FavoritesActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.settings){
            Intent intent = new Intent(AboutActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(AboutActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
