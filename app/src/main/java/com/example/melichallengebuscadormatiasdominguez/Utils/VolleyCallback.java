package com.example.melichallengebuscadormatiasdominguez.Utils;

import com.example.melichallengebuscadormatiasdominguez.Objects.Product;

import java.util.ArrayList;

public interface VolleyCallback {
    void onSuccess(ArrayList<Product> result);
    void onError(String result);
}