package com.example.melichallengebuscadormatiasdominguez.Utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.melichallengebuscadormatiasdominguez.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {
    private static Gson gson;

    public static Gson getGsonParser() {
        if(null == gson) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }

    //showErrorDialog: mostrar error en cuadro de texto para el usuario
    public static void showErrorDialog(Context context,String title,String Description,String button){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
        dialogo1.setTitle(title);
        dialogo1.setMessage(Description);
        dialogo1.setPositiveButton(button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }
}