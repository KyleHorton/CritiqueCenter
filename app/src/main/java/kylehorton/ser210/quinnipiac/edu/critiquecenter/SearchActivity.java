package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {
    private Spinner spinner;
    public static String genre = "";
    public static String search = "";
    private String reviewText = "";
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

                EditText searchedItem = (EditText) findViewById(R.id.editText);
                search = searchedItem.getText().toString();

                if (!search.equals("")) {
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

                } else {
                    Toast.makeText(SearchActivity.this, "Please enter an item to search!", // if no item is entered but button still pressed
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
