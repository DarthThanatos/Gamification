package vobis.example.com.gamification.floatingactivity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vobis.example.com.gamification.R;

public class FloatingActivity extends ActionBarActivity {
    private  static  final String TAG = "floating_tag";
    private List<FeedItem> feedItemList;
    private RecyclerView recyclerView;
    private CustomRecyclerAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        recyclerView = (RecyclerView) findViewById(R.id.floating_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
        new DownloadTask().execute(url);
    }

    public class DownloadTask extends AsyncTask<String, Void, Integer>{

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                if(statusCode == 200){
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = br.readLine())!=null){
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1;

                }
                else{
                    result = 0;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result){
            progressBar.setVisibility(View.GONE);
            if(result == 1){
                adapter = new CustomRecyclerAdapter(FloatingActivity.this, feedItemList);
                CustomRecyclerAdapter.OnFloatingItemClickListener floatingListener =
                        new CustomRecyclerAdapter.OnFloatingItemClickListener() {
                            @Override
                            public void onClick(FeedItem item) {
                                Toast.makeText(FloatingActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        };
                adapter.setFloatingListener(floatingListener);
                recyclerView.setAdapter(adapter);
            }
            else{
                Toast.makeText(FloatingActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String result){
        try{
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            feedItemList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++){
                JSONObject post = posts.optJSONObject(i);
                FeedItem item = new FeedItem();
                item.setTitle(post.optString("title"));
                item.setThumbnail(post.optString("thumbnail"));
                feedItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_floating, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
