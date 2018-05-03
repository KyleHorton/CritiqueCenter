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
 * Critique Center Application
 * Authors : Mark Russo, Kyle Horton
 * May 2, 2018
 * SER210
 */

public class AsyncClass extends AsyncTask<Void, Void, String> {
    String data = "";
    String cRating = "";
    String uRating = "";
    String search = "";
    String genre = "";
    String director = "";
    String release = "";
    String display = "";
    String artist = "";
    String studio = "";
    String title = "";
    boolean isMovie = false;
    boolean isTV = false;
    boolean isAlbum = false;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Checks spinner for movie tv show or album
        if (SearchActivity.genre.equals("Movie")) {
            genre = "movie";
            isMovie = true;
        } else if (SearchActivity.genre.equals("TV Show")) {
            genre = "tvshow";
            isTV = true;
        } else {
            genre = "album";
            isAlbum = true;
        }
    }

    //API calls
    @Override
    protected String doInBackground(Void... params) {
        search = SearchActivity.search;
        Log.d(TAG, "Searched: " + search);
        Log.d(TAG, "Genre: " + genre);
        HttpURLConnection connection = null;
        URL url = null;

        //if search is a movie
        if (isMovie) {

            // try catch block to receive the API data
            try {
                url = new URL("https://api-marcalencc-metacritic-v1.p.mashape.com/movie/" + search + "/%7Byear%7D?mashape-key=J6MWOEOA8Jmshuu1yzdAszSfcbSMp1uhKuhjsna2IqKdjtZVrR");
                connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                // reads in the text as a string
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray jsonArray = new JSONArray(data);

                // access array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonArrayData = jsonArray.getJSONObject(i);
                    title = jsonArrayData.getString("Title");
                    director = jsonArrayData.getString("Director");
                    release = jsonArrayData.getString("ReleaseDate");
                    JSONObject jsonObject2 = jsonArrayData.getJSONObject("Rating");
                    cRating = jsonObject2.getString("CriticRating");
                    uRating = jsonObject2.getString("UserRating");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Rating: " + cRating);
            Log.d(TAG, "UserRating: " + uRating);
            Log.d(TAG, "ReleaseDate: " + release);
            Log.d(TAG, "Director: " + director);

            // if no rating
            if (cRating.equals("")) {
                display = "ERROR: ITEM NOT FOUND!";
            } else {
                display = "Title: " + title + '\n' + "Director: " + director + '\n' + "Release Date: " + release + '\n' + "MetaCritic Rating: " + cRating + '\n' + "User Rating: " + uRating;
            }
            return display;
        }
        if (isTV) {

            // try catch block to receive the API data
            try {
                url = new URL("https://api-marcalencc-metacritic-v1.p.mashape.com/tvshow/" + search + "/%7Bseason%7D?mashape-key=IOCYZerEwKmshut8w5RvcY6usvyEp1l1U8kjsnaFeBQjtAfcC9");
                connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                // reads in the text as a string
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray jsonArray = new JSONArray(data);

                // access array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonArrayData = jsonArray.getJSONObject(i);
                    title = jsonArrayData.getString("Title");
                    studio = jsonArrayData.getString("Studio");
                    release = jsonArrayData.getString("ReleaseDate");
                    JSONObject jsonObject2 = jsonArrayData.getJSONObject("Rating");
                    cRating = jsonObject2.getString("CriticRating");
                    uRating = jsonObject2.getString("UserRating");

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Rating: " + cRating);
            Log.d(TAG, "UserRating: " + uRating);
            Log.d(TAG, "ReleaseDate: " + release);
            Log.d(TAG, "Studio: " + studio);

            // if no rating
            if (cRating.equals("")) {
                display = "ERROR: ITEM NOT FOUND!";
            } else {
                display = "Title: " + title + '\n' + "Studio: " + studio + '\n' + "Release Date: " + release + '\n' + "MetaCritic Rating: " + cRating + '\n' + "User Rating: " + uRating;
            }
            return display;

        }
        if (isAlbum) {

            // try catch block to receive the API data
            try {
                url = new URL("https://api-marcalencc-metacritic-v1.p.mashape.com/album/" + search + "/%7Byear%7D?mashape-key=IOCYZerEwKmshut8w5RvcY6usvyEp1l1U8kjsnaFeBQjtAfcC9");
                connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                // reads in the text as a string
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray jsonArray = new JSONArray(data);

                // access array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonArrayData = jsonArray.getJSONObject(i);
                    artist = jsonArrayData.getString("PrimaryArtist");
                    title = jsonArrayData.getString("Title");
                    release = jsonArrayData.getString("ReleaseDate");
                    JSONObject jsonObject2 = jsonArrayData.getJSONObject("Rating");
                    cRating = jsonObject2.getString("CriticRating");
                    uRating = jsonObject2.getString("UserRating");

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Rating: " + cRating);
            Log.d(TAG, "UserRating: " + uRating);
            Log.d(TAG, "ReleaseDate: " + release);
            Log.d(TAG, "Primary Artist: " + artist);

            // if no rating
            if (cRating.equals("")) {
                display = "ERROR: ITEM NOT FOUND!";
            } else {
                display = "Title: " + title + '\n' + "Primary Artist: " + artist + '\n' + "Release Date: " + release + '\n' + "MetaCritic Rating: " + cRating + '\n' + "User Rating: " + uRating;
            }
            return display;

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
