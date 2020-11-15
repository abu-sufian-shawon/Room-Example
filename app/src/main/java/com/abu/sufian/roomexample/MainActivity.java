package com.abu.sufian.roomexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abu.sufian.roomexample.asynctask.InsertAsyncTask;
import com.abu.sufian.roomexample.datamodel.StudentModel;
import com.abu.sufian.roomexample.room.DatabaseClass;

public class MainActivity extends AppCompatActivity {
    private EditText edtFirstName, edtLastName, edtEmail, edtPhone, edtInstitute;
    private Spinner spnTechnology, spnSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize all view objects
        init();

        // Set Spinner Item
        createSpinner();
    }

    private void init() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtInstitute = findViewById(R.id.edtInstitute);
        spnTechnology = findViewById(R.id.spnTechnology);
        spnSemester = findViewById(R.id.spnSemester);
    }

    private void createSpinner() {
        String[] technology = getResources().getStringArray(R.array.technology);
        String[] semester = getResources().getStringArray(R.array.semester);
        ArrayAdapter<String> technologyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                technology);
        technologyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                semester);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTechnology.setAdapter(technologyAdapter);
        spnSemester.setAdapter(semesterAdapter);
    }

    private StudentModel getDataModel() {
        String firstName = edtFirstName.getText().toString().trim();
        String lastName = edtLastName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String institute = edtInstitute.getText().toString().trim();

        String technology, semester;

        if (spnTechnology.getSelectedItemId() != 0)
            technology = spnTechnology.getSelectedItem().toString();
        else {
            TextView textView = (TextView) spnTechnology.getSelectedView();
            textView.setError("");
            textView.setTextColor(Color.RED);
            return null;
        }

        if (spnSemester.getSelectedItemId() != 0)
            semester = spnSemester.getSelectedItem().toString();
        else {
            TextView textView = (TextView) spnSemester.getSelectedView();
            textView.setError("");
            textView.setTextColor(Color.RED);
            return null;
        }

        if (firstName.isEmpty()) {
            edtFirstName.requestFocus();
            edtFirstName.setError("Empty");
            return null;
        } else if (lastName.isEmpty()) {
            edtLastName.requestFocus();
            edtLastName.setError("Empty");
            return null;
        } else if (phone.isEmpty()) {
            edtPhone.requestFocus();
            edtPhone.setError("Empty");
            return null;
        } else if (email.isEmpty()) {
            edtEmail.requestFocus();
            edtEmail.setError("Empty");
            return null;
        } else if (institute.isEmpty()) {
            edtInstitute.requestFocus();
            edtInstitute.setError("Empty");
            return null;
        }

        return new StudentModel(firstName, lastName, phone, email, institute, technology, semester);
    }


    public void saveData(View v) {
        if (getDataModel() != null) {
            Toast.makeText(this, "Start data inserting...", Toast.LENGTH_SHORT).show();
            new InsertAsyncTask(MainActivity.this, DatabaseClass.getInstance(MainActivity.this).daoStudent()).execute(getDataModel());
        }
    }

    public void viewData(View v) {

    }
}