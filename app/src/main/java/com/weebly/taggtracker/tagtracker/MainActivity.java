package com.weebly.taggtracker.tagtracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre;
    Button bt_guardar, bt_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_nombre = (EditText) findViewById(R.id.et_nombre);


        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);

        bt_guardar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Comportamento para salvar a tag
                View btnSalvar = findViewById(R.id.bt_guardar);
                final EditText txtTitulo = (EditText ) findViewById(R.id.et_nombre);
                btnSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Verifica se vazio
                        if (txtTitulo.getText().toString().isEmpty()){
                            txtTitulo.setError("Digite um título!");
                            txtTitulo.setText("");
                            return;
                        }
                        //Verifica se maior que 3  30 caracteres
                        if (txtTitulo.getText().length() < 3 || txtTitulo.getText().length() > 30){
                            txtTitulo.setError("O título deve ter entre 3 e 30 caracteres!");
                            txtTitulo.setText("");
                            return;
                        }
                        //Verifica se existe checklist igual
                        ArrayList<String> listaTotal = leChecklist();

                        if (listaTotal.contains(txtTitulo.getText().toString())){
                            txtTitulo.setError("Já existe uma checklist com esse nome salva!");
                            txtTitulo.setText("");
                            return;
                        }

                            guardar(et_nombre.getText().toString());
                            txtTitulo.setText("");
                    }
                });

            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });
    }

    private void guardar(String Nombre){
        BaseHelper helper = new BaseHelper(this, "Demo", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);

            db.insert("PERSONAS",null, c);
            db.close();
            Toast.makeText(this,"CheckList Inserida com sucesso ",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"ERROR" +e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    public ArrayList<String> leChecklist(){
        ArrayList<String> resp = new ArrayList<String>();

        BaseHelper helper = new BaseHelper(this, "Demo", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select nombre from personas", null);

        if(cursor.moveToFirst()) {
            do {
                String linea = cursor.getString(0);
                resp.add(linea);
            } while (cursor.moveToNext());
        }
        db.close();
        return resp;
    }
}
