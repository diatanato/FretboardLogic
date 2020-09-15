package com.diatanato.android.fretboarlogic.database.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class StringsConverter
{
    @TypeConverter
    public static List<Integer> fromString(String value) {
        return new Gson().fromJson(value, new TypeToken<List<Integer>>() {}.getType());
    }

    @TypeConverter
    public static String fromList(List<Integer> list) {
        return new Gson().toJson(list);
    }
}
