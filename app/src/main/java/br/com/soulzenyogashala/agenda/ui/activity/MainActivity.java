package br.com.soulzenyogashala.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import br.com.soulzenyogashala.agenda.R;
import br.com.soulzenyogashala.agenda.dao.AlunoDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista de Alunos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton float_button = findViewById(R.id.floatingActionButton);
        float_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaDeCadastro.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AlunoDAO dao = new AlunoDAO();
        final ListView listView = findViewById(R.id.lista);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}
