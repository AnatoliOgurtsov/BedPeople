package by.a_ogurtsov.bedpeoples.AsyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import by.a_ogurtsov.bedpeoples.Constants;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.Interfaces.Callbacks;

public class MyAsyncTaskViewAll extends AsyncTask <ArrayList<Face>, Void, FaceList>{
    /*private MyAdapter myAdapter;*/
    private Context context;
    private FaceList base;
    Callbacks callback;

    public MyAsyncTaskViewAll(/*MyAdapter myAdapter,*/ Context context) {
        this.context = context;
       /* this.myAdapter = myAdapter;*/
    }

    public MyAsyncTaskViewAll() {
    }

    public MyAsyncTaskViewAll(Callbacks callback) {
        this.callback = callback;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Begin!!!!!!!!");
    }

    @Override
    protected FaceList doInBackground(ArrayList<Face>... params) {
        System.out.println("RUNNING!!!!!!!!");
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template.getForObject(Constants.URL.GET_PEOPLE, FaceList.class);
    }

    @Override
    protected void onPostExecute(FaceList faces) {
        super.onPostExecute(faces);
        System.out.println("END!!!!!!!!");
        callback.returnDatabase(faces);

    }


}

