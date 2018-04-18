package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Mark Russo and Kyle Horton
 * April 16, 2018
 */

public class HomeScreenActivity extends AppCompatActivity {

    private Button review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //underlines the title on the home screen
        TextView title = findViewById(R.id.title);
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        review = (Button) findViewById(R.id.find_review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreenActivity.this, SearchActivity.class);
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
        if (item.getItemId() == R.id.favorites){

        }
        if (item.getItemId() == R.id.settings){

        }
        if (item.getItemId() == R.id.add_favorites){
            Toast.makeText(HomeScreenActivity.this, "No review to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(HomeScreenActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
