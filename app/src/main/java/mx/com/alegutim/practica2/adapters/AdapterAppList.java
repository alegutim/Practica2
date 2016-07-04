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
    private final String url_1 = "https://mir-s3-cdn-cf.behance.net/projects/202/12792733.54832468a07db.jpg";
    private final String url_2 = "http://laeconomia.com.mx/wp-content/uploads/pase-urbano.png";


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
        Picasso.with(getContext()).load(modelApp.image_id==0?url_1:url_2).into(row_image);
        row_txttitle.setText(modelApp.appTittle);
        row_txtdeveloper.setText(modelApp.appDeveloper);
        row_txtstatus.setText((String.valueOf(modelApp.appUpdated)));


        return convertView;
    }
}
