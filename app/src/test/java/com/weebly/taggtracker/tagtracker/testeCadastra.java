package com.weebly.taggtracker.tagtracker;

import android.database.ContentObservable;
import android.widget.EditText;

import junit.framework.TestCase;
import junit.framework.TestCase;

import org.junit.Test;


/**
 * Created by marioamigos on 24/10/2016.
 */

public class testeCadastra extends TestCase {

    public void testeTentaInserirValorValido() {
        DatabaseHelper d =  new DatabaseHelper(null);
        boolean resultado = d.insereChecklist("Lista Academia");
        assertTrue(resultado);
    }

    public void testeTentaInserirValorPequeno() {
        DatabaseHelper e =  new DatabaseHelper(null);
        boolean resultado = e.insereChecklist("Li");
        assertFalse(resultado);
    }

    public void testeTentaInserirValorGrande(){
        DatabaseHelper f = new DatabaseHelper(null);
        boolean resultado = f.insereChecklist("12345678901234567890123456789012345667");
        assertTrue(resultado);

    }

    public void testeValorVazio(){
        DatabaseHelper g = new DatabaseHelper(null);
        boolean resultado = g.insereChecklist("");
        assertTrue(resultado);
    }

}
