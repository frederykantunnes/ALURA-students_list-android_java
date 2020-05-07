package br.com.soulzenyogashala.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.soulzenyogashala.agenda.R;
import br.com.soulzenyogashala.agenda.dao.AlunoDAO;
import br.com.soulzenyogashala.agenda.model.Aluno;

public class MainActivity extends AppCompatActivity {

    AlunoDAO dao;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista de Alunos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new AlunoDAO();

        for (int i = 0; i < 10; i++) {
            dao.save(new Aluno("fred", "12", "asdasd"));
            dao.save(new Aluno("frederyk", "12", "asdasd"));
        }


        FloatingActionButton float_button = findViewById(R.id.floatingActionButton);
        float_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaDeCadastro.class);
                startActivity(intent);
            }
        });

        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }


    public void configuraLista(){
        final List<Aluno> alunos = new ArrayList<>();
        final ListView listView = findViewById(R.id.lista);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos );
        listView.setAdapter(adapter);
        configuraListnerdeClique(listView, alunos);
        registerForContextMenu(listView);
    }
    public void configuraListnerdeClique(final ListView listView, final List<Aluno> alunos){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiparaformulario = new Intent(MainActivity.this, TelaDeCadastro.class);
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                vaiparaformulario.putExtra("aluno", aluno);
                startActivity(vaiparaformulario);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.activity_menu_remover){
            Toast.makeText(this, "Remove", Toast.LENGTH_LONG).show();
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = (Aluno) adapter.getItem(menuInfo.position);
            dao.remove(aluno);
            adapter.remove(aluno);
        }
        return super.onContextItemSelected(item);
    }
}
