package com.example.jj_perez.schematic.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jj_perez.schematic.R;
/**
 * Created by JJ_PEREZ on 22/09/2015.
 */
public class FilmCursosAdapter extends CursorAdapter{

    public FilmCursosAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        DatabaseUtils.dumpCursor(cursor);
        TextView idView = (TextView) view.findViewById(R.id.list_item_id);
        idView.setText(cursor.getString(cursor.getColumnIndex(FillmsColumns._ID)));

        TextView nameView = (TextView) view.findViewById(R.id.list_item_name);
        nameView.setText(cursor.getString(
                cursor.getColumnIndex(FillmsColumns.NAME)));

        TextView reviewView = (TextView) view.findViewById(R.id.list_item_review);
        reviewView.setText(cursor.getInt(
                cursor.getColumnIndex(FillmsColumns.VOTES)));

        TextView voteView = (TextView) view.findViewById(R.id.list_item_votes);
        voteView.setText(cursor.getString(
                cursor.getColumnIndex(FillmsColumns.REVIEW)));



    }

}
