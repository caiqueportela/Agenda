package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDao {

    @Query("SELECT t.* FROM Telefone t WHERE t.alunoId = :alunoId AND t.tipo = 'FIXO' LIMIT 1")
    Telefone buscaTelefoneFixoAluno(int alunoId);

    @Insert
    void salvar(Telefone... telefones);

    @Query("SELECT * FROM Telefone t WHERE t.alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesAluno(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualizar(Telefone... telefones);

}
