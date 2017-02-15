package com.aubert.freebooks.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.aubert.freebooks.utils.ApplicationController;
import com.aubert.freebooks.R;
import com.aubert.freebooks.entity.Book;
import com.aubert.freebooks.utils.CustomListAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class FreebooksListActivity extends Activity {
    public static final String TAG = FreebooksListActivity.class.getSimpleName();

    private ArrayList<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freebooks_list);

        getFreebooks();
    }

    private void getFreebooks() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, "https://api.glose.com/v1/booklists/free-books?_version=20150601", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                mBooks = new ArrayList<Book>();

                try {
                    JSONArray arrModules = response.getJSONArray("modules");

                    JSONObject module = (JSONObject)arrModules.get(1);

                    JSONArray arrBooks = module.getJSONArray("books");

                    for (int i = 0; i < arrBooks.length(); i++) {
                        JSONObject jsonObj = (JSONObject)arrBooks.get(i);

                        Book book = new Book();
                        book.setUrlMediumImage(jsonObj.getString("mediumImage"));
                        book.setTitle(jsonObj.getString("title"));
                        mBooks.add(book);

                        Log.d(TAG, jsonObj.getString("mediumImage"));
                    }

                    final ListView listView = (ListView)findViewById(R.id.books_list);
                    listView.setAdapter(new CustomListAdapter(getApplicationContext(), mBooks));

                } catch (JSONException error) {
                    VolleyLog.e(TAG, error.getMessage());
                    Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ApplicationController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
