package com.example.melichallengebuscadormatiasdominguez.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.melichallengebuscadormatiasdominguez.Objects.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import kotlin.text.Charsets;

public class RestApi {

    //definir paramentros aqui
    final static String url ="https://api.mercadolibre.com/sites/MLA";

    //buscar producto que contengan las palabras o letras definidas previamente
    public void searchProducts(Context context,String keywords,final VolleyCallback callback) throws IOException {

        String query = URLEncoder.encode(keywords, Charsets.UTF_8.name());
        String URL = url + "/search?q="+query;
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Product> products = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            if (response != null) {
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject obj =jsonArray.getJSONObject(i);
                                    String name =obj.getString("title");
                                    int price =obj.getInt("price");
                                    String currency =obj.getString("currency_id");
                                    int quantity =obj.getInt("available_quantity");
                                    String condition =obj.getString("condition");
                                    String image =obj.getString("thumbnail");
                                    Product p = new Product(name,price,currency,quantity,condition,image);
                                    products.add(p);
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        callback.onSuccess(products);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RA_SearchPr",volleyError.toString());
                        callback.onError(volleyError.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);


    }


}
