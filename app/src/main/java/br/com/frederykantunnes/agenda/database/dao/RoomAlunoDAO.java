package br.com.frederykantunnes.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.frederykantunnes.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {

    @Insert
    void save(Aluno aluno);

    @Delete
    void remove(Aluno aluno);

    @Query("select * from aluno")
    List<Aluno> getAll();

    @Update
    void edit(Aluno aluno);
}
