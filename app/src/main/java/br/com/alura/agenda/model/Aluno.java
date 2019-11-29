package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    //private String sobrenome;
    private String telefone;
    private String email;


    private Calendar momentoCadastro = Calendar.getInstance();

    public Calendar getMomentoCadastro() {
        return momentoCadastro;
    }

    public void setMomentoCadastro(Calendar momentoCadastro) {
        this.momentoCadastro = momentoCadastro;
    }

    /*
        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return (this.id > 0);
    }

    public String getNomeCompleto() {
        return this.nome;// + " " + this.sobrenome;
    }

    public String dataFormatada() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyy");
        return formatador.format(momentoCadastro.getTime());
    }

}
