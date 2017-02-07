package by.a_ogurtsov.bedpeoples;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import by.a_ogurtsov.bedpeoples.Entity.Face;

public class InputActivity extends AppCompatActivity {
    final static int INPUTLAYOUT = R.layout.input;

    private EditText etName, etSurname, etCountry, etAddress, etPhone, etWhatDidHeDo;
    private AutoCompleteTextView etCity;

    private Snackbar snackbar;
    View.OnClickListener snackbarOnClickListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(INPUTLAYOUT);
        etName = (EditText) findViewById(R.id.name);
        etSurname = (EditText) findViewById(R.id.surname);
        etCountry = (EditText) findViewById(R.id.country);

        etCity = (AutoCompleteTextView) findViewById(R.id.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.city));
        etCity.setAdapter(adapter);

        etAddress = (EditText) findViewById(R.id.address);
        etPhone = (EditText) findViewById(R.id.phone);
        etWhatDidHeDo = (EditText) findViewById(R.id.whatDidHeDo);
    }



    public void btnOkey(View v){
        Intent intent = new Intent();

        if (etName.getText().toString().equals("")||etSurname.getText().toString().equals("")||etCountry.getText().toString().equals("")
                ||etCity.getText().toString().equals("")||etWhatDidHeDo.getText().toString().equals("")) {

           // Toast.makeText(this, "Please fill in the required fields", Toast.LENGTH_SHORT).show();

            snackbar = Snackbar.make(v, "Please fill in the required fields", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", snackbarOnClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { snackbar.dismiss(); }                });
            snackbar.show();
        } else {
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("surname", etSurname.getText().toString());
            intent.putExtra("country", etCountry.getText().toString());
            intent.putExtra("city", etCity.getText().toString());
            intent.putExtra("address", etAddress.getText().toString());
            intent.putExtra("phone", etPhone.getText().toString());
            intent.putExtra("whatDidHeDo", etWhatDidHeDo.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
      }  //===== end btOkey(View view)


    public void btnCancel(View v){
        setResult(RESULT_CANCELED, null);
        finish();
    }

}
