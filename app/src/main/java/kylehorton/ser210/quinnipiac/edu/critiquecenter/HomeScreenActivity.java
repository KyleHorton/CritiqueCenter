package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
}
