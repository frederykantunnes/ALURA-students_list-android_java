package br.com.soulzenyogashala.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.soulzenyogashala.agenda.model.Aluno;

public class AlunoDAO {
    private  final static List<Aluno> alunos = new ArrayList<>();
    public void save(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
