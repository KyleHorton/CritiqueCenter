package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsActivity.currTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }
}
