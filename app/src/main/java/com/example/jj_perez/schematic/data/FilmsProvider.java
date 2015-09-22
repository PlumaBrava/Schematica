package com.example.jj_perez.schematic.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by JJ_PEREZ on 22/09/2015.
 */

@ContentProvider(authority = FilmsProvider.AUTHORITY, database = FilmDataBase.class)
public class FilmsProvider {

    public static final String AUTHORITY =
            "com.example.jj_perez.schematic.data.FilmsProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String FILMS = "films";

    }

    private static Uri buildUri(String ... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths){
            builder.appendPath(path);
        }
        return builder.build();
    }
    @TableEndpoint(table = FilmDataBase.FILMS) public static class Films{
        @ContentUri(
                path = Path.FILMS,
                type = "vnd.android.cursor.dir/planet",
                defaultSort = FillmsColumns.VOTES + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.FILMS);

        @InexactContentUri(
                name = "PLANET_ID",
                path = Path.FILMS + "/#",
                type = "vnd.android.cursor.item/planet",
                whereColumn = FillmsColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id){
            return buildUri(Path.FILMS, String.valueOf(id));
        }


    }

}
