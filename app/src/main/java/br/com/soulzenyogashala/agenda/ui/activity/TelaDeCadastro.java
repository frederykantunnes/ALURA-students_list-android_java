package br.com.soulzenyogashala.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.soulzenyogashala.agenda.R;
import br.com.soulzenyogashala.agenda.dao.AlunoDAO;
import br.com.soulzenyogashala.agenda.model.Aluno;

public class TelaDeCadastro extends AppCompatActivity {

    EditText camponome, campotelefone, campoemail;
    Button salvar;
    AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastrar Novo Aluno");
        setContentView(R.layout.activity_tela_de_cadastro);
        dao = new AlunoDAO();
        inicializaViews();
        configuraBotaoSalvar();

    }
    public void inicializaViews(){
        camponome = findViewById(R.id.nome);
        campotelefone = findViewById(R.id.telefone);
        campoemail = findViewById(R.id.email);
        salvar = findViewById(R.id.btn_salvar);
    }

    public void configuraBotaoSalvar(){
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno(camponome.getText().toString(), campotelefone.getText().toString(), campoemail.getText().toString());
                dao.save(aluno);
                finish();
            }
        });
    }
}
