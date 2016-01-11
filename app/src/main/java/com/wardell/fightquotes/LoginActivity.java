package com.wardell.fightquotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView email;
    private EditText password;
    private Button sigin, register;
    private Firebase firebaseRef;
    private static final String TAG = "Login";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        context = this;
        firebaseRef = new Firebase("https://glaring-fire-7430.firebaseio.com/");
        email = (AutoCompleteTextView)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        sigin = (Button)findViewById(R.id.email_sign_in_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
    public boolean formValidated()
    {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordText) && !isPasswordValid(passwordText)) {
            password.setError(getString(R.string.error_invalid_password));
            return false;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailText)) {
            email.setError(getString(R.string.error_field_required));
            return false;
        } else if (!isEmailValid(emailText)) {
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }
        return true;
    }
    public void signIn(View view)
    {
        if(formValidated()) {
            firebaseRef.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Log.v(TAG, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    Intent intent = new Intent(context,QuoteListActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    // there was an error
                    Log.e(TAG, "error signing in ++ " + firebaseError.getMessage());
                    Log.e(TAG, "error signing in ++ " + firebaseError.getDetails());
                    Toast.makeText(context, "Error signing in " + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public void register(View view)
    {
        if(formValidated()) {
            firebaseRef.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    Log.v(TAG, "Successfully created user account with uid: " + result.get("uid"));
                    Toast.makeText(context, "Successfully created user account", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    // there was an error
                    Log.e(TAG, "Error creating user ++ " + firebaseError.getMessage());
                    Log.e(TAG, "Error creating user ++ " + firebaseError.getDetails());
                    Toast.makeText(context, "Error creating user " + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
