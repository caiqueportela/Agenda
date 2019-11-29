package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_EDITAR_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_NOVO_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    //private EditText campoSobrenome;
    private EditText campoEmail;
    private EditText campoTelefone;

    private Aluno aluno;

    //private final AlunoDAO dao = new AlunoDAO();
    private AlunoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);

        this.dao = AgendaDatabase.getInstance(this).getRoomAlunoDao();

        inicializaCampos();

        //configuraBotaoSalvar();

        carregaAluno();
    }

    // Cria menu de opções na appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITAR_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        //campoSobrenome.setText(aluno.getSobrenome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    /*
    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizaFormulario();
            }
        });
    }
    */

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.editar(aluno);
        } else {
            dao.salvar(aluno);
        }
        finish();
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        //campoSobrenome = findViewById(R.id.activity_formulario_aluno_sobrenome);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        //String sobrenome = campoSobrenome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        aluno.setNome(nome);
        //aluno.setSobrenome(sobrenome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

}
