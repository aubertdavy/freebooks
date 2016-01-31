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

    private final String TAG_TITLE = "title";
    private final String TAG_BOOKS = "books";
    private final String TAG_MODULES = "modules";
    private final String TAG_READERS = "readers";
    private final String TAG_AVATAR = "avatar";
    private final String TAG_MEDIUMIMAGE = "mediumImage";

    private ArrayList<Book> books;

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

                books = new ArrayList<Book>();

                try {
                    JSONArray jsonModules = response.getJSONArray(TAG_MODULES);
                    JSONArray jsonList = (JSONArray)jsonModules.get(0);
                    JSONObject jsonBook = (JSONObject)jsonList.get(1);
                    JSONArray jsonBooks = jsonBook.getJSONArray(TAG_BOOKS);

                    for (int i = 0; i < jsonBooks.length(); i++) {
                        JSONObject jsonObj = (JSONObject)jsonBooks.get(i);

                        Book book = new Book();
                        book.setUrlMediumImage(jsonObj.getString(TAG_MEDIUMIMAGE));
                        book.setTitle(jsonObj.getString(TAG_TITLE));

                        JSONArray jsonReaders = jsonObj.getJSONArray(TAG_READERS);
                        if (jsonReaders.length() >= 0){
                            JSONObject jsonReader = (JSONObject)jsonReaders.get(0);
                            book.setUrlReaderOneImage(jsonReader.getString(TAG_AVATAR));
                        }
                        if (jsonReaders.length() >= 1){
                            JSONObject jsonReader = (JSONObject)jsonReaders.get(1);
                            book.setUrlReaderTwoImage(jsonReader.getString(TAG_AVATAR));
                        }
                        books.add(book);
                        Log.d(TAG, jsonObj.getString(TAG_MEDIUMIMAGE));
                    }

                    final ListView listView = (ListView)findViewById(R.id.books_list);
                    listView.setAdapter(new CustomListAdapter(getApplicationContext(), books));

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
