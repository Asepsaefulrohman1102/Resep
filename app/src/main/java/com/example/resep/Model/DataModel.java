package com.example.resep.Model;

public class DataModel {
    private int id;
    private String nama, nama_pembuat , isi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_pembuat() {
        return nama_pembuat;
    }

    public void setNama_pembuat(String nama_pembuat) {
        this.nama_pembuat = nama_pembuat;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
