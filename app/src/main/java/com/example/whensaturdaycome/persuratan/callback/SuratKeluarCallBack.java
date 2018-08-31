package com.example.whensaturdaycome.persuratan.callback;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SuratKeluarCallBack {
    RequestQueue requestQueue;
    String url = "http://192.168.43.186/apicoba/index.php/Surat_keluar/";

    public SuratKeluarCallBack(Context context){

        requestQueue = Volley.newRequestQueue(context);
    }

    public void SuratKeluarCallBack (final SuratKeluarCallBack.SuratKeluarBack webBack){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.e("data", response.toString());
                    webBack.hasil(response.toString());

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    public interface SuratKeluarBack{
        void hasil (String hasil);
    }
}
