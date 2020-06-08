package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarPasswordClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password_cliente);
    }

    public void OpenRegresarLoginPage(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void OpenListCategoriesPage(View view) {
        startActivity(new Intent(this,ListCategoriesActivity.class));
    }
}
