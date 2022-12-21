package com.example.resep.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resep.API.APIRequestData;
import com.example.resep.API.RetroServer;
import com.example.resep.Model.ResponseModel;
import com.example.resep.R;
import com.example.resep.API.APIRequestData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private int xId;
    private String xNama, xNamaP, xIsiRe;
    private EditText etNama, etNamaP, etIsiRe;
    private Button btnUbah;
    private String yNama, yNamaP, yIsiRe;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xNama = terima.getStringExtra("xNama");
        xNamaP = terima.getStringExtra("xNamaP");
        xIsiRe = terima.getStringExtra("xIsi");

        etNama = findViewById(R.id.et_nama);
        etNamaP = findViewById(R.id.et_namaP);
        etIsiRe = findViewById(R.id.et_isi_Re);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(xNama);
        etNamaP.setText(xNamaP);
        etIsiRe.setText(xIsiRe);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNama = etNama.getText().toString();
                yNamaP = etNamaP.getText().toString();
                yIsiRe = etIsiRe.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yNama, yNamaP, yIsiRe);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}