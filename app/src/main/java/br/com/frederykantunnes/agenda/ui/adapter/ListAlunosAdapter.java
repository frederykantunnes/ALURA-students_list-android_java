package br.com.frederykantunnes.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.agenda.R;
import br.com.frederykantunnes.agenda.model.Aluno;

public class ListAlunosAdapter extends BaseAdapter {

    private List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = getInflate(parent);
        setAlunoView(position, view);
        return view;
    }

    private void setAlunoView(int position, View view) {
        TextView nome =  view.findViewById(R.id.item_aluno_nome);
        TextView telefone =  view.findViewById(R.id.item_aluno_telefone);
        nome.setText(alunos.get(position).getNome());
        telefone.setText(alunos.get(position).getTelefone());
    }

    private View getInflate(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno_list, parent, false);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    private void clear() {
        alunos.clear();
    }

    private void addAll(List<Aluno> todos) {
        alunos.addAll(todos);
    }


    public void updateList(List<Aluno> todos){
        clear();
        addAll(todos);
        notifyDataSetChanged();
    }
}
