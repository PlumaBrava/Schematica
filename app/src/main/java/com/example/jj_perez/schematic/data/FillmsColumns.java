package com.example.jj_perez.schematic.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by JJ_PEREZ on 22/09/2015.
 */
public class FillmsColumns {

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID =
            "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String NAME = "name";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String VOTES =
            "votes";
    @DataType(DataType.Type.TEXT) @NotNull public static final String REVIEW =
            "review";

}
