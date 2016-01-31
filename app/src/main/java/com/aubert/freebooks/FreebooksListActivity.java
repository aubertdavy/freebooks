package com.aubert.freebooks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FreebooksListActivity extends Activity {
    private final  String TAG = "FreebooksListActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freebooks_list);
    }
}
