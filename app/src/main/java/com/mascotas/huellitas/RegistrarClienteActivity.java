package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarClienteActivity extends AppCompatActivity {

    Button buttonRegistrar;
    TextInputEditText textName,textEmail,textPassword;
    String name,email,password;
    ProgressBar loadingBar;
    private static final String URL_SERVICE = "https://gtsgroup.com.pe/tiendaGlobal/insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        buttonRegistrar = findViewById(R.id.buttonRegistrar);
        textName = findViewById(R.id.textRegistroNombre);
        textEmail = findViewById(R.id.textRegistroEmail);
        textPassword = findViewById(R.id.textRegistroPassword);
        loadingBar = findViewById(R.id.loadingBar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = textName.getText().toString().trim();
                email = textEmail.getText().toString().trim();
                password = textPassword.getText().toString().trim();
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    registrarUsuario(URL_SERVICE);
                }else{
                    Toast.makeText(RegistrarClienteActivity.this,"Debe ingresar los campos para registrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registrarUsuario(String URL) {
        loadingBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    //JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")){
                        Toast.makeText(RegistrarClienteActivity.this,"Registro exitoso: "+name,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarClienteActivity.this,ListCategoriesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(RegistrarClienteActivity.this,"Registro incorreccto",Toast.LENGTH_SHORT).show();
                }
                /*
                if(!response.isEmpty()){
                    Intent intent = new Intent(RegistrarClienteActivity.this,ListCategoriesActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegistrarClienteActivity.this,"No se ha guardado correctamente el registro",Toast.LENGTH_SHORT).show();
                }
                */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarClienteActivity.this,"se fue al servidor y regreso",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //parametros.put("email", editTextMail.getText().toString());
                //parametros.put("password", editTextPass.getText().toString());
                parametros.put("name", name);
                parametros.put("email", email);
                parametros.put("password", password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RegistrarClienteActivity.this);
        requestQueue.add(stringRequest);

    }


    public void OpenListCategoriesPage(View view) {
        startActivity(new Intent(RegistrarClienteActivity.this,ListCategoriesActivity.class));
    }

    public void OpenRegresarLoginPage(View view) {
        startActivity(new Intent(RegistrarClienteActivity.this,MainActivity.class));
    }
}
