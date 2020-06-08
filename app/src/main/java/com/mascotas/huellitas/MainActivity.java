package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenSignPage(View view) {
        startActivity(new Intent(this,RegistrarClienteActivity.class));
    }

    public void OpenRecoveryPasswordPage(View view) {
        startActivity(new Intent(MainActivity.this,RecuperarPasswordClienteActivity.class));
    }

    public void OpenListCategoriesPage(View view) {
        startActivity(new Intent(this,ListCategoriesActivity.class));
    }
}
