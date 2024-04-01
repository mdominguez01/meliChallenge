package com.example.melichallengebuscadormatiasdominguez.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.melichallengebuscadormatiasdominguez.Objects.Product;
import com.example.melichallengebuscadormatiasdominguez.R;
import com.example.melichallengebuscadormatiasdominguez.Utils.Utils;
import com.example.melichallengebuscadormatiasdominguez.databinding.FragmentThirdBinding;
import com.squareup.picasso.Picasso;


public class ProductFragment extends Fragment {
    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Product product = getParamsOfPreviusView();
        setContentInViews(product);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //obtener paramentros enviados por la anterior vista
    public Product getParamsOfPreviusView(){
        Bundle bundle = getArguments();
        String productJsonString = bundle.getString("PRODUCT");
        return Utils.getGsonParser().fromJson(productJsonString, Product.class);
    }

    //setear contenido de los textViews e iamgeViews
    public void setContentInViews(Product product){
        //Obtner imagen de la url dada y setearla en imageView
        Picasso.get().load(product.getImage()).placeholder(R.drawable.ic_launcher_foreground).into(binding.image);

        //setear otros parametros de prducto
        binding.tvName.setText("Nombre: "+product.getName());
        binding.tvPrice.setText("Precio: "+product.getName() + ""+product.getCurrency());
        binding.tvCondition.setText("Condicion: "+product.getCondition());
        binding.tvQuantity.setText("Cantidad:" +product.getQuantity());
    }
}