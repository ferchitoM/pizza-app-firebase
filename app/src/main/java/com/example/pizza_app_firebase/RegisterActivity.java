package com.example.pizza_app_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizza_app_firebase.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private User newUser;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    Button      register, login;
    TextView    name, email, password, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        register    = findViewById(R.id.buttonRegister);
        login       = findViewById(R.id.buttonLogin);

        name        = findViewById(R.id.name);
        email       = findViewById(R.id.email);
        password    = findViewById(R.id.password);
        phone       = findViewById(R.id.phone);
        address     = findViewById(R.id.address);

        register.setOnClickListener(v -> {
            uploadData();
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void uploadData() {

        if(validateFields()) return;

        mAuth.createUserWithEmailAndPassword(newUser.email, newUser.password)
             .addOnCompleteListener(this, task -> {
               if(task.isSuccessful()) {

                   newUser.password = "";
                   newUser.email = "";

                   FirebaseUser user = mAuth.getCurrentUser();
                   DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
                   db.child(user.getUid()).setValue(newUser);

                   Toast.makeText(RegisterActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                   intent.putExtra("email", newUser.email);
                   intent.putExtra("password", newUser.password);
                   intent.putExtra("name", newUser.name);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(RegisterActivity.this, "El usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
               }
             });
    }

    private boolean validateFields(){

        String nameText     = name.getText().toString();
        String emailText    = email.getText().toString();
        String passwordText = password.getText().toString();
        String phoneText    = phone.getText().toString();
        String addressText  = address.getText().toString();

        newUser             = new User();
        newUser.name        = nameText;
        newUser.email       = emailText;
        newUser.password    = passwordText;
        newUser.phone       = Long.valueOf(phoneText);
        newUser.address     = addressText;

        if(newUser.name.isEmpty() || newUser.email.isEmpty() || newUser.password.isEmpty() || phoneText.isEmpty() || addressText.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(passwordText.length() < 6){
            Toast.makeText(RegisterActivity.this, "La contraseña debe contener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

}