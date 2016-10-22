package com.weebly.taggtracker.tagtracker;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by Barbara on 19/10/2016.
 */

public class CadastraTags extends AppCompatActivity{
    Dialog dialog;
    BancoDeDados bd;

    CadastraTags(BancoDeDados bd){
        this.bd = bd;
    }

    public void onCreate(){

    }

    public void salvaTag(final View view){
        dialog = new Dialog(CadastraTags.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_cadastrar_tags);


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();

        param.gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(false);


        //Comportamento para salvar a tag
        View btnSalvar = dialog.findViewById(R.id.btnSalvarTag);
        final EditText txtTitulo = (EditText ) findViewById(R.id.txtTitleTag);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtTitulo.toString().isEmpty()){
                    txtTitulo.setError("Digite um título!");
                    return;
                } else {
                    bd.insereTags(bd.retornaProxIDTags(), txtTitulo.toString());
                }
            }
        });

        //Comportamento para cancelar salvar a tag
        View btnCancelar = dialog.findViewById(R.id.btnCancelartag);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dialog.cancel();
                return;
            }
        } );
    }



}
