package com.sanket.safewe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactDetailsActivity extends AppCompatActivity {

    private EditText etName1, etName2, etName3,
                etPhone1, etPhone2, etPhone3,
                etEmail1, etEmail2, etEmail3,
                etAddress1, etAddress2, etAddress3;
    private Button save;
    ContactViewModel contactViewModel;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        sharedPreference = new SharedPreference(this);

        contactViewModel =  new ViewModelProvider(this).get(ContactViewModel.class); //init

        save = findViewById(R.id.save_ec);

        etName1 = findViewById(R.id.name);
        etName2 = findViewById(R.id.name2);
        etName3 = findViewById(R.id.name3);

        etEmail1 = findViewById(R.id.email);
        etEmail2 = findViewById(R.id.email2);
        etEmail3 = findViewById(R.id.email3);

        etPhone1 = findViewById(R.id.phn);
        etPhone2 = findViewById(R.id.phn2);
        etPhone3 = findViewById(R.id.phn3);

        etAddress1 = findViewById(R.id.address);
        etAddress2 = findViewById(R.id.address2);
        etAddress3 = findViewById(R.id.address3);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = etName1.getText().toString();
                String name2 = etName2.getText().toString();
                String name3 = etName3.getText().toString();

                String email1 = etEmail1.getText().toString();
                String email2 = etEmail2.getText().toString();
                String email3 = etEmail3.getText().toString();

                String phone1 = etPhone1.getText().toString();
                String phone2 = etPhone2.getText().toString();
                String phone3 = etPhone3.getText().toString();

                String address1 = etAddress1.getText().toString();
                String address2 = etAddress2.getText().toString();
                String address3 = etAddress3.getText().toString();

                if(name1.isEmpty() || name2.isEmpty() || name3.isEmpty()
                || email1.isEmpty() || email2.isEmpty() || email3.isEmpty()
                || phone1.isEmpty() || phone2.isEmpty() || phone3.isEmpty()
                || address1.isEmpty() || address2.isEmpty() || address3.isEmpty()){

                    Toast.makeText(ContactDetailsActivity.this, "All Fields are Compulsory", Toast.LENGTH_SHORT).show();
                }
                else{
                    EmergencyContacts emergencyContacts = new EmergencyContacts(name1, email1, phone1, address1);
                    EmergencyContacts emergencyContacts2 = new EmergencyContacts(name2, email2, phone2, address2);
                    EmergencyContacts emergencyContacts3 = new EmergencyContacts(name3, email3, phone3, address3);
                        contactViewModel.insert(emergencyContacts);
                        contactViewModel.insert(emergencyContacts2);
                        contactViewModel.insert(emergencyContacts3);
                }
            }
        });
    }
}