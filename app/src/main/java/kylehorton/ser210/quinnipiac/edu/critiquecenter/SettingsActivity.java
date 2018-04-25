package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Button darkBTN, dayBTN, defaultBTN, previewText,setText;
    public static int currTheme = R.style.AppTheme;
    private String chosenFont = "";
    public static String currText = "applegaramond.ttf";
    private TextView settingsText, backText, fontText;
    private Spinner spinner;

    // creates views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creates back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        settingsText = (TextView) findViewById(R.id.settingsText);
        settingsText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        backText = (TextView) findViewById(R.id.backText);
        backText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        fontText = (TextView) findViewById(R.id.fontText);
        fontText.setTypeface(Typeface.createFromAsset(getAssets(), SettingsActivity.currText));
        darkBTN = (Button) findViewById(R.id.darkBTN);
        defaultBTN = (Button) findViewById(R.id.defaultTheme);
        dayBTN = (Button) findViewById(R.id.dayBTN);
        previewText = (Button) findViewById(R.id.previewText);
        setText = (Button) findViewById(R.id.setText);

        spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.fonts));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        // what to do if clicked
        darkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppThemeDark;
                recreate();
            }
        });

        // what to do if clicked
        defaultBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppTheme;
                recreate();
            }
        });

        // what to do if clicked
        dayBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currTheme = R.style.AppThemeDay;
                recreate();
            }
        });

        previewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, FontActivity.class);
                startActivity(intent);
                finish();

            }
        });

        setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenFont = spinner.getSelectedItem().toString();

                if (chosenFont.equals("Neon 80s")){
                    currText = "neon.ttf";
                    recreate();

                }
                if (chosenFont.equals("Super Mario")){
                    currText = "supermario.ttf";
                    recreate();

                }
                if (chosenFont.equals("Apple Garamond")){
                    currText = "applegaramond.ttf";
                    recreate();

                }
                if (chosenFont.equals("Blacklisted")){
                    currText = "blacklisted.ttf";
                    recreate();

                }
                if (chosenFont.equals("Mexican Fiesta")){
                    currText = "mexicanfiesta.ttf";
                    recreate();

                }
                if (chosenFont.equals("College Collage")){
                    currText = "collegecollage.ttf";
                    recreate();
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
            Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.add_favorites){
            Toast.makeText(SettingsActivity.this, "No review to add to favorites!",
                    Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.share){
            Toast.makeText(SettingsActivity.this, "There's no review to share yet!",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
