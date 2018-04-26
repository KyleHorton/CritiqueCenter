package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import org.junit.Test;

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
        assertEquals("51", output);
    }

}