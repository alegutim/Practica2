package mx.com.alegutim.practica2.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mx.com.alegutim.practica2.R;
import mx.com.alegutim.practica2.adapters.AdapterAppList;
import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.sql.AppDataSource;

/**
 * Created by Administrator on 04/07/2016.
 */
public class FragmentList extends Fragment {
    AppDataSource appDataSource;

    private ListView ltsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appDataSource = new AppDataSource(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ltsView = (ListView) view.findViewById(R.id.ltsView);
        TextView fragment_list_txt = (TextView) view.findViewById(R.id.fragment_list_txt);
        List<itemApp>  modelAppList = appDataSource.getAllItems();
        if ( modelAppList.size()==0){
            fragment_list_txt.setText("No Apps installed");
        } else {
            fragment_list_txt.setText("");
        }
        ltsView.setAdapter(new AdapterAppList(getActivity(), modelAppList));
        return view;
    }
}
