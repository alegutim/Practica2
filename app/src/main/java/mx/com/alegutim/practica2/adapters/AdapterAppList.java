package mx.com.alegutim.practica2.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.com.alegutim.practica2.R;
import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.sql.AppDataSource;

/**
 * Created by Administrator on 04/07/2016.
 */
public class AdapterAppList extends ArrayAdapter<itemApp> {
    public static final String url_1 = "http://parentesis.com/imagesPosts/uber-head.jpg";
    public static final String url_2 = "https://s3.amazonaws.com/urgeio-versus/whatsapp/front/front-1393846082939.flat.jpg";
    public static final String url_3 = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Facebook_icon.svg/2000px-Facebook_icon.svg.png";
    public static final String url_4 = "https://lh3.googleusercontent.com/MOf9Kxxkj7GvyZlTZOnUzuYv0JAweEhlxJX6gslQvbvlhLK5_bSTK6duxY2xfbBsj43H=w300";
    public static final String url_5 = "http://puntodestino.com.mx/wp-content/uploads/2016/05/TuTag.jpg";
    private ViewHolder viewHolder;
    private itemApp modelApp;
    private AppDataSource appDataSource;

    public AdapterAppList(Context context, List<itemApp> objects) {
        super(context, 0, objects);
        appDataSource = new AppDataSource(getContext());
    }

    @Override
    public View getView(final int position,  View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater  = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list,null);
            viewHolder = new ViewHolder();
            viewHolder.row_txttitle = (TextView) convertView.findViewById(R.id.row_txttitle);
            viewHolder.row_txtdeveloper= (TextView) convertView.findViewById(R.id.row_txtdeveloper);
            viewHolder.row_txtstatus= (TextView) convertView.findViewById(R.id.row_txtstatus);
            viewHolder.row_image = (ImageView) convertView.findViewById(R.id.row_image);
            viewHolder.row_image_button = (ImageView) convertView.findViewById(R.id.row_image_button);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        modelApp = getItem(position);
        String id_image = "";
        switch (modelApp.image_id){
            case 1:
                id_image=url_1;
                break;
            case 2:
                id_image=url_2;
                break;
            case 3:
                id_image=url_3;
                break;
            case 4:
                id_image=url_4;
                break;
            case 5:
                id_image=url_5;
                break;
        }
        Picasso.with(getContext()).load(id_image).into(viewHolder.row_image);
        viewHolder.row_txttitle.setText(modelApp.appTittle);
        viewHolder.row_txtdeveloper.setText(modelApp.appDeveloper);
        viewHolder.row_txtstatus.setText((modelApp.appUpdated?"Installed":"Update"));
        viewHolder.row_image_button.setClickable(true);
        viewHolder.row_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Borrar App")
                        .setMessage(String.format("¿Desea borrar la App %s?",viewHolder.row_txttitle.getText()))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appDataSource.deleteItem(modelApp);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).create().show();
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView row_image;
        TextView row_txttitle;
        ImageView row_image_button;
        TextView row_txtdeveloper;
        TextView row_txtstatus;

    }
}
