package com.example.jj_perez.schematic.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by JJ_PEREZ on 22/09/2015.
 */
@Database(version = FilmDataBase.VERSION)
public class FilmDataBase {

    public static final int VERSION = 1;

    private FilmDataBase() {}

    @Table(FillmsColumns.class) public static final String FILMS = "films";

}


