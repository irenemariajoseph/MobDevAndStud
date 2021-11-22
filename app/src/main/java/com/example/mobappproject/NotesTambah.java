package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesTambah extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notes_tambah);

            final EditText edtNama = (EditText) findViewById(R.id.edtNama);
            final EditText edtKatagori = findViewById(R.id.edtKatagori);


            Button btnReset = (Button) findViewById(R.id.btnReset);
            Button btnSimpan = findViewById(R.id.btnSimpan);

            final SQLite dbHandler = new SQLite(this);

            try {
                dbHandler.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notes notes = new Notes();
                    String JudulNotes = edtNama.getText().toString();
                    String KontenNotes = edtKatagori.getText().toString();

                    dbHandler.createNotes(JudulNotes,KontenNotes);

                    Toast.makeText(NotesTambah.this, "Note berhasil ditambahkan", Toast.LENGTH_LONG).show();
                    edtNama.setText("");
                    edtKatagori.setText("");

                    edtNama.requestFocus();

                    Intent i = new Intent(NotesTambah.this, NotesDriver.class);
                    startActivity(i);
                    NotesTambah.this.finish();
                    dbHandler.close();
                }
            });
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edtNama.setText("");
                    edtKatagori.setText("");
                    edtNama.requestFocus();

                    Intent i = new Intent(NotesTambah.this, AdminTrackingUpdate.class);
                    startActivity(i);
                    NotesTambah.this.finish();
                    dbHandler.close();
                }
            });

        }
    }