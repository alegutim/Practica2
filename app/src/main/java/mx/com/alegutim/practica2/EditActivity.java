package mx.com.alegutim.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.sql.AppDataSource;

public class EditActivity extends AppCompatActivity {

    private itemApp modelApp;
    private AppDataSource appDataSource;
    private EditText edit_tittle;
    private EditText edit_developer;
    private EditText edit_detail;
    private CheckBox edit_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        appDataSource= new AppDataSource(getApplicationContext());
        edit_tittle = (EditText)findViewById(R.id.edit_tittle);
        edit_developer = (EditText)findViewById(R.id.edit_developer);
        edit_detail = (EditText)findViewById(R.id.edit_detail);
        edit_check = (CheckBox)findViewById(R.id.edit_check);
        modelApp = new itemApp();
        modelApp.id = getIntent().getExtras().getInt("ID");
        modelApp.appTittle = getIntent().getExtras().getString("TITTLE");
        modelApp.appDeveloper = getIntent().getExtras().getString("DEVELOPER");
        modelApp.appDetail = getIntent().getExtras().getString("DETAIL");
        modelApp.image_id = getIntent().getExtras().getInt("IMAGE");
        modelApp.appUpdated = getIntent().getExtras().getBoolean("UPDATE");

        edit_tittle.setText(modelApp.appTittle);
        edit_developer.setText(modelApp.appDeveloper);
        edit_detail.setText(modelApp.appDetail);
        if (modelApp.appUpdated){
            edit_check.setText("Installed");
            edit_check.setChecked(true);
        } else{
            edit_check.setText("Updataed");
            edit_check.setChecked(false);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSave);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
