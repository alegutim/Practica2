package mx.com.alegutim.practica2.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import mx.com.alegutim.practica2.DetailActivity;
import mx.com.alegutim.practica2.R;
import mx.com.alegutim.practica2.adapters.AdapterAppList;
import mx.com.alegutim.practica2.model.itemApp;
import mx.com.alegutim.practica2.sql.AppDataSource;

/**
 * Created by Administrator on 04/07/2016.
 */
public class FragmentList extends Fragment implements AdapterView.OnItemClickListener {
    AppDataSource appDataSource;
    private ListView  listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDataSource = new AppDataSource(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.ltsView);
        TextView fragment_list_txt = (TextView) view.findViewById(R.id.fragment_list_txt);
        List<itemApp> modelAppList = appDataSource.getAllItems();
        listView.setAdapter(new AdapterAppList(getActivity(), modelAppList));
        if ( modelAppList.size()==0){
            fragment_list_txt.setText("No Apps installed");
        } else {
            fragment_list_txt.setText("");
        }

        listView.setOnItemClickListener(this);




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterAppList adapter = (AdapterAppList) parent.getAdapter();
                final itemApp modelApp = adapter.getItem(position);

                new AlertDialog.Builder(getActivity())
                        .setTitle("Borrar App")
                        .setMessage(String.format("¿Desea borrar la App %s?",modelApp.appTittle))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appDataSource.deleteItem(modelApp);
                                listView.setAdapter(new AdapterAppList(getActivity(),appDataSource.getAllItems()));
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).create().show();

                return true;
            }
        });



        return view;
    }

    private void trueAcces(itemApp modelApp) {
        startActivity(new Intent(getActivity(), DetailActivity.class));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.ltsView:
                AdapterAppList adapter = (AdapterAppList) parent.getAdapter();
                itemApp modelApp = adapter.getItem(position);
                trueAcces(modelApp);
                break;
        }

    }
}
