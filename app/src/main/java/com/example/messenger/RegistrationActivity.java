package com.example.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextAge;

    private Button buttonSignUp;

    private RegistrationViewModel registrationViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();

        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                String name = editTextName.getText().toString().trim();
                String surname = editTextSurname.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                if (email.equals("") ||
                        password.equals("") ||
                        name.equals("") ||
                        surname.equals("") ||
                        age.equals("")) {

                    Toast.makeText(
                            RegistrationActivity.this,
                            "Invalid input params",
                            Toast.LENGTH_SHORT).show();
                } else {
                    registrationViewModel.signUp(
                            email,
                            password,
                            name,
                            surname,
                            Integer.parseInt(age));
                }
            }
        });
    }

    private void observeViewModel() {
        registrationViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(
                            RegistrationActivity.this,
                            errorMessage,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrationViewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = UsersActivity.newIntent(
                            RegistrationActivity.this,
                            firebaseUser.getUid());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initViews() {

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextAge = findViewById(R.id.editTextAge);

        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }
}