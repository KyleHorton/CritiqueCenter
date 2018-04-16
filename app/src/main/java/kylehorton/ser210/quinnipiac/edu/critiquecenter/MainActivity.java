package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //splash screen shown
        int secondsDelayed = 5;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}

