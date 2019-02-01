package utilities;

import android.content.Context;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.dicoding.listmovie.MovieListItem;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static ArrayList<MovieListItem> fetchData(String url) throws IOException{
        ArrayList<MovieListItem> movieListItems = new ArrayList<MovieListItem>();
        try {
            URL new_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            String results = IOUtils.toString(inputStream);
            parseJson(results,movieListItems);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieListItems;
    }

    public static void parseJson(String results, ArrayList<MovieListItem> movieListItems) {
        try {
            JSONObject mainObject = new JSONObject(results);
            JSONArray resArray = mainObject.getJSONArray("results");
            //Getting the reluts object
            for (int i = 0; i<resArray.length(); i++){
                JSONObject jsonObject = resArray.getJSONObject(i);
                MovieListItem movieListItem = new MovieListItem();
                movieListItem.setId(jsonObject.getInt("id"));
                movieListItem.setVoteAverage(jsonObject.getInt("vote_average"));
                movieListItem.setVoteCount(jsonObject.getInt("vote_count"));
                movieListItem.setOriginalTitle(jsonObject.getString("original_title"));
                movieListItem.setTitle(jsonObject.getString("title"));
                movieListItem.setPopularity(jsonObject.getDouble("popularity"));
                movieListItem.setBackdropPath(jsonObject.getString("backdrop_path"));
                movieListItem.setOverview(jsonObject.getString("overview"));
                movieListItem.setReleaseDate(jsonObject.getString("release_date"));
                movieListItem.setPosterPath(jsonObject.getString("poster_path"));

                movieListItems.add(movieListItem);
            }
            Log.d("NetworkUtils: ","Terdapat Total data :"+movieListItems.get(0).getTitle());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG,"Error during Parsing JSON",e);
        }
    }
    public static Boolean networkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }
}
