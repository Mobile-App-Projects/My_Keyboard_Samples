package com.mutuinda.mykeyboardsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText birthday;
    private EditText firstname;
    private EditText surname;
    private EditText phone;
    private EditText email;
    private EditText uPassword;
    private EditText ucPassword;
    private int mYear;
    private int mMonth;
    private int mDay;
    String fName, sName, number, address, bDate, uPass, ucPass;
    Spinner phoneSpinner;
    private String mSpinnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.first_name);
        surname = findViewById(R.id.surname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email_address);
        uPassword = findViewById(R.id.password);
        ucPassword = findViewById(R.id.confirm_password);

        fName = firstname.getText().toString();
        sName = surname.getText().toString();
        number = phone.getText().toString();
        address = email.getText().toString();
        uPass = uPassword.getText().toString();
        ucPass = ucPassword.getText().toString();

        birthday = findViewById(R.id.birthday);
        bDate = birthday.getText().toString();
        birthday.setOnClickListener(this);

        phoneSpinner = findViewById(R.id.phone_spinner);

        if(phoneSpinner != null){
            phoneSpinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_label, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(phoneSpinner != null){
            phoneSpinner.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View view) {
        if(view == birthday){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay=  c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    birthday.setText(dayOfMonth + "-" + (month-1 + "-" + year));
                }
            },mYear,mMonth,mDay);

            datePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        Toast myToast = Toast.makeText(MainActivity.this, "Selected phone as: " + mSpinnerLabel, Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast toast  = Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(MainActivity.this, "Please Accept Terms and Conditions", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void createAccount(View view) {
        CredentialsCheck();
        Toast.makeText(MainActivity.this,"Success!",Toast.LENGTH_SHORT).show();
    }

    private void CredentialsCheck(){
        if(fName.isEmpty() || sName.isEmpty() || number.isEmpty() || bDate.isEmpty() || address.isEmpty() || uPass.isEmpty() || ucPass.isEmpty()){
            Toast.makeText(MainActivity.this, "Missing Credentials", Toast.LENGTH_SHORT).show();
        }else if(!uPass.equals(ucPass)){
            Toast.makeText(MainActivity.this,"Password Mismatch!",Toast.LENGTH_SHORT).show();
        }
    }
}

