package mx.com.alegutim.practica2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.com.alegutim.practica2.R;
import mx.com.alegutim.practica2.model.itemApp;

/**
 * Created by Administrator on 04/07/2016.
 */
public class AdapterAppList extends ArrayAdapter<itemApp> {
    private final String url_1 = "http://parentesis.com/imagesPosts/uber-head.jpg";
    private final String url_2 = "https://s3.amazonaws.com/urgeio-versus/whatsapp/front/front-1393846082939.flat.jpg";
    private final String url_3 = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Facebook_icon.svg/2000px-Facebook_icon.svg.png";
    private final String url_4 = "https://lh3.googleusercontent.com/MOf9Kxxkj7GvyZlTZOnUzuYv0JAweEhlxJX6gslQvbvlhLK5_bSTK6duxY2xfbBsj43H=w300";
    private final String url_5 = "http://puntodestino.com.mx/wp-content/uploads/2016/05/TuTag.jpg";


    public AdapterAppList(Context context, List<itemApp> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        }
        TextView row_txttitle = (TextView) convertView.findViewById(R.id.row_txttitle);
        TextView row_txtdeveloper = (TextView) convertView.findViewById(R.id.row_txtdeveloper);
        TextView row_txtstatus = (TextView) convertView.findViewById(R.id.row_txtstatus);
        ImageView row_image = (ImageView) convertView.findViewById(R.id.row_image);
        itemApp modelApp = getItem(position);
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
        //Picasso.with(getContext()).load(id_image).into(row_image);
        row_txttitle.setText(modelApp.appTittle);
        row_txtdeveloper.setText(modelApp.appDeveloper);
        row_txtstatus.setText((modelApp.appUpdated?"Installed":"Update"));


        return convertView;
    }
}
