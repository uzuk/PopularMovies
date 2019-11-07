package com.example.popularmovies1.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieNetworkUtilities {

    public static final String API_KEY = "b8d758b8afc922c290f0860cefab9ce6";

    private static final String LOG_TAG = MovieNetworkUtilities.class.getSimpleName();
    private static final String MOVIE_QUERY_API = "api_key";
    private static final String MOVIE_BASE = "https://api.themoviedb.org/3/movie/";

    public static URL buildUrl(String movieURL){
        Uri uri = Uri.parse( MOVIE_BASE )
                .buildUpon()
                .appendPath( movieURL )
                .appendQueryParameter( MOVIE_QUERY_API, API_KEY )
                .build();
        URL url = null;

        try {
            url = new URL( uri.toString() );
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem create URL ", e);
        }
        return url;
    }
    public static String getResponseFromHTTP(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }


    }
}
