package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Telefone;

public class BuscaTelefoneFixoAlunoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDao dao;
    private final int alunoId;
    private final TelefoneFixoEncontradoListener listener;

    public BuscaTelefoneFixoAlunoTask(TelefoneDao dao, int alunoId, TelefoneFixoEncontradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaTelefoneFixoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.quandoEncontrado(telefone);
    }

    public interface TelefoneFixoEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado
        );
    }

}
