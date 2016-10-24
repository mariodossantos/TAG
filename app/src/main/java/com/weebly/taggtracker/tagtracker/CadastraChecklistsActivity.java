package com.weebly.taggtracker.tagtracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class CadastraChecklistsActivity extends AppCompatActivity {
    DatabaseHelper bd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public CadastraChecklistsActivity() {
        bd = new DatabaseHelper(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_checklists);


        //Comportamento para salvar a checklist
        View btnSalvar = findViewById(R.id.btnSalvarChecklist);
        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText txtTitulo = (EditText) findViewById(R.id.txtTitleChecklist);

                //Verifica se vazio
                if (txtTitulo.getText().toString().isEmpty()) {
                    txtTitulo.setError("Digite um título!");
                    txtTitulo.setText("");
                    return;
                }
                //Verifica se maior que 3  30 caracteres
                if (txtTitulo.getText().length() < 3 || txtTitulo.getText().length() > 30) {
                    txtTitulo.setError("O título deve ter entre 3 e 30 caracteres!");
                    txtTitulo.setText("");
                    return;
                }
                //Verifica se existe checklist igual
                ArrayList<String> listaTotal = bd.leChecklist();

                if (listaTotal.contains(txtTitulo.getText().toString())) {
                    txtTitulo.setError("Já existe uma checklist com esse nome salva!");
                    txtTitulo.setText("");
                    return;
                }

                if (bd.insereChecklist(txtTitulo.getText().toString())) {
                    //Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, getIntent());
                    finish();
                }
                txtTitulo.setText("");
            }
        });

        //Comportamento para cancelar salvar a tag
        View btnCancelar = findViewById(R.id.btnCancelarChecklist);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CadastraChecklists Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
