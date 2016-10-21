package com.weebly.tagtracker.tagtracker.test;

import com.weebly.taggtracker.tagtracker.TelaInicialActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testandoCadastraChecklists extends ActivityInstrumentationTestCase2<TelaInicialActivity> {
  	private Solo solo;
  	
  	public testandoCadastraChecklists() {
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
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.fab));
        //Click on CHECKLISTS
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.btnChecklists));
        //Wait for activity: 'com.weebly.taggtracker.tagtracker.MainActivity'
		assertTrue("com.weebly.taggtracker.tagtracker.MainActivity is not found!", solo.waitForActivity(com.weebly.taggtracker.tagtracker.MainActivity.class));
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_guardar));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'ok'
		solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre), "ok");
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_guardar));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'gifikgdkgylgkdrjzrjsfjstkdlgdksrjsykkxfjjktdrjsktstjgktk'
		solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre), "gifikgdkgylgkdrjzrjsfjstkdlgdksrjsykkxfjjktdrjsktstjgktk");
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_guardar));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'Tagtracker'
		solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre), "Tagtracker");
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_guardar));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
        //Click on Visualizar minhas Checklists
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_mostrar));
        //Wait for activity: 'com.weebly.taggtracker.tagtracker.Listado'
		assertTrue("com.weebly.taggtracker.tagtracker.Listado is not found!", solo.waitForActivity(com.weebly.taggtracker.tagtracker.Listado.class));
        //Scroll to 12 Tagtracker
		android.widget.ListView listView0 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 0);
		solo.scrollListToLine(listView0, 1);
        //Click on 12 Tagtracker
		solo.clickOnView(solo.getView(android.R.id.text1, 10));
        //Press menu back key
		solo.goBack();
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
        //Enter the text: 'Tagtracker'
		solo.clearEditText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre));
		solo.enterText((android.widget.EditText) solo.getView(com.weebly.tagtracker.tagtracker.R.id.et_nombre), "Tagtracker");
        //Click on SALVAR Checklist
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.bt_guardar));
        //Press menu back key
		solo.goBack();
        //Click on RelativeLayout
		solo.clickOnView(solo.getView(com.weebly.tagtracker.tagtracker.R.id.btnFechaMenu));
	}
}
