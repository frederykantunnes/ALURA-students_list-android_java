package br.com.frederykantunnes.agenda.database.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorData {

    @TypeConverter
    public Long toLong(Calendar valor){
        return valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long valor){
        Calendar instance = Calendar.getInstance();
        if(valor  != null ) {
            instance.setTimeInMillis(valor);
        }
        return instance;
    }

}
