package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String email,password;
    private static final String URL_SERVICE = "https://gtsgroup.com.pe/tiendaGlobal/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        editTextMail = findViewById(R.id.textClienteEmail);
        editTextPass = findViewById(R.id.textClientePass);

        recuperarPreferencias();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextMail.getText().toString();
                password = editTextPass.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()){
                    serviceUsuario(URL_SERVICE);
                }else{
                    Toast.makeText(MainActivity.this,"Debe ingresar los campos de email y password",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void serviceUsuario(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    guardarPreferencias();
                    Intent intent = new Intent(MainActivity.this,ListCategoriesActivity.class);
                    startActivity(intent);
                    finish();
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
                //parametros.put("email", editTextMail.getText().toString());
                //parametros.put("password", editTextPass.getText().toString());
                parametros.put("email", email);
                parametros.put("password", password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        editTextMail.setText(preferences.getString("email","micorreo@gmail.com"));
        editTextPass.setText(preferences.getString("password", "12345678"));
    }

    public void OpenRecoveryPasswordPage(View view) {
        startActivity(new Intent(MainActivity.this,RecuperarPasswordClienteActivity.class));
    }

    public void OnRegisterPage(View view) {
        startActivity(new Intent(MainActivity.this,RegistrarClienteActivity.class));
    }
}
