package com.example.whensaturdaycome.persuratan.data;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.whensaturdaycome.persuratan.LoginActivity;
import com.example.whensaturdaycome.persuratan.MainActivity;

import org.json.JSONObject;

public class AppControl extends Application {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private String user,pass;

    @SuppressLint("WrongConstant")
    public AppControl(Context context){
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences("Login",Context.MODE_APPEND);
        this.editor            = this.sharedPreferences.edit();
    }
    @SuppressLint("WrongConstant")
    public AppControl(Context context, String username, String password){
        this.context           = context;
        this.sharedPreferences = this.context.getSharedPreferences("Login",Context.MODE_APPEND);
        this.editor            = this.sharedPreferences.edit();
        this.editor.putString("username",username);
        this.editor.putString("password",password);
        this.editor.commit();
    }

    public String getid(){
        String json = sharedPreferences.getString("data",null);
        String id   = null;
        try{
            JSONObject object = new JSONObject(json);
             id                     = object.getString("id_user");
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public boolean isLogin(){
        String username    = this.sharedPreferences.getString("user" ,null);
        String password = this.sharedPreferences.getString("pass" ,null);
        if(username == null || password == null){
            return false;
        }
        return true;
    }
    public String getUser() {
        return this.sharedPreferences.getString("username",null);
    }
    public String getPass() {
        return this.sharedPreferences.getString("password",null);
    }
    public void logout() {
        this.editor.clear();
        this.editor.commit();
    }
}
