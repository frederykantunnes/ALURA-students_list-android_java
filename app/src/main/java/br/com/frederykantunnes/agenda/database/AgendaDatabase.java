package br.com.frederykantunnes.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.frederykantunnes.agenda.database.converters.ConversorData;
import br.com.frederykantunnes.agenda.database.dao.RoomAlunoDAO;
import br.com.frederykantunnes.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
@TypeConverters({ConversorData.class})
public abstract class AgendaDatabase extends RoomDatabase {
    public static String DB_NAME = "agenda.db";
    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AgendaDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
