package com.example.tarea_app_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea_app_sqlite.modelo.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob, lastname;
    Button insert, update, delete, view;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nombre);
        lastname=findViewById(R.id.apellido);
        contact = findViewById(R.id.telefono);
        dob = findViewById(R.id.fecha_nacimiento);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DbHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT,lastnameTXT, contactTXT, dobTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Nuevo usuario ingresado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Usuario no ingresado", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT,lastnameTXT, contactTXT, dobTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Usuario no actualizado", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Usuario no eliminado", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No existen usuarios", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nombre :"+res.getString(0)+"\n");
                    buffer.append("Apellido :"+res.getString(1)+"\n");
                    buffer.append("Telefono :"+res.getString(2)+"\n");
                    buffer.append("Fecha de nacimiento :"+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Informacion de usuarios");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}