package com.example.tarea_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tarea_3.api.ApiService;
import com.example.tarea_3.entity.User;
import com.example.tarea_3.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>data = new ArrayList<>();
    ListView lstUsuario = null;
    ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstUsuario = findViewById(R.id.ListaUsuario);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lstUsuario.setAdapter(adapter);

        cargarData();
    }
    public void cargarData(){
        ApiService api = ConnectionRest.getConnection().create(ApiService.class);
        Call<List<User>> call = api.listaUsuario();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> users = response.body();
                    for (User x : users){
                        data.add(x.getName());
                        data.add(x.getUsername());
                        data.add(x.getEmail());
                        data.add(Integer.toString(x.getId()));

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}