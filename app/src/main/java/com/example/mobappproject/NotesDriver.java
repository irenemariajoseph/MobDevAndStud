package com.example.mobappproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotesDriver extends ListActivity implements AdapterView.OnItemLongClickListener {

    private SQLite dbHandler;
    private ArrayList<Notes> values;
//    private Button btnEdit, btnHapus;
    private ListView list;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_driver);

        dbHandler = new SQLite(this);
        try {
            dbHandler.open();
        } catch (SQLException e){
            e.printStackTrace();
        }

        values = dbHandler.getAllNotes();

        ArrayAdapter<Notes> adapter = new ArrayAdapter <Notes> (this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        list = (ListView) findViewById(android.R.id.list);
        list.setOnItemLongClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NotesTambah.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }


}