package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;


@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String DATABSE = "agenda.db";

    public abstract AlunoDao getRoomAlunoDao();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, AgendaDatabase.DATABSE)
                .allowMainThreadQueries()
                .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                .build();
    }
}
