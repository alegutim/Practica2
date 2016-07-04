package mx.com.alegutim.practica2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mx.com.alegutim.practica2.fragment.FragmentList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_ACTIVITY =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSave);
        setSupportActionBar(toolbar);
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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_ADD_ACTIVITY==requestCode && requestCode==RESULT_OK){
            getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentList()).commit();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}