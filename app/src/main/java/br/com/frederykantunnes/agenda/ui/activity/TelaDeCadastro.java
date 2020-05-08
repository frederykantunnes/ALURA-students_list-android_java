package br.com.frederykantunnes.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.frederykantunnes.agenda.R;
import br.com.frederykantunnes.agenda.dao.AlunoDAO;
import br.com.frederykantunnes.agenda.model.Aluno;

public class TelaDeCadastro extends AppCompatActivity {

    EditText camponome, campotelefone, campoemail;
    Button salvar;
    AlunoDAO dao;
    Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastrar Novo Aluno");
        setContentView(R.layout.activity_tela_de_cadastro);
        dao = new AlunoDAO();
        inicializaViews();
        configuraBotaoSalvar();

//        registerForContextMenu(this);

        Intent dados = getIntent();
        if (dados.hasExtra("aluno")){
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            assert aluno != null;
            camponome.setText(aluno.getNome());
            campoemail.setText(aluno.getEmail());
            campotelefone.setText(aluno.getTelefone());
        }else{
            aluno = new Aluno();
        }
    }
    public void inicializaViews(){
        camponome = findViewById(R.id.nome);
        campotelefone = findViewById(R.id.telefone);
        campoemail = findViewById(R.id.email);
        salvar = findViewById(R.id.btn_salvar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.activity_menu_salvar){
            salvar();
        }
        return super.onOptionsItemSelected(item);
    }

    public void configuraBotaoSalvar(){
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    public void salvar(){
        if (aluno.getNome()==null){
            Aluno aluno = new Aluno(camponome.getText().toString(), campotelefone.getText().toString(), campoemail.getText().toString());
            dao.save(aluno);
        }else{
            aluno.setNome(camponome.getText().toString());
            aluno.setEmail(campoemail.getText().toString());
            aluno.setTelefone(campotelefone.getText().toString());
            dao.edit(aluno);
        }
        finish();
    }
}
