package com.example.resep.Activity;

import android.annotation.SuppressLint;
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
import com.example.resep.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etNamaP, etIsiR;
    private Button btnSimpan;
    private String nama, namaP, isiR;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etNamaP = findViewById(R.id.et_namaP);
        etIsiR = findViewById(R.id.et_isi);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = etNama.getText().toString();
                namaP = etNamaP.getText().toString();
                isiR = etIsiR.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }
                else if(namaP.trim().equals("")){
                    etNamaP.setError("Nama Pembuat Harus Diisi");
                }
                else if(isiR.trim().equals("")){
                    etIsiR.setError("Telepon Harus Diisi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama, namaP, isiR);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
