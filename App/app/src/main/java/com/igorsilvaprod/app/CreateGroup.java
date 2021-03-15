package com.igorsilvaprod.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.igorsilvaprod.app.models.userlinks;
import com.igorsilvaprod.app.models.users;

import java.util.HashMap;
import java.util.Map;

public class CreateGroup extends AppCompatActivity {
    Button btnVoltar, btnCriar;
    EditText editGroupName;
    String url="http://192.168.1.7:3000/login/auth";
    //StringRequest stringRequest;
    RequestQueue reqQueue;
    JsonArrayRequest ArRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StringRequest stringRequest;
        RequestQueue requestQueue;
        String url="http://192.168.1.7:3000/login/addDate";
        userlinks owner = new userlinks();
        userlinks fk_userID = new userlinks();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        EditText nomeGroup = findViewById(R.id.editGroupName);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaPrincipal();
            }
        });
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                if(editGroupName.getText().length()==0){
                    editGroupName.setError("Campo Obrigatório");
                    editGroupName.requestFocus();
                    validado=false;
                }
                if(validado){
                    updateGrupo();
                }
            }
        });
    }
    private void updateGrupo(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        irTelaPrincipal();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("editGroupName", editGroupName.getText().toString() );
                //ADICIONAR OWNER

                return params;
            }
        };
        queue.add(postRequest);

        ;}
    private void irTelaPrincipal(){
        Intent trocardetela= new Intent(CreateGroup.this, Principal.class);
        startActivity(trocardetela);
        finish();

    }
}
