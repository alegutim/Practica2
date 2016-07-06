package mx.com.alegutim.practica2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.practica2.model.itemApp;

/**
 * Created by Administrator on 04/07/2016.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    public AppDataSource(Context context){
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void  saveItem(itemApp modelApp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_APP_TITTLE,modelApp.appTittle);
        contentValues.put(MySqliteHelper.COLUMN_APP_DEVELOPER,modelApp.appDeveloper);
        contentValues.put(MySqliteHelper.COLUMN_APP_DETAIL,modelApp.appDetail);
        contentValues.put(MySqliteHelper.COLUMN_APP_UPDATE,(modelApp.appUpdated==true?1:0));
        contentValues.put(MySqliteHelper.COLUMN_APP_IMAGE,modelApp.image_id);
        db.insert(MySqliteHelper.TABLE_NAME_APP,null,contentValues);
    }

    public  void deleteItem (itemApp modelApp){
        db.delete(MySqliteHelper.TABLE_NAME_APP,MySqliteHelper.COLUMN_ID_APP + " =? ", new String[]{String.valueOf(modelApp.id)});
    }

    public  void saveUtlConexion (itemApp modelApp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_APP_UPDATE,modelApp.appUpdated==true?1:0);
        db.update(MySqliteHelper.TABLE_NAME_APP,contentValues,MySqliteHelper.COLUMN_ID_APP + " =? ", new String[]{String.valueOf(modelApp.id)});
    }


    public List<itemApp> getAllItems(){
        List<itemApp> modelAppList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME_APP,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID_APP));
            String appTittle = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_TITTLE));
            String appDeveloper = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_DEVELOPER));
            String appDetail = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_DETAIL));
            String appUpdated = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_UPDATE));
            String appImage = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_APP_IMAGE));
            itemApp modelApp = new itemApp();
            modelApp.id=id;
            modelApp.appTittle=appTittle;
            modelApp.appDeveloper=appDeveloper;
            modelApp.appDetail=appDetail;
            modelApp.appUpdated=(Integer.parseInt(appUpdated)==1?true:false);
            modelApp.image_id=Integer.parseInt(appImage);
            modelAppList.add(modelApp);
        }
        return modelAppList;
    }

}
