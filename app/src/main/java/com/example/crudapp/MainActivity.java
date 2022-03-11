package com.example.crudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editName, editGender, editRoll, editId;
    Button btnAllData;
    Button btnviewALl;
    Button btnDelete;
    Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.edit_name);
        editGender = (EditText) findViewById(R.id.edit_gender);
        editRoll = (EditText) findViewById(R.id.edit_roll);
        editId = (EditText) findViewById(R.id.edit_search);
        btnAllData = (Button) findViewById(R.id.btn_add);
        btnviewALl = (Button) findViewById(R.id.btn_viewall);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnupdate = (Button) findViewById(R.id.btn_update);


        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }


    public void AddData() {
        btnAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(editName.getText().toString(), editGender.getText().toString(), editRoll.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data Inserted ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll() {
        btnviewALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n");
                    buffer.append("Name:" + res.getString(1) + "\n");
                    buffer.append("Gender:" + res.getString(2) + "\n");
                    buffer.append("RollNo:" + res.getString(3) + "\n\n");
                }
                showMessage("data", buffer.toString());
            }
        });
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void clear(View view) {
        editId.setText("");
        editName.setText("");
        editRoll.setText("");
        editGender.setText("");

    }
    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows=mydb.deleteData(editId.getText().toString());
                if (deleteRows>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateData(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=mydb.UpdateData(editId.getText().toString(),
                        editName.getText().toString(),
                        editGender.getText().toString(),editRoll.getText().toString());
                if (isUpdate==true)
                    Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();

            }
        });
    }
}