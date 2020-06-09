package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListCategoriesActivity extends AppCompatActivity {

    Button buttonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);

        buttonClose = findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(ListCategoriesActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
