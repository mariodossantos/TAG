package com.weebly.taggtracker.tagtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Barbara on 23/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TAGTRACKER.db";
    public Context contexto;

    public static abstract class tabelaChecklists implements BaseColumns {
        public static final String nomeTabela = "checklists";
        public static final String colunaID = "ID";
        public static final String colunaTitulo = "titulo";
    }

    public static abstract class tabelaTags implements BaseColumns {
        public static final String nomeTabela = "tags";
        public static final String colunaID = "ID";
        public static final String colunaTitulo = "titulo";
    }

    public static abstract class tabelaAssocia implements BaseColumns {
        public static final String nomeTabela = "associa";
        public static final String checklistsID = "checklistsID";
        public static final String tagsID = "tagsID";
    }


    private static final String SQL_CREATE_TABLE1 =
            "CREATE TABLE IF NOT EXISTS " + tabelaTags.nomeTabela + " (" +
                    tabelaTags.colunaID + " INTEGER PRIMARY KEY, " +
                    tabelaTags.colunaTitulo + " TEXT not null ); ";

    private static final String SQL_CREATE_TABLE2 =
                    "CREATE TABLE IF NOT EXISTS " + tabelaChecklists.nomeTabela + " (" +
                    tabelaChecklists.colunaID + " INTEGER PRIMARY KEY, " +
                    tabelaChecklists.colunaTitulo + " TEXT not null);" ;

    private static final String SQL_CREATE_TABLE3 =
                    "CREATE TABLE IF NOT EXISTS " + tabelaAssocia.nomeTabela + " ( " +
                    tabelaAssocia.tagsID + " INTEGER not null, " +
                    tabelaAssocia.checklistsID + " INTEGER not null, " +
                    "FOREIGN KEY (" + tabelaAssocia.tagsID + ") REFERENCES " +
                            tabelaTags.nomeTabela + "( " + tabelaTags.colunaID + "), " +
                    "FOREIGN KEY (" + tabelaAssocia.checklistsID + ") REFERENCES " +
                            tabelaChecklists.nomeTabela + "(" + tabelaChecklists.colunaID + "))";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + tabelaAssocia.nomeTabela + "; " +
                    "DROP TABLE IF EXISTS " + tabelaChecklists.nomeTabela + "; " +
                    "DROP TABLE IF EXISTS " + tabelaTags.nomeTabela;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.contexto = context;
    }

    public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_TABLE1);
            db.execSQL(SQL_CREATE_TABLE2);
            db.execSQL(SQL_CREATE_TABLE3);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }




    //INSERÇÃO
    public boolean insereChecklist(String titulo) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(tabelaChecklists.colunaID, String.valueOf(retornaProxIDChecklists()));
            values.put(tabelaChecklists.colunaTitulo, titulo);

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    tabelaChecklists.nomeTabela,
                    null,
                    values);
            db.close();
            Toast.makeText(contexto, R.string.checklist_inserted,Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(contexto, R.string.checklist_notinserted,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean insereTags(String titulo) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(tabelaTags.colunaID, retornaProxIDTags());
            values.put(tabelaTags.colunaTitulo, titulo);

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    tabelaTags.nomeTabela,
                    null,
                    values);
            db.close();
            Toast.makeText(contexto,R.string.tag_inserted,Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(contexto,R.string.tag_notinserted,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void insereAssocia(int idChecklists, int idTags) {
        try {

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(tabelaAssocia.checklistsID, idChecklists);
            values.put(tabelaAssocia.tagsID, idTags);

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = getWritableDatabase().insert(
                    tabelaAssocia.nomeTabela,
                    null,
                    values);
        }  catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    //LEITURA
    public ArrayList<String> leChecklist(){
        ArrayList<String> resp = new ArrayList<String>();

        try {
            Cursor cursor = getReadableDatabase().rawQuery("select * from " + tabelaChecklists.nomeTabela, null);

            if (cursor.moveToFirst()) {
                do {
                    String linea = "[" + cursor.getInt(0) + "] " + cursor.getString(1);
                    resp.add(linea);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }  catch (Exception e){
            Toast.makeText(contexto,R.string.erro_exibirChecklist,Toast.LENGTH_SHORT).show();
        }

        return resp;
    }

    public ArrayList<String> leTags(){
        ArrayList<String> resp = new ArrayList<String>();

        try {
            Cursor cursor = getReadableDatabase().rawQuery("select * from " + tabelaTags.nomeTabela, null);

            if (cursor.moveToFirst()) {
                do {
                    String linea = "<" + cursor.getInt(0) + "> " + cursor.getString(1);
                    resp.add(linea);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e){
            Toast.makeText(contexto,R.string.erro_exibirTags,Toast.LENGTH_SHORT).show();
        }

        return resp;
    }

    public ArrayList<String> leItensListas(String idChecklist) {
        ArrayList<String> resp = new ArrayList<String>();

        try {
            Cursor cursor = getReadableDatabase().rawQuery(" select " + tabelaTags.colunaTitulo + " from " + tabelaTags.nomeTabela +
                    " where " + tabelaTags.colunaID + " in ( select " + tabelaAssocia.tagsID +
                    " from " + tabelaAssocia.nomeTabela + " as a  where " + tabelaAssocia.checklistsID + " = " +
                    idChecklist, null);

            if (cursor.moveToFirst()) {
                do {
                    String linea = cursor.getString(0);
                    resp.add(linea);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }  catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return resp;
    }

    //REMOÇÃO
    public void deletaAssocia(int checklistsID, int tagsID){

        if (tagsID != 0 && checklistsID != 0){
            // Define 'where' part of query.
            String selection = tabelaAssocia.checklistsID + " = ? and " +
                    tabelaAssocia.tagsID + " = ? ";
            // Specify arguments in placeholder order.
            String[] selectionArgs = {String.valueOf(checklistsID), String.valueOf(tagsID)};
            // Issue SQL statement.
            getWritableDatabase().delete(tabelaAssocia.nomeTabela, selection, selectionArgs);
        } else if (tagsID == 0) {
            String selection2 = tabelaAssocia.checklistsID + " = ?";
            String[] selectionArgs2 = { String.valueOf(checklistsID) };
            getWritableDatabase().delete(tabelaAssocia.nomeTabela, selection2, selectionArgs2);
        } else if (checklistsID == 0){
            String selection3 = tabelaAssocia.tagsID + " = ?";
            String[] selectionArgs3 = { String.valueOf(tagsID) };
            getWritableDatabase().delete(tabelaAssocia.nomeTabela, selection3, selectionArgs3);
        }
    }

    public void deletaChecklists(int checklistsID){
        try {
            // Define 'where' part of query.
            String selection = tabelaChecklists.colunaID + " = ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = {String.valueOf(checklistsID)};
            // Issue SQL statement.
            deletaAssocia(checklistsID, 0);
            getWritableDatabase().delete(tabelaChecklists.nomeTabela, selection, selectionArgs);
        }  catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void deletaTags(int tagsID){
        try {
            // Define 'where' part of query.
            String selection = tabelaTags.colunaID + " = ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = {String.valueOf(tagsID)};
            // Issue SQL statement.
            deletaAssocia(0, tagsID);
            getWritableDatabase().delete(tabelaTags.nomeTabela, selection, selectionArgs);
        }  catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    //ATUALIZAÇÃO
    public void atualizaChecklists(String titulo, int checklistsID) {
        try {
            // New value for one column
            ContentValues values = new ContentValues();
            values.put(tabelaChecklists.colunaTitulo, titulo);

            // Which row to update, based on the ID
            String selection = tabelaChecklists.colunaID + " = ?";
            String[] selectionArgs = {String.valueOf(checklistsID)};

            int count = getWritableDatabase().update(
                    tabelaChecklists.nomeTabela,
                    values,
                    selection,
                    selectionArgs);
        } catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void atualizatags(String titulo, int tagsID) {
        try {
            // New value for one column
            ContentValues values = new ContentValues();
            values.put(tabelaTags.colunaTitulo, titulo);

            // Which row to update, based on the ID
            String selection = tabelaTags.colunaID + " = ?";
            String[] selectionArgs = {String.valueOf(tagsID)};

            int count = getWritableDatabase().update(
                    tabelaTags.nomeTabela,
                    values,
                    selection,
                    selectionArgs);
        }  catch (Exception e){
            Toast.makeText(contexto,"ERROR" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    //BUSCA
    public int retornaProxIDTags(){
        int id = 0;
        try {
            Cursor cursor = getReadableDatabase().rawQuery("select coalesce( max( " + tabelaTags.colunaID
                                        + " ), 0) from " + tabelaTags.nomeTabela, null);

            cursor.moveToFirst();
            id = cursor.getInt(0);

            cursor.close();
        }  catch (Exception e){}
        return id + 1;
    }

    public int retornaProxIDChecklists(){
        int id = 0;
        try {
            Cursor cursor = getReadableDatabase().rawQuery("select coalesce( max( id ), 0) from "
                    + tabelaChecklists.nomeTabela, null);

            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
        }  catch (Exception e){}
        return id + 1;
    }


}


