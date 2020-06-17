package com.mascotas.huellitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mascotas.huellitas.Adapters.CategoriaAdapter;
import com.mascotas.huellitas.Models.Categoria;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListCategoriesActivity extends AppCompatActivity {

    List<Categoria> categoriaList = new ArrayList<>();
    RecyclerView recyclerView;
    String url;
    CategoriaAdapter adapter;

    /*
    RecyclerView recyclerView;
    List<Categoria> categoriaList = new ArrayList<>();
    String url;
    CategoriaAdapter adapter;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        /*
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
        */
        //data();

        llenarCategorias();
        /*
        recyclerView = findViewById(R.id.recyclerViewC);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //adapter = new CategoriaAdapter(this,categoriaList);
        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        */

        /*
        //Enviar Parámetros al servidor
        RequestBody formBody = new FormBody.Builder()
                .add("email", "jurassic@gmail.com")
                .add("password", "123")
                .add("name", "jurassic")
                .build();
        */
        /*
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url("https://swapi.dev/api/films/?format=json")
                .url("https://gtsgroup.com.pe/tiendaGlobal/dato.json")
                //.url("https://gtsgroup.com.pe/tiendaGlobal/categorias.php")
                //.post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(getApplicationContext(),"no se puedo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                JsonArray jsonArray;
                JsonObject jsonObject = new Gson().fromJson(json,JsonObject.class);
                jsonArray = jsonObject.getAsJsonArray("results");

                Gson gson = new GsonBuilder().create();
                Type list = new TypeToken<List<Categoria>>(){}.getType();
                categoriaList = gson.fromJson(jsonArray.toString(),list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new CategoriaAdapter(getApplicationContext(),categoriaList);
                        recyclerView.setAdapter(adapter);

                        //Invoco el metodo setOnClickListener definido en CategoriaAdapter.java
                        adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(),
                                        "Selección: "+categoriaList.get
                                                (recyclerView.getChildAdapterPosition(view))
                                                .getEpisode_id(),Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                });
            }
        });
        //update();
        */
    }

    public void llenarCategorias() {

        /*
        //Enviar Parámetros al servidor
        RequestBody formBody = new FormBody.Builder()
                .add("email", "jurassic@gmail.com")
                .add("password", "123")
                .add("name", "jurassic")
                .build();
         */

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url("https://swapi.dev/api/films/?format=json")
                .url("https://gtsgroup.com.pe/tiendaGlobal/dato.json")
                //.url("https://gtsgroup.com.pe/tiendaGlobal/categorias.php")
                //.post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(getApplicationContext(),"no se puedo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                JsonArray jsonArray;
                JsonObject jsonObject = new Gson().fromJson(json,JsonObject.class);
                jsonArray = jsonObject.getAsJsonArray("results");

                Gson gson = new GsonBuilder().create();
                Type list = new TypeToken<List<Categoria>>(){}.getType();
                categoriaList = gson.fromJson(jsonArray.toString(),list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        construirRecycler(categoriaList);

                    }
                });

            }
        });

    }

    private void construirRecycler(final List<Categoria> categoriaList) {

        //categoriaList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewC);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //adapter = new CategoriaAdapter(this,categoriaList);
        //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        //llenarPersonajes();

        adapter = new CategoriaAdapter(getApplicationContext(),categoriaList);


        //Invoco el metodo setOnClickListener definido en CategoriaAdapter.java
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Selección: "+categoriaList.get
                                (recyclerView.getChildAdapterPosition(view))
                                .getEpisode_id(),Toast.LENGTH_SHORT).show();


            }
        });

        recyclerView.setAdapter(adapter);

    }

    public void update(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new CategoriaAdapter(getApplicationContext(),categoriaList);
                adapter.notifyDataSetChanged();
            }
        });

    }

    /*
    public void data(){

        Categoria categoria = new Categoria();
        categoria.setTitle("titulo");
        categoria.setEpisode_id(1);
        categoria.setOpening_crawl("opening");
        categoria.setDirector("director");
        categoria.setProducer("producer");
        categoria.setUrl("url");
        categoria.setCreated("created");
        categoria.setEdited("edited");

        //creado el objeto lo agregamos a la lista
        categoriaList.add(categoria);

        //Esto es lo que vamos a obtener de la API pero aca manul mente lo vemos y haci podemos
        //crear mas 3,4,5...
        //Podemos reciclar ese objeto
        categoria = new Categoria();
        categoria.setTitle("titulo");
        categoria.setEpisode_id(2);
        categoria.setOpening_crawl("opening");
        categoria.setDirector("director");
        categoria.setProducer("producer");
        categoria.setUrl("url");
        categoria.setCreated("created");
        categoria.setEdited("edited");

        categoriaList.add(categoria);

    }
    */

}
