package com.example.melichallengebuscadormatiasdominguez.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.melichallengebuscadormatiasdominguez.Objects.Product;
import com.example.melichallengebuscadormatiasdominguez.R;
import com.example.melichallengebuscadormatiasdominguez.Utils.RestApi;
import com.example.melichallengebuscadormatiasdominguez.Utils.Utils;
import com.example.melichallengebuscadormatiasdominguez.Utils.VolleyCallback;
import com.example.melichallengebuscadormatiasdominguez.databinding.FragmentSecondBinding;

import java.io.IOException;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private FragmentSecondBinding binding;

    private ListView listOfProducts;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = null;
        binding.title.setText("Productos: ");

        searchWordOnline(view);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //toProductFragment: navegar a la siguiente vista
    private void toProductFragment(Product product) {

        //paramentros que se desean enviar a la siguiente vista

        Bundle bundle = new Bundle();
        String personJsonString = Utils.getGsonParser().toJson(product);
        bundle.putString("PRODUCT", personJsonString);

        //cambiar de vista

        NavHostFragment.findNavController(ListFragment.this)
                .navigate(R.id.action_SecondFragment_to_thirdFragment,bundle);
    }


    // searchWordOnline: buscar resultados en la url definida previamente
    private void searchWordOnline(View view) {

        //obtener parametros de la anterior vista

        Bundle bundle=getArguments();
        String search=bundle.getString("search");
        binding.title.setText("Productos: " + search);

        //instanciar aqui

        ArrayList<String> productsTitles = new ArrayList<>();
        RestApi restApi = new RestApi();

        // instancia spinning wheel para mostrar hasta que se carguen los datos
        ProgressDialog dialog = new ProgressDialog(getContext()); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle(R.string.DialogLoading_Title);
        dialog.setMessage(getString(R.string.DialogLoading_Description));
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //
        try {

            restApi.searchProducts(getContext(), search, new VolleyCallback() {
                @Override
                public void onSuccess(ArrayList<Product> result) {

                    //armar array de titulos(string) para ser visualizados a modo lista

                    for (int i=0;i<result.size();i++){
                        productsTitles.add(result.get(i).getName());
                    }

                    //obtener cantidad de elementos en la lista para luego redimenzionarla

                    listOfProducts = (ListView)view.findViewById(R.id.list_of_products);
                    ViewGroup.LayoutParams lp =  listOfProducts.getLayoutParams();
                    lp.height = (productsTitles.size()*130);
                    listOfProducts.setLayoutParams(lp);

                    //setear listview con items (titulos)
                    adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1,productsTitles);
                    binding.listOfProducts.setAdapter(adapter);

                    binding.listOfProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            toProductFragment(result.get(position));
                        }
                    });
                    dialog.dismiss();
                }

                @Override
                public void onError(String result) {
                    dialog.dismiss();
                    Utils.showErrorDialog(getContext(),getString(R.string.WithoutConnectio_Title),getString(R.string.WithoutConnectio_Description),getString(R.string.WithoutConnectio_Button));
                }
            });
        } catch (IOException e) {
            dialog.dismiss();
            Utils.showErrorDialog(getContext(),getString(R.string.WithoutConnectio_Title),getString(R.string.WithoutConnectio_Description),getString(R.string.WithoutConnectio_Button));

            Log.e("LF_SearchWord",e.toString());
            throw new RuntimeException(e);
        }
    }

}