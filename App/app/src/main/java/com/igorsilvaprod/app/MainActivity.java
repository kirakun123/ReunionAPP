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
import com.igorsilvaprod.app.models.users;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    users S_Users = com.igorsilvaprod.app.models.users.getInstance();
    Button btnEntrar;
    Button btnAAA;
    Button btnRegistrar;
    EditText editLogin, editSenha;
    String url="http://192.168.1.7:3000/login/auth";
//    StringRequest stringRequest;
    RequestQueue reqQueue;
    JsonArrayRequest ArRequest;
    private int userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        reqQueue = Volley.newRequestQueue(this);
        btnEntrar = findViewById(R.id.btnEntrar);
        editLogin = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnAAA = findViewById(R.id.btnAAA);


        btnAAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaPrincipal();}});

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                if(editLogin.getText().length()==0){
                    editLogin.setError("Campo Obrigatório");
                    editLogin.requestFocus();
                    validado=false;
                }
                if(editSenha.getText().length()==0){
                    editSenha.setError("Campo Obrigatório");
                    editSenha.requestFocus();
                    validado=false;
                }
                if(validado){
                    updateLogin();
                }
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaRegistrar();
            }
        });
    }



    private void updateLogin() {
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
                    params.put("email", editLogin.getText().toString() );
                    params.put("password", editSenha.getText().toString() );


                    return params;
                }
            };
            queue.add(postRequest);

        ;}//

    private void irTelaRegistrar(){
        Intent trocardetela= new Intent(MainActivity.this, Registro.class);
        startActivity(trocardetela);
        finish();
    }
    private void irTelaPrincipal(){
        Intent trocardetela= new Intent(MainActivity.this, Principal.class);
        startActivity(trocardetela);
        finish();

    }
}
