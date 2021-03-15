package com.igorsilvaprod.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.igorsilvaprod.app.models.users;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegDate extends AppCompatActivity {
    Button btnRegistrarDate;
    Button btnVoltar;
    EditText editTema, editData, editHorario;
    //int userID = MainActivity.MeuUserID.getInstance().GuardaUserID;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String url="http://192.168.1.7:3000/login/addDate";
    users userID = new users();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_date);


        requestQueue = Volley.newRequestQueue(this);
        btnRegistrarDate = findViewById(R.id.btnRegistrarDate);
        editTema = findViewById(R.id.editTema);
        editData = findViewById(R.id.editData);
        editHorario = findViewById(R.id.editHorario);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnRegistrarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                if(editTema.getText().length()==0){
                    editTema.setError("Campo Obrigatório");
                    editTema.requestFocus();
                    validado=false;
                }
                if(editData.getText().length()==0){
                    editData.setError("Campo Obrigatório");
                    editData.requestFocus();
                    validado=false;
                }
                if(editHorario.getText().length()==0){
                    editHorario.setError("Campo Obrigatório");
                    editHorario.requestFocus();
                    validado=false;
                }
                if (validado) {
                    registrarDate();
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

    public void registrarDate(){
        RequestQueue queue = Volley.newRequestQueue(RegDate.this, new HurlStack());

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        Context context = getApplicationContext();
                        CharSequence text = "Registrado com sucesso, por favor retorne a tela anterior";
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
                params.put("editTema", editTema.getText().toString() );
                params.put("editDate", editData.getText().toString() );
                params.put("editHorario", editHorario.getText().toString());
                userID.getUserID();
                //MainActivity.MeuUserID.getInstance().GuardaUserID;


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
    public void irTelaMain(){
        startActivity(new Intent(this, Principal.class));
        finish();
    }
}
