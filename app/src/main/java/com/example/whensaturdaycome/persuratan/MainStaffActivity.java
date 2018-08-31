package com.example.whensaturdaycome.persuratan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whensaturdaycome.persuratan.data.AppControl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainStaffActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Button inbox;
    private Button outbox;
    RequestQueue requestQueue;

    private static final String PROFILE_URL = "http://192.168.43.186/apicoba/index.php/profile";
    private String datahasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_staff);

        Toolbar toolbar = findViewById(R.id.toolbar);

        inbox = findViewById(R.id.inbox);
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainStaffActivity.this, SuratMasukActivity.class));
                Toast.makeText(getApplicationContext(), "Inbox Telah Dipilih", Toast.LENGTH_SHORT).show();
            }
        });

        outbox = findViewById(R.id.outbox);
        outbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainStaffActivity.this, SuratKeluarActivity.class));
                Toast.makeText(getApplicationContext(), "Outbox Telah Dipilih", Toast.LENGTH_SHORT).show();
            }

        });

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        @SuppressLint("WrongConstant") SharedPreferences preferences= getSharedPreferences("signin",MODE_APPEND);
        final String userid= preferences.getString("username",null);


        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView name = navigationView.getHeaderView(0).findViewById(R.id.name);
        String appUsername = new AppControl(this).getUser();
        try {
            name.setText(appUsername);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navigation1) {
            String username = new AppControl(this).getUser();
            profilRequest(username);
            Toast.makeText(getApplicationContext(), "Profil Telah Dipilih", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.navigation2) {
            Toast.makeText(getApplicationContext(), "Surat Masuk Telah Dipilih", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainStaffActivity.this, SuratMasukActivity.class));
            return true;
        } else if (id == R.id.navigation3) {
            Toast.makeText(getApplicationContext(), "Surat Keluar Telah Dipilih", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainStaffActivity.this, SuratKeluarActivity.class));
            return true;
        } else if (id == R.id.navigation4) {
            Toast.makeText(getApplicationContext(), "Informasi Telah Dipilih", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainStaffActivity.this, InformasiActivity.class));
            return true;
        } else if (id == R.id.navigation5) {
            new AppControl(this).logout();
            Toast.makeText(getApplicationContext(), "Anda Keluar", Toast.LENGTH_SHORT).show();
            Intent keluar = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(keluar);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void profilRequest(final String username) {
        RequestQueue queue = Volley.newRequestQueue(MainStaffActivity.this);
        String response = null;
        final String responsel = response;
        StringRequest postRequest = new StringRequest(Request.Method.POST, PROFILE_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //pd.hide();
                            //showSnackbar(response);
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("sukses")) {
                                datahasil = response ;
                                Bundle b= new Bundle();
                                Intent intent = new Intent(MainStaffActivity.this, ProfileActivity.class);


                                b.putString("profile", datahasil);
                                intent.putExtras(b);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "load sukses",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "load gagal",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        /* pd.hide(); */
                        NetworkResponse responsel = error.networkResponse;
                        if (responsel != null && responsel.data != null) {
                            Toast.makeText(getApplicationContext(), "salah email or password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Cek your conection", Toast.LENGTH_SHORT).show();
                        }
//                        Log.d("ErrorResponse", responsel);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username",username);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
}
