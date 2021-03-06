package com.techkid.anh82.demoretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassWord;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassWord = (EditText) findViewById(R.id.etPassWord);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a-server.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        RegisterService registerService = retrofit.create(RegisterService.class);
        registerService.register(new RegisterRequest(etUserName.getText().toString(),etPassWord.getText().toString())).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.code()==200){
                    RegisterResponse registerResponse = response.body();

                    if(registerResponse.getCode() ==1){
                        Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No connection internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}
