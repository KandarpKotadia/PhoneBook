package com.example.phonebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.AccessController;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvContacts;
    ArrayAdapter contacts;
    static ArrayList<String> contactsName, contactsNumber;
    Button addContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContacts = findViewById(R.id.lvContacts);
        addContact = findViewById(R.id.bAdd);
        contactsName = new ArrayList<>();
        contactsNumber = new ArrayList<>();

        contacts = new ArrayAdapter(this, R.layout.tv_contacts_to_display, contactsName);
        lvContacts.setAdapter(contacts);
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetailsDisplay.class);
                intent.putExtra("Number", contactsNumber.get(position));
                startActivity(intent);
            }
        });
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //Toast.makeText(getApplicationContext(),"Long pressed",Toast.LENGTH_LONG).show();

                new AlertDialog.Builder(MainActivity.this)
                .setTitle("Cation")
                .setMessage("what do you want to do?")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactsName.remove(position);
                        contactsNumber.remove(position);
                    }
                })
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LinearLayout lilaEditContacts = new LinearLayout(getApplicationContext());
                        lilaEditContacts.setOrientation(LinearLayout.VERTICAL);

                        final EditText editContactName = new EditText(getApplicationContext());
                        //editContactName.setMessage("Name");
                        editContactName.setText(contactsName.get(position));
                        lilaEditContacts.addView(editContactName);

                        final EditText editContactNumber = new EditText(getApplicationContext());
                        //editContactName.setMessage("Phone");
                        editContactNumber.setText(contactsNumber.get(position));
                        lilaEditContacts.addView(editContactNumber);

                        editContactName.setInputType(InputType.TYPE_CLASS_TEXT);
                        editContactNumber.setInputType(InputType.TYPE_CLASS_PHONE);

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Edit Contact")
                                //.setMessage("")
                                .setView(lilaEditContacts)
                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        contactsName.set(position, editContactName.getText().toString());
                                        contactsNumber.add(position, editContactNumber.getText().toString());
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                    }
                })
                .show();
                return false;
            }
        });

        addContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        LinearLayout lilay = new LinearLayout(this);
        lilay.setOrientation(LinearLayout.VERTICAL);

        final EditText contactName = new EditText(this);
        contactName.setHint("Contact Name");
        contactName.setEms(1);

        contactName.setMaxWidth(2);

        final EditText contactNumber = new EditText(this);
        contactNumber.setHint("Contact Number");
        
        lilay.addView(contactName);
        lilay.addView(contactNumber);

        contactName.setInputType(InputType.TYPE_CLASS_TEXT);
        contactNumber.setInputType(InputType.TYPE_CLASS_PHONE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Create Contact");
        //alert.setMessage("Enter contact details");
        alert.setView(lilay);
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                contactsName.add(contactName.getText().toString());
                contactsNumber.add(contactNumber.getText().toString());
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }
}