package com.example.whensaturdaycome.persuratan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    private TextView nama, email, status,username,id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle b = getIntent().getExtras();

        nama = (TextView) findViewById(R.id.nama);
        email = (TextView) findViewById(R.id.email);
        username= (TextView) findViewById(R.id.userp);
        status=(TextView)findViewById(R.id.status);
        id_user = (TextView) findViewById(R.id.idus);

        try {
            JSONObject jsonObject= new JSONObject(b.getString("profile"));
            JSONObject data = jsonObject.getJSONObject("data");
            nama.setText(""+ data.get("nama"));
            email.setText(""+ data.get("email"));
            username.setText(""+ data.get("username"));
            status.setText(""+data.get("level"));
            id_user.setText(""+data.get("id_user"));

        }catch (Exception e){

        }

    }
}

