package by.a_ogurtsov.bedpeoples;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText et_user;
    private EditText et_password;
    private Button btn_reg;
    private Button btn_login;

    final int RESULT_CODE_REGISTRATION = 1;
    final int RESULT_CODE_LOGIN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_user = (EditText)findViewById(R.id.user);
        et_password = (EditText)findViewById(R.id.password);
        btn_reg = (Button)findViewById(R.id.button_reg);
        btn_login = (Button)findViewById(R.id.button_login);
    }

    public void registration (View v){
        Intent intent = new Intent();
        if (et_user.getText().toString().equals("") || et_password.getText().toString().equals(""))
            Toast.makeText(this, "Please input user name and password", Toast.LENGTH_SHORT).show();
          else {
            intent.putExtra("user", et_user.getText().toString());
            intent.putExtra("password", et_password.getText().toString());
            setResult(RESULT_CODE_REGISTRATION, intent);  // RESULT_CODE_REGISTRATION = 1
            finish();
        }  //end else
    }
    public void login (View v){
        if (et_user.getText().toString().equals("")  || et_password.getText().toString().equals(""))
            Toast.makeText(this, "Please input user name and password", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent();
            intent.putExtra("user", et_user.getText().toString());
            intent.putExtra("password", et_password.getText().toString());
            setResult(RESULT_CODE_LOGIN, intent); // RESULT_CODE_LOGIN = 2
            finish();
        } // end else
    }
}
