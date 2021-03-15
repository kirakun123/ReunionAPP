package com.igorsilvaprod.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class Principal extends AppCompatActivity {

    TextView Date;
    Button btnVoz;
    RecyclerView recicleReunion;
    String url="http://192.168.1.7:3000/login/home";
    //    StringRequest stringRequest;
    RequestQueue reqQueue;
    JsonArrayRequest ArRequest;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        FloatingActionButton btnVoz = findViewById(R.id.btnVoz);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irTelaRegData();
            }
        });
        btnVoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoice();
            }
        });
    }
    private void startVoice(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ola, para marcar uma reuniao diga DATA ");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                }
                if(mVoiceInputTv.getText().toString().equals("DATA")){
                    Intent intent = new Intent(getApplicationContext(),RegDate.class);
                    startActivity(intent);
                }
                break;
            }

        }
    }
    public void atualizarReunion(){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray reunions = new JSONArray(response);

                        for(int i=0; i<reunions.length(); i++){
                            //CARREGAR RECYCLER VIEW
                            JSONObject reunionsObj = reunions.getJSONObject(i);
                            String reunionName = reunionsObj.getString("reunionName");
                            String date = reunionsObj.getString("date");
                            String hour = reunionsObj.getString("hour");

                            //reunions Reunions = new reunions(reunionName,date);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Principal.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(this).add(stringRequest);
    }
    public void irTelaRegData(){
        Intent trocardetela= new Intent(Principal.this, RegDate.class);
        startActivity(trocardetela);
        finish();
    }
}
