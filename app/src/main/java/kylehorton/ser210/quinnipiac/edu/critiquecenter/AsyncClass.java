package kylehorton.ser210.quinnipiac.edu.critiquecenter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Kyleh on 4/17/2018.
 */

public class AsyncClass extends AsyncTask<Void, Void, String> {
    String data = "";
    String rating = "";
    String search = "";
    String genre = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (SearchActivity.genre.equals("Movie")) {
            genre = "movie";
        } else if (SearchActivity.genre.equals("TV Show")){
            genre = "tvshow";
        } else {
            genre = "album";
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        search = SearchActivity.search;

        Log.d(TAG, "Searched: "+ search);
        Log.d(TAG, "Genre: " + genre);

        HttpURLConnection connection = null;
        URL url = null;

        // try catch block to receive the API data
        try {
            url = new URL("https://api-marcalencc-metacritic-v1.p.mashape.com/search/" + search + "/" + genre + "?limit=1&offset=1&mashape-key=IOCYZerEwKmshut8w5RvcY6usvyEp1l1U8kjsnaFeBQjtAfcC9");
            connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            // reads in the text as a string
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            // using the JSon libraries to parse the strings read in above
            JSONArray jsonArray = new JSONArray(data);

            // access array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonArrayData = jsonArray.getJSONObject(i);

                JSONArray jsonArray1 = jsonArrayData.getJSONArray("SearchItems");

                // access second array to find rating
                for (int x = 0; i < jsonArray1.length(); x++) {
                    JSONObject jsonObject = jsonArray1.getJSONObject(x);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("Rating");
                    rating = jsonObject1.getString("CriticRating");

                    // if no rating
                    if (rating.length() == 0){
                        rating = "ERROR: Invalid Search! Please enter a different item or genre!";
                    }

                }

                // if the array is empty
                if (jsonArray1.length() == 0){
                    rating = "ERROR: Invalid Search! Please enter a different item or genre!";
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Rating: "+ rating);
        return rating;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
