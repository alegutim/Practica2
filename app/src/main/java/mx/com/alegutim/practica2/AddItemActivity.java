package mx.com.alegutim.practica2;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import mx.com.alegutim.practica2.adapters.AdapterAppList;
import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.sql.AppDataSource;

public class AddItemActivity extends AppCompatActivity {

    private EditText add_txtname;
    private EditText add_txtdeveloper;
    private EditText add_txtdetail;
    private CheckBox add_chk_install_update;
    private final int random_image= 1 + (int)(Math.random() * 5);
    private AppDataSource appDataSource;
    protected Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getResources();
        appDataSource = new AppDataSource(getApplicationContext());
        setContentView(R.layout.activity_add_item);
        add_txtname = (EditText) findViewById(R.id.add_txtname);
        add_txtdeveloper = (EditText) findViewById(R.id.add_txtdeveloper);
        add_txtdetail = (EditText) findViewById(R.id.add_txtdetail);
        add_chk_install_update = (CheckBox) findViewById(R.id.add_chk_install_update);
        ImageView add_img = (ImageView) findViewById(R.id.add_img);
        String img_url = "";
        switch (random_image){
            case 1:
                img_url= AdapterAppList.url_1;
                break;
            case 2:
                img_url=AdapterAppList.url_2;
                break;
            case 3:
                img_url=AdapterAppList.url_3;
                break;
            case 4:
                img_url=AdapterAppList.url_4;
                break;
            case 5:
                img_url=AdapterAppList.url_5;
                break;
        }

        Picasso.with(getApplicationContext()).load(img_url).into(add_img);
        add_chk_install_update.setText(res.getString(R.string.additem_checked_notok));
        add_chk_install_update.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(add_chk_install_update.isChecked()){
                    add_chk_install_update.setText(res.getString(R.string.additem_checked_ok));
                } else{
                    add_chk_install_update.setText(res.getString(R.string.additem_checked_notok));
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSave);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        findViewById(R.id.add_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemApp modelApp = new itemApp();
                modelApp.appTittle = add_txtname.getText().toString();
                modelApp.appDeveloper = add_txtdeveloper.getText().toString();
                modelApp.appDetail = add_txtdetail.getText().toString();
                modelApp.appUpdated = add_chk_install_update.isChecked();
                modelApp.image_id = random_image;
                appDataSource.saveItem(modelApp);
                setResult(RESULT_OK,new Intent());
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
