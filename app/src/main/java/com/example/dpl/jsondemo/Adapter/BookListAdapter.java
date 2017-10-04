package com.example.dpl.jsondemo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dpl.jsondemo.R;
import com.example.dpl.jsondemo.bean.Book;

import java.util.ArrayList;

/**
 * Created by dpl on 2017/10/4 0004.
 */

public class BookListAdapter extends BaseAdapter {
    private Context context1;
    private ArrayList<Book> list;
    public BookListAdapter(Context context, ArrayList<Book> books){
        this.context1=context;
        this.list=books;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(holder==null){
            view=View.inflate(context1, R.layout.item,null);
            holder=new ViewHolder();
            holder.textView=view.findViewById(R.id.tv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        Book book=list.get(position);
        holder.textView.setText(book.getTitle()+"\n"+book.getId());
        return view;
    }
    class ViewHolder{
        TextView textView;
    }
}
