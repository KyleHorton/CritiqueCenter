package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.util.Log;

import org.junit.Test;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Created by Kyleh on 4/25/2018.
 */
public class AsyncClassTest {
    @Test
    public void onPostExecute() throws Exception {
        AsyncClass asyncClass = new AsyncClass();
        SearchActivity.search = "Step Brothers";
        SearchActivity.genre = "Movie";
        String output = asyncClass.execute().get();
        Log.d(TAG, "onPostExecute: " + output);
        assertEquals("51", output);
    }

}