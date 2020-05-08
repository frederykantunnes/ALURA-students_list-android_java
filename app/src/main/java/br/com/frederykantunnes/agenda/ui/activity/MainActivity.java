package br.com.frederykantunnes.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import br.com.frederykantunnes.agenda.R;
import br.com.frederykantunnes.agenda.model.Aluno;
import br.com.frederykantunnes.agenda.ui.ListaAlunosView;

public class MainActivity extends AppCompatActivity {

    ListaAlunosView listaAlunosView;
    List<Aluno> alunos;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista de Alunos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaAlunosView = new ListaAlunosView(this);
        alunos = new ArrayList<>();
        listView = findViewById(R.id.lista);
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
        listaAlunosView.updateList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        if(item.getItemId() == R.id.activity_menu_remover){
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }


    public void configuraLista(){

        final List<Aluno> alunos = new ArrayList<>();
        final ListView listView = findViewById(R.id.lista);
        listaAlunosView.getAdapterView(listView);
        listaAlunosView.configurarListenerDeClique(listView, alunos);
        registerForContextMenu(listView);
    }



}
