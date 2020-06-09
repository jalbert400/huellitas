package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    TextInputEditText editTextMail;
    TextInputEditText editTextPass;
    private static final String URL_SERVICE = "https://gtsgroup.com.pe/tiendaGlobal/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        editTextMail = findViewById(R.id.textClienteEmail);
        editTextPass = findViewById(R.id.textClientePass);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceUsuario(URL_SERVICE);
            }
        });

    }

    private void serviceUsuario(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent intent = new Intent(MainActivity.this,ListCategoriesActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"El usuario y password incorrectos",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"se fue al servidor y regreso",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", editTextMail.getText().toString());
                parametros.put("password", editTextPass.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }


    public void OpenRecoveryPasswordPage(View view) {
        startActivity(new Intent(MainActivity.this,RecuperarPasswordClienteActivity.class));
    }

    public void OnRegisterPage(View view) {
        startActivity(new Intent(MainActivity.this,RegistrarClienteActivity.class));
    }
}
