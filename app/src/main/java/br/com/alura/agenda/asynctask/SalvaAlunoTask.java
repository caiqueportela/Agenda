package br.com.alura.agenda.asynctask;

import br.com.alura.agenda.database.dao.AlunoDao;
import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDao alunoDao;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDao telefoneDao;

    public SalvaAlunoTask(AlunoDao alunoDao, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular, TelefoneDao telefoneDao, FinalizadaListener listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDao = telefoneDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDao.salvar(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDao.salvar(telefoneFixo, telefoneCelular);
        return null;
    }

}
