package mx.com.alegutim.practica2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mx.com.alegutim.practica2.adapters.AdapterAppList;
import mx.com.alegutim.practica2.fragment.FragmentList;
import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.service.ServiceNotification;
import mx.com.alegutim.practica2.service.ServiceUpdating;
import mx.com.alegutim.practica2.sql.AppDataSource;

public class DetailActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT_ACTIVITY =1;
    private final String url= "https://www.google.com.mx/";
    private ImageView detail_img_app;
    private TextView detail_tittle_app;
    private TextView detail_developer_app;
    private TextView detail_detail_app;
    private Button detail_btn_update;
    private itemApp modelApp;
    AppDataSource appDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        appDataSource= new AppDataSource(getApplicationContext());
        detail_img_app = (ImageView)findViewById(R.id.detail_img_app);
        detail_tittle_app = (TextView)findViewById(R.id.detail_tittle_app);
        detail_developer_app = (TextView)findViewById(R.id.detail_developer_app);
        detail_detail_app = (TextView)findViewById(R.id.detail_detail_app);
        detail_btn_update = (Button)findViewById(R.id.detail_btn_update);
        LoadInfo(getIntent());


        detail_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniServiceUpdate();
                detail_btn_update.setText(getResources().getString(R.string.adapter_intalled));
                detail_btn_update.setEnabled(false);
                modelApp.appUpdated=true;
                appDataSource.saveChangeUpdate(modelApp);
                Intent i = new Intent();

                setResult(RESULT_OK,new Intent());
                finish();
            }
        });



        findViewById(R.id.detail_btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
            }
        });

        findViewById(R.id.detail_btn_drop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailActivity.this)
                        .setTitle(getResources().getString(R.string.adapter_alert_dialog))
                        .setMessage(String.format(getResources().getString(R.string.adapter_alert_message),modelApp.appTittle))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                iniService();
                               appDataSource.deleteItem(modelApp);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).create().show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSave);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_detail_edit:
                Intent i = new Intent(getApplicationContext(),EditActivity.class);
                i.putExtra("ID",modelApp.id);
                i.putExtra("TITTLE",modelApp.appTittle);
                i.putExtra("DEVELOPER",modelApp.appDeveloper);
                i.putExtra("DETAIL",modelApp.appDetail);
                i.putExtra("IMAGE",modelApp.image_id);
                i.putExtra("UPDATE",modelApp.appUpdated);
                startActivityForResult(i,REQUEST_CODE_EDIT_ACTIVITY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void iniService(){
        startService((new Intent(getApplicationContext(), ServiceNotification.class)));
    }


    protected void iniServiceUpdate(){
        startService((new Intent(getApplicationContext(), ServiceUpdating.class)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_activity,menu);
        return true;
    }

    protected void LoadInfo(Intent intent){
        modelApp = new itemApp();
        modelApp.id = intent.getExtras().getInt("ID");
        modelApp.appTittle = intent.getExtras().getString("TITTLE");
        modelApp.appDeveloper = intent.getExtras().getString("DEVELOPER");
        modelApp.appDetail = intent.getExtras().getString("DETAIL");
        modelApp.image_id = intent.getExtras().getInt("IMAGE");
        modelApp.appUpdated = intent.getExtras().getBoolean("UPDATE");

        detail_tittle_app.setText(modelApp.appTittle);
        detail_developer_app.setText(modelApp.appDeveloper);
        detail_detail_app.setText(modelApp.appDetail);
        String id_image = "";
        switch (modelApp.image_id){
            case 1:
                id_image= AdapterAppList.url_1;
                break;
            case 2:
                id_image=AdapterAppList.url_2;
                break;
            case 3:
                id_image=AdapterAppList.url_3;
                break;
            case 4:
                id_image=AdapterAppList.url_4;
                break;
            case 5:
                id_image=AdapterAppList.url_5;
                break;
        }
        Picasso.with(getApplicationContext()).load(id_image).into(detail_img_app);
        if (modelApp.appUpdated){
            detail_btn_update.setText(getResources().getString(R.string.additem_checked_ok));
            detail_btn_update.setEnabled(false);
        } else{
            detail_btn_update.setText(getResources().getString(R.string.additem_checked_notok));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE_EDIT_ACTIVITY==requestCode && resultCode==RESULT_OK){
            LoadInfo(data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
