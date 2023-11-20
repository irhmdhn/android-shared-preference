package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences("com.example.sharedpreference", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setAsDefaultValue();
    }

    private void setAsDefaultValue(){
        setUsername("admin");
        setPassword("admin");
    }

    private void setUsername(String UsernameValue) {
        editor.putString("sp_username", UsernameValue).apply();
    }
    private void setPassword(String PasswordValue) {
        editor.putString("sp_password", PasswordValue).apply();
    }
    public void saveIsLogin(Boolean value){
        editor.putBoolean("sp_islogin", value).commit();
    }

    public String getUsername(){
        return sharedPreferences.getString("sp_username","");
    }
    public String getPassword(){
        return sharedPreferences.getString("sp_password","");
    }
    public boolean getIsLogin(){
        return  sharedPreferences.getBoolean("sp_islogin",false);
    }
}
