package mx.com.alegutim.practica2.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import mx.com.alegutim.practica2.DetailActivity;
import mx.com.alegutim.practica2.MainActivity;
import mx.com.alegutim.practica2.R;

/**
 * Created by Administrator on 06/07/2016.
 */
public class ServiceUpdating extends Service {
    private MyAsyncTask myAsyncTask;
    private int id;
    protected Resources res = getResources();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getExtras()!=null && intent.getExtras().containsKey("key_id")){
            id = intent.getExtras().getInt("key_id");
        }


        if (myAsyncTask==null){
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
        }

        return START_STICKY;
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Boolean> {
        private NotificationCompat.Builder mNotif;

        @Override
        protected void onPreExecute() {

            mNotif = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle(res.getString(R.string.serviceUpdating_tittle))
                    .setContentText(res.getString(R.string.serviceUpdating_text))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_navigation_more_vert))
                    .setSmallIcon(android.R.drawable.ic_dialog_email);
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (int i = 0; i < 3; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            mNotif.setProgress(10, values[0], false);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, mNotif.build());

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                // eliminar progreso
                mNotif.setProgress(0, 0, false);
                mNotif.setContentTitle(res.getString(R.string.serviceUpdating_tittle_final));
                mNotif.setContentText(res.getString(R.string.serviceUpdating_text_final));
                mNotif.setContentInfo(res.getString(R.string.serviceUpdating_info_final));
                mNotif.setAutoCancel(true);
                myAsyncTask = null;
                stopSelf();
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(id,mNotif.build());

            }
        }
    }
}