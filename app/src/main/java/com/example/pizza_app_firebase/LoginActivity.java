package com.example.pizza_app_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pizza_app_firebase.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar actionBar;

    private User user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    Button login, register;
    TextView email;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        actionBar = findViewById(R.id.actionBar);
        actionBar.setTitle("");
        setSupportActionBar(actionBar);


        //Obtener los datos del registro
        Intent originIntent = getIntent();
            String newUserEmail = originIntent.getStringExtra("email");
            String newUserPass = originIntent.getStringExtra("password");
            String newUserName = originIntent.getStringExtra("name");

        if(newUserName != null) {

            Toast.makeText(LoginActivity.this, "Bienvenido " + newUserName + ", ya puedes iniciar sesión", Toast.LENGTH_LONG).show();
        }



        login       = findViewById(R.id.buttonLogin);
        register    = findViewById(R.id.buttonRegister);
        email       = findViewById(R.id.email);
        password    = findViewById(R.id.password);

        login.setOnClickListener(v -> {
            loginUser();
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {

        if (validateFields()) return;

        mAuth.signInWithEmailAndPassword( user.email, user.password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Datos de usuario incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateFields() {

        String emailText    = email.getText().toString();
        String nameText     = password.getText().toString();

        user                = new User();
        user.email          = emailText;
        user.password       = nameText;

        if (user.email.isEmpty() || user.password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}