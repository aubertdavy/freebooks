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

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
//    public static final String TAG = CustomListAdapter.class.getSimpleName();

    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_freebooks_list_item, null);
            holder = new ViewHolder();
            holder.txtTitleView = (TextView)convertView.findViewById(R.id.txtTitle);
            holder.imageBookView = (ImageView)convertView.findViewById(R.id.imgBook);
            holder.imageReaderOneView = (ImageView)convertView.findViewById(R.id.imgReaderOne);
            holder.imageReaderTwoView = (ImageView)convertView.findViewById(R.id.imgReaderTwo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Book newsBook = (Book)listData.get(position);
        holder.txtTitleView.setText(newsBook.getTitle());
        if (holder.imageBookView != null) {
            new ImageDownloaderTask(holder.imageBookView).execute(newsBook.getUlrMediumImage());
        }
        if (holder.imageReaderOneView != null) {
            new ImageDownloaderTask(holder.imageReaderOneView).execute(newsBook.getUlrReaderOneImage());
        }
        if (holder.imageReaderTwoView != null) {
            new ImageDownloaderTask(holder.imageReaderTwoView).execute(newsBook.getUlrReaderTwoImage());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView txtTitleView;
        ImageView imageBookView;
        ImageView imageReaderOneView;
        ImageView imageReaderTwoView;
    }
}