package by.a_ogurtsov.bedpeoples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final int LOGINLAYOUT = R.layout.login;

    private EditText etUser;
    private EditText etPassword;
    private Button btnReg;
    private Button btnLogin;

    final int RESULT_CODE_REGISTRATION = 1;
    final int RESULT_CODE_LOGIN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LOGINLAYOUT);
        etUser = (EditText)findViewById(R.id.user);
        etPassword = (EditText)findViewById(R.id.password);
        btnReg = (Button)findViewById(R.id.button_reg);
        btnLogin = (Button)findViewById(R.id.button_login);
    }

    public void registration (View v){
        Intent intent = new Intent();
        if (etUser.getText().toString().equals("") || etPassword.getText().toString().equals(""))
            Toast.makeText(this, "Please input user name and password", Toast.LENGTH_SHORT).show();
          else {
            intent.putExtra("user", etUser.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            setResult(RESULT_CODE_REGISTRATION, intent);  // RESULT_CODE_REGISTRATION = 1
            finish();
        }  //end else
    }
    public void login (View v){
        if (etUser.getText().toString().equals("")  || etPassword.getText().toString().equals(""))
            Toast.makeText(this, "Please input user name and password", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent();
            intent.putExtra("user", etUser.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            setResult(RESULT_CODE_LOGIN, intent); // RESULT_CODE_LOGIN = 2
            finish();
        } // end else
    }
}
