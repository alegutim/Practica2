package mx.com.alegutim.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import mx.com.alegutim.practica2.fragment.FragmentList;
import mx.com.alegutim.practica2.sql.AppDataSource;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_ACTIVITY =1;
    private AppDataSource appDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDataSource = new AppDataSource(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSave);
        setSupportActionBar(toolbar);

                getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList() ).commit();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_main_add:
                startActivityForResult(new Intent(getApplicationContext(),AddItemActivity.class)
                ,REQUEST_CODE_ADD_ACTIVITY);
                return true;
            case R.id.menu_main_delete_all:
                appDataSource.deleteAll();
                getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_ADD_ACTIVITY==requestCode && resultCode==RESULT_OK){
            getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
