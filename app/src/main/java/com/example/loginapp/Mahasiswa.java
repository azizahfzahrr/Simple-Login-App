package com.example.loginapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Mahasiswa implements Parcelable {
    private String Username;
    private String Password;
    private int Nim;
    private int Umur;

    public void setUsername(String username){
        Username = username;
    }
    public void setNim(int nim){
        Nim = nim;
    }
    public void setUmur(int umur){
        Umur = umur;
    }
    public void setPassword(String password){
        Password = password;
    }
    public String getUsername(){
        return Username;
    }
    public int getNim(){
        return Nim;
    }
    public int getUmur(){
        return Umur;
    }
    public String getPassword(){
        return Password;
    }

    protected Mahasiswa(Parcel in) {
        Username = in.readString();
        Nim = in.readInt();
        Umur = in.readInt();
        Password = in.readString();
    }

    public Mahasiswa(String username, int nim, int umur, String password){
        this.Username = username;
        this.Nim = nim;
        this.Umur = umur;
        this.Password = password;
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(Username);
        dest.writeInt(Nim);
        dest.writeInt(Umur);
        dest.writeString(Password);
    }
}
