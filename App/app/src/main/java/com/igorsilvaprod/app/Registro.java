package com.igorsilvaprod.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    Button btnEntrar;
    Button btnVoltar;
    EditText editUserName,editLogin, editSenha;
    String url="http://192.168.1.7:3000/login/reg";
    //String url="http://localhost/Projeto_AndroidWEB/registrousuario.php?email=";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        requestQueue = Volley.newRequestQueue(this);
        editUserName = findViewById(R.id.editUserName);
        btnEntrar = findViewById(R.id.btnRegistrar);
        editLogin = findViewById(R.id.TextEmail);
        editSenha = findViewById(R.id.TextSenha);
        btnVoltar = findViewById(R.id.btnVoltar);
//        url+=editLogin.getText().toString()+"&senha="+editSenha.getText().toString();

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
                    registrarLogin();
                }
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaMain();
            }
        });
    }
    public void registrarLogin(){

                RequestQueue queue = Volley.newRequestQueue(Registro.this, new HurlStack());

                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);

                                Context context = getApplicationContext();
                                CharSequence text = "Registrado com sucesso, por favor retorne a tela anterior para fazer login";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();

                                irTelaMain();
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
                        params.put("usernm", editUserName.getText().toString());

                        return params;
                    }
                };
            queue.add(postRequest).setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 0;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
    }
    private void irTelaMain(){
//        Intent trocardetela= new Intent(Registro.this, MainActivity.class);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
