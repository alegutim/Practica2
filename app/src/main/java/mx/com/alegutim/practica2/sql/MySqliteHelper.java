package mx.com.alegutim.practica2.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 04/07/2016.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME  = "practica2_msqlite";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME_APP = "APP_TABLE";
    public static final String COLUMN_ID_APP = BaseColumns._ID;
    public static final String COLUMN_APP_TITTLE= "TITTLE";
    public static final String COLUMN_APP_DEVELOPER= "DEVELOPER";
    public static final String COLUMN_APP_DETAIL= "DETAIL";
    public static final String COLUMN_APP_UPDATE= "UPDATED";
    public static final String COLUMN_APP_IMAGE= "IMAGE";

    private static final String CREATE_TABLE_APPS = "create table " + TABLE_NAME_APP
            + " ( " + COLUMN_ID_APP + " integer primary key autoincrement ," +
            COLUMN_APP_TITTLE + " text not null , "+
            COLUMN_APP_DEVELOPER + " text not null , "+
            COLUMN_APP_DETAIL + " text not null, "+
            COLUMN_APP_UPDATE + " integer not null, "+
            COLUMN_APP_IMAGE + " integer not null )";



    public MySqliteHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APPS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
