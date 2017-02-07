package by.a_ogurtsov.bedpeoples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewAll;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.Interfaces.Callbacks;


public class StartActivity extends AppCompatActivity implements Callbacks{

    private MyAsyncTaskViewAll mytask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        setdata();
    }

    private void setdata() {
        mytask = new MyAsyncTaskViewAll((Callbacks) this);
        mytask.execute();
    }

    @Override
    public void returnDatabase(FaceList dataBase) {
        MainActivity.databaseAll = dataBase;
        setResult(RESULT_OK, null);
        finish();
    }
}
