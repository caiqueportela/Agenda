package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDao telefoneDao;
    private final Aluno aluno;
    private final TelefonesAlunoEncontradosListener listener;

    public BuscaTodosTelefonesAlunoTask(TelefoneDao telefoneDao, Aluno aluno, TelefonesAlunoEncontradosListener listener) {
        this.telefoneDao = telefoneDao;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDao.buscaTodosTelefonesAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesAlunoEncontradosListener {
        void quandoEncontrados(List<Telefone> telefones);
    }

}
