package br.com.frederykantunnes.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import java.util.List;
import br.com.frederykantunnes.agenda.database.AgendaDatabase;
import br.com.frederykantunnes.agenda.database.dao.RoomAlunoDAO;
import br.com.frederykantunnes.agenda.model.Aluno;
import br.com.frederykantunnes.agenda.ui.activity.TelaDeCadastro;
import br.com.frederykantunnes.agenda.ui.adapter.ListAlunosAdapter;

public class ListaAlunosView {
    private Context context;
    private ListAlunosAdapter adapter;
    private RoomAlunoDAO dao;


    public ListaAlunosView(Context context) {
        this.context = context;
        dao =  AgendaDatabase.getInstance(context).getRoomAlunoDAO();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Excluir")
                .setMessage("Deseja excluir o aluno selecionado?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno aluno = (Aluno) adapter.getItem(menuInfo.position);
                        dao.remove(aluno);
                        adapter.remove(aluno);
                    }
                })
                .setNegativeButton("Não", null)
                .show()
        ;
    }

    public void getAdapterView(ListView listView) {
        adapter = new ListAlunosAdapter(context);
        listView.setAdapter(adapter);
    }

    public void configurarListenerDeClique(final ListView listView, List<Aluno> alunos){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiparaformulario = new Intent(context, TelaDeCadastro.class);
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                vaiparaformulario.putExtra("aluno", aluno);
                context.startActivity(vaiparaformulario);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }


    public void updateList() {
        adapter.updateList(dao.getAll());
    }
}
