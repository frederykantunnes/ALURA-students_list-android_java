package br.com.frederykantunnes.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String sobrenome;
    private Calendar datadecriacao = Calendar.getInstance();

    public Calendar getDatadecriacao() {
        return datadecriacao;
    }

    public void setDatadecriacao(Calendar datadecriacao) {
        this.datadecriacao = datadecriacao;
    }

    @Ignore
    public Aluno(String nome, String telefone, String email, String sobrenome) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.sobrenome = sobrenome;
    }


    public Aluno(){

    }


    @NonNull
    @Override
    public String toString() {
        return nome;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}
