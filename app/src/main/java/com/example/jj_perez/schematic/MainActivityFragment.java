package com.example.jj_perez.schematic;

import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.jj_perez.schematic.data.FillmsColumns;
import com.example.jj_perez.schematic.data.FilmCursosAdapter;
import com.example.jj_perez.schematic.data.FilmsProvider;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private static final int CURSOR_LOADER_ID = 0;

    private FilmCursosAdapter mFilmAdapter;

    Film[] films = {
            new Film("Mercury", 57, "buena"),
            new Film("Luna", 58, "regular"),
            new Film("Sol", 59, "medium"),
            new Film("Tierra", 60, "excelent"),

    };


    public MainActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){






        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);

        Cursor c = getActivity().getContentResolver().query(FilmsProvider.Films.CONTENT_URI,
                null, null, null, null);
     //   Log.i(LOG_TAG, "cursor count: " + c.getCount());
        if (c == null || c.getCount() == 0){
            insertData();
        }
        mFilmAdapter = new FilmCursosAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        GridView listView = (GridView) rootView.findViewById(R.id.movies_list);
        listView.setAdapter(mFilmAdapter);


        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), FilmsProvider.Films.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mFilmAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mFilmAdapter.swapCursor(null);
    }

    public void insertData() {
        Log.d(LOG_TAG, "insert");
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>(films.length);

        for (Film film : films) {
            ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                    FilmsProvider.Films.CONTENT_URI);
            builder.withValue(FillmsColumns.NAME, film.getName());
            builder.withValue(FillmsColumns.VOTES, film.getVotes());
            builder.withValue(FillmsColumns.REVIEW, film.getReview());
            batchOperations.add(builder.build());
        }

        try {
            getActivity().getContentResolver().applyBatch(FilmsProvider.AUTHORITY, batchOperations);
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(LOG_TAG, "Error applying batch insert", e);
        }

    }
}