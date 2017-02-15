package com.aubert.freebooks.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.aubert.freebooks.R;
import com.aubert.freebooks.entity.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList mBooks;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, ArrayList books) {
        mContext = context;
        mBooks = books;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return mBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_freebooks_list_item, null);
        }

        Book book = (Book)mBooks.get(position);
        if (book != null) {
            ((TextView)convertView.findViewById(R.id.txtTitle)).setText(book.getTitle());

            Picasso.with(mContext)
                   .load(book.getUlrMediumImage())
                   .into((ImageView)convertView.findViewById(R.id.imgBook));
        }

        return convertView;
    }
}