package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {
    private TextView genre, searched, review;
    private int rating;
    private String ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        genre = (TextView) findViewById(R.id.genreSearched);
        genre.setText(getIntent().getStringExtra("genreText"));
        searched = (TextView) findViewById(R.id.itemSearched);
        searched.setText(getIntent().getStringExtra("searchedText"));
        review = (TextView) findViewById(R.id.review);
        review.setText("Rating: " + getIntent().getStringExtra("reviewText"));
        review.setMovementMethod(new ScrollingMovementMethod());
    }
}
