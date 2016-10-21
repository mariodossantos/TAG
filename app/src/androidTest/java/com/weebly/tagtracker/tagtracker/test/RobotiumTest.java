/*package com.weebly.tagtracker.tagtracker.test;

import com.weebly.taggtracker.tagtracker.TelaInicialActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class RobotiumTest extends ActivityInstrumentationTestCase2<TelaInicialActivity> {
  	private Solo solo;
  	
  	public RobotiumTest() {
		super(TelaInicialActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.weebly.taggtracker.tagtracker.TelaInicialActivity'
		solo.waitForActivity(com.weebly.taggtracker.tagtracker.TelaInicialActivity.class, 2000);
        //Click on ImageView
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.fab));
        //Click on CHECKLISTS
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.btnChecklists));
        //Wait for activity: 'com.weebly.taggtracker.tagtracker.MainActivity'
		assertTrue("com.weebly.taggtracker.tagtracker.MainActivity is not found!", solo.waitForActivity(com.weebly.taggtracker.tagtracker.MainActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.bt_guardar));
        //Set default small timeout to 11731 milliseconds
		Timeout.setSmallTimeout(11731);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'i'
		//solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
		//solo.enterText((android.widget.EditText) solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre), "i");
        //Assert that: 'O título deve ter entre 3 e 30 caracteres!' is shown
		//assertTrue("'O título deve ter entre 3 e 30 caracteres!' is not shown!", solo.waitForText(java.util.regex.Pattern.quote("O título deve ter entre 3 e 30 caracteres!"), 1, 20000, true, true));
        //Click on com.weebly.tagtracker.tagtracker.R.id.et_nombre
		//solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'Titulo'
		solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre), "Titulo");
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.bt_guardar));
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.bt_guardar));
        //Assert that: 'Já existe uma checklist com esse nome salva!' is shown
		assertTrue("'Já existe uma checklist com esse nome salva!' is not shown!", solo.waitForText(java.util.regex.Pattern.quote("Já existe uma checklist com esse nome salva!"), 1, 20000, true, true));
        //Click on Titulo
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.et_nombre));
        //Click on Visualizar minhas Checklists
		solo.clickOnView(solo.getView(com.weebly.taggtracker.tagtracker.R.id.bt_mostrar));
        //Wait for activity: 'com.weebly.taggtracker.tagtracker.Listado'
		assertTrue("com.weebly.taggtracker.tagtracker.Listado is not found!", solo.waitForActivity(com.weebly.taggtracker.tagtracker.Listado.class));
        //Press menu back key
		solo.goBack();
	}
}*/
