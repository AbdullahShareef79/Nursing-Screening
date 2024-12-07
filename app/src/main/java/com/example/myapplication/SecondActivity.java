package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText inputFirstName, inputOccupation, inputAllergies;
    private RadioGroup vaccinationGroup, medicationsGroup;
    private Button submitButton, backButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        inputFirstName = findViewById(R.id.inputFirstName);
        inputOccupation = findViewById(R.id.inputOccupation);
        inputAllergies = findViewById(R.id.inputAllergies);
        vaccinationGroup = findViewById(R.id.vaccinationGroup);
        medicationsGroup = findViewById(R.id.medicationsGroup);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton);
        exitButton = findViewById(R.id.exitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = inputFirstName.getText().toString().trim();
                String occupation = inputOccupation.getText().toString().trim();
                String allergies = inputAllergies.getText().toString().trim();

                int selectedVaccinationId = vaccinationGroup.getCheckedRadioButtonId();
                int selectedMedicationsId = medicationsGroup.getCheckedRadioButtonId();

                String vaccinationStatus = (selectedVaccinationId != -1) ?
                        ((RadioButton) findViewById(selectedVaccinationId)).getText().toString() : "Unselected";
                String medicationsStatus = (selectedMedicationsId != -1) ?
                        ((RadioButton) findViewById(selectedMedicationsId)).getText().toString() : "Unselected";

                if (firstName.isEmpty() || occupation.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Please fill in required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    String summary = "Name: " + firstName + "\n" +
                            "Occupation: " + occupation + "\n" +
                            "Allergies: " + allergies + "\n" +
                            "Vaccination Status: " + vaccinationStatus + "\n" +
                            "Medications: " + medicationsStatus;
                    showSummaryDialog(summary);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitConfirmationDialog();
            }
        });
    }

    private void showSummaryDialog(String summary) {
        new AlertDialog.Builder(this)
                .setTitle("Form Summary")
                .setMessage(summary)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
