package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_LISTA_ALUNOS;

/**
 * res
 * <p>
 * drawable contem desenhos (imagens) a ser utilizado no app
 * minimap contem icones do aplicativo (normal e arredondado)
 * values possue valores estatico do app
 * - colors possue cores padrão
 * - strings possui variaveis
 * - styles possui os estilos do app
 */

// CTRL + N Entra (busca) em uma classe
// Alt + Shift + X fecha todos arquivos
// Activity possui recursos comum
// AppCompanyActivity Da suporte a versões anteriores do android e adiciona AppBar
public class ListaAlunosActivity extends AppCompatActivity {

    //private final AlunoDAO dao = new AlunoDAO();
    //private ListaAlunosAdapter adapter;
    //private ArrayAdapter<Aluno> adapter;
    private ListaAlunosView listaAlunosView;

    // Activity é um contexto

    // Executado ao criar a activity, ciclo de vida
    // Sobrescreve da classe Activity mas necesita chama-la para realizar tratamentos e funcionalidades automaticamente
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Exibe uma mensagem
        // Toast.makeText(this, "Caique Portela", Toast.LENGTH_SHORT).show();

        // CTRL + P Mostra possibilidades de uso
        // Adicionar .var ao final cria a variavel
        // TextView - View padrão com texto
        //TextView aluno = new TextView(this);
        //aluno.setText("Caique Portela");

        // Defini a view a ser exibida
        //setContentView(aluno);
        // R possui mapeamento de todos resources do projeto
        setContentView(R.layout.activity_lista_alunos);

        // Define titulo da activity (appbar)
        setTitle(TITULO_APPBAR_LISTA_ALUNOS);
        this.listaAlunosView =  new ListaAlunosView(this);

        //List<String> alunos = new ArrayList<>(Arrays.asList("Caique", "Mássylla", "Alex", "Alura", "Bit"));
        // Busca uma view pelo id
        //TextView aluno1 = findViewById(R.id.textView);
        // CTRL + D duplica linha
        //TextView aluno2 = findViewById(R.id.textView2);
        //TextView aluno3 = findViewById(R.id.textView3);

        //aluno1.setText(alunos.get(0));
        //aluno2.setText(alunos.get(1));
        //aluno3.setText(alunos.get(2));

        configuraFabNovoAluno();

        configuraLista();
    }

    // Cria menu de contexto (espécie de menu com clique direito do mouse, click longo nesse caso
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Adiciona um item ao menu
        //menu.add("Remover");
        // "Infla" menu criado com XML e devine no contexto
        getMenuInflater().inflate(R.menu.activity_lista_alunos, menu);
    }

    // Ação executada ao clicar em item do menu de contexto
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoAdicionar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizarAlunos();
    }

    private void configuraLista() {
        // ListView implementa uma lista
        ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listView);
        // Define o adapter para tratar os dados e construtir a view, passando um layout padrão de view de lista e os dados
        listaAlunosView.configuraAdapter(listaDeAlunos);

        configuraListenerDeCliquePorItem(listaDeAlunos);

        //configuraListenerDeCliqueLongoPorItem(listaDeAlunos);

        // Registra menu de contexto na ListView, sabe entender que o clique é por filho (item)
        registerForContextMenu(listaDeAlunos);
    }

    /*private void configuraListenerDeCliqueLongoPorItem(final ListView listaDeAlunos) {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                removerAluno(alunoEscolhido);
                //Toast.makeText(ListaAlunosActivity.this, "Apagar " + alunoEscolhido, Toast.LENGTH_SHORT).show();
                return true; // Se false, indica a continuação do evento, então vai executar o click normal. Se true, indica que tratarei todo o evento sozinho
            }
        });
    }*/

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent formularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        formularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(formularioActivity);
    }

}
