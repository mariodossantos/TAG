package com.weebly.taggtracker.tagtracker;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class TelaTagsActivity extends ListFragment {
    private DatabaseHelper bd;
    private ArrayAdapter<String> adapter;

    public ArrayAdapter<String> getAdapter(){
        return this.adapter;
    }


    public void instanciaBD(DatabaseHelper bd){
        this.bd = bd;
    }

    public void atualizaAdapter(){
        this.adapter.notifyDataSetChanged();
    }

    @Override
    //Coloca a view do xml definido: tela_checklists
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_checklists, container, false);
    }

    @Override
    //Coloca os dados dp BD no adapter
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //pega os dados das checklists
        ArrayList<String> values = bd.leTags();

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        adapter.notifyDataSetChanged();


        setListAdapter(adapter);
    }

    @Override
    //Faz alguma coisa quando o item é clicado: MELHORAR!!
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
        Toast.makeText(getActivity(), "Item: " + l.getItemAtPosition(position), Toast.LENGTH_SHORT)
                .show();
    }
}






