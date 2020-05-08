package br.com.frederykantunnes.agenda.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.frederykantunnes.agenda.model.Aluno;

public class AlunoDAO {
    private  final static List<Aluno> alunos = new ArrayList<>();
    private static int contador = 1;
    public void save(Aluno aluno) {
        aluno.setId(contador);
        alunos.add(aluno);
        contador++;
    }
    
    public void edit(Aluno aluno){
        Aluno alunoencontrado = null;
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()){
                alunoencontrado = a;
            }
        }
        if (alunoencontrado != null){
            int pos = alunos.indexOf(alunoencontrado);
            alunos.set(pos, aluno);
        }
    }

    public void remove(Aluno aluno){
        alunos.remove(aluno);
    }

    public List<Aluno> getAllObj() {
        return new ArrayList<>(alunos);
    }
}
