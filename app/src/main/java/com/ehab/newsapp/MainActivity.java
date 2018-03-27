package com.ehab.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ehab.newsapp.model.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.RecyclerViewClickListener, LoaderManager.LoaderCallbacks<List<Results>>{
    public static final int LOADER_ID = 22;
    public static final String BASE_URL = "https://content.guardianapis.com/search?";
    final String APIKEY_PARAM = "api-key";

    RecyclerView newsRecyclerView;
    TextView emptyView;

    NewsAdapter adapter;
    List<Results> results = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = findViewById(R.id.empty_view);
        newsRecyclerView = findViewById(R.id.newsRecylerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NewsAdapter(results, this);
        newsRecyclerView.setAdapter(adapter);
        toggleEmptyView(results);

        if(isConnected()) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        }else{
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText("No Internet Connection");
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(Results result) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(result.getWebUrl()));
        startActivity(intent);
    }

    @Override
    public Loader<List<Results>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<Results>>(this) {
            List<Results> news;

            @Override
            public List<Results> loadInBackground() {

                Uri uri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter(APIKEY_PARAM, "test")
                        .build();

                URL url = null;
                try {
                    url = new URL(uri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                String jsonRecipesResponse = null;
                try {
                    jsonRecipesResponse = getResponseFromHttpUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    news = getNewsFromJson(jsonRecipesResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return news;
            }

            @Override
            protected void onStartLoading() {
                if (news !=null) {
                    deliverResult(news);
                }else{
                    forceLoad();
                }
            }

            @Override
            public void deliverResult(List<Results> data) {
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Results>> loader, List<Results> data) {
        toggleEmptyView(data);
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Results>> loader) {

    }

    private String getResponseFromHttpUrl(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    private List<Results> getNewsFromJson(String jsonNewsResponse) throws JSONException {
        List<Results> parsedNewsData = new ArrayList<>();

        JSONObject jsonObj = new JSONObject(jsonNewsResponse);
        JSONObject responseObj = jsonObj.getJSONObject("response");
        JSONArray resultsJSONArray = responseObj.getJSONArray("results");
        for (int i = 0; i < resultsJSONArray.length(); i++) {
            JSONObject newsItem = resultsJSONArray.getJSONObject(i);
            Results result = new Results();
            result.setId(newsItem.getString("id"));
            result.setType(newsItem.getString("type"));
            result.setSectionId(newsItem.getString("sectionId"));
            result.setSectionName(newsItem.getString("sectionName"));
            result.setWebPublicationDate(newsItem.getString("webPublicationDate"));
            result.setWebTitle(newsItem.getString("webTitle"));
            result.setWebUrl(newsItem.getString("webUrl"));
            result.setApiUrl(newsItem.getString("apiUrl"));
            result.setApiUrl(newsItem.getString("apiUrl"));
            result.setIsHosted(newsItem.getString("isHosted"));
            result.setPillarId(newsItem.getString("pillarId"));
            result.setPillarName(newsItem.getString("pillarName"));

            parsedNewsData.add(result);
        }

        return parsedNewsData;
    }

    private void toggleEmptyView(List<Results> results){
        if (results.isEmpty()) {
            newsRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            newsRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private Boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
