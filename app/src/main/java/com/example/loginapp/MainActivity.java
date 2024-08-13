package com.example.loginapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Mahasiswa> ArrMhs = new ArrayList<>();
    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Register;
    private int counter = 5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.IdUsername);
        Password = (EditText) findViewById(R.id.IdPasswd);
        Info = (TextView) findViewById(R.id.IdInfo);
        Register = (Button) findViewById(R.id.idRegister);
        Login = (Button) findViewById(R.id.idLogin);

        Intent intent = getIntent();
        if (intent.hasExtra("myData")) {
            ArrMhs = intent.getParcelableArrayListExtra("myData");
        }

        Register.setOnClickListener(this);
        Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idLogin) {
            validate(Username.getText().toString(), Password.getText().toString());
        }

        if (v.getId() == R.id.idRegister) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }

    private void validate(String valUsername, String valPassword) {
        if (ArrMhs != null && !ArrMhs.isEmpty()) {
            for (int idx = 0; idx < ArrMhs.size(); idx++) {
                String UserPassword = ArrMhs.get(idx).getPassword();
                String UserName = ArrMhs.get(idx).getUsername();
                if (valUsername.equals(UserName) && valPassword.equals(UserPassword)) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    return;  // Berhenti setelah login berhasil
                }
            }

            // Jika tidak ada kecocokan username dan password
            counter--;
            Info.setText("Toleransi Jumlah Kesalahan: " + counter);

            if (counter == 0) {
                Login.setEnabled(false);
            }
        } else {
            Info.setText("Belum ada data pengguna. Silakan registrasi terlebih dahulu.");
        }
    }
}
