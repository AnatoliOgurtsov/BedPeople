package by.a_ogurtsov.bedpeoples.AsyncTasks;


import android.content.Context;
import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.Constants;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;

public class MyAsyncTaskViewAll extends AsyncTask <ArrayList<Face>, Void, FaceList>{
    private MyAdapter myAdapter;
    private Context context;
    static public FaceList facesAllBase;

    public MyAsyncTaskViewAll(MyAdapter myAdapter, Context context) {
        this.context = context;
        this.myAdapter = myAdapter;
    }

    @Override
    protected FaceList doInBackground(ArrayList<Face>... params) {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return template.getForObject(Constants.URL.GET_PEOPLE, FaceList.class);

    }

    @Override
    protected void onPostExecute(FaceList faces) {
        facesAllBase = faces;
        myAdapter.setM_myData(faces);
        myAdapter.notifyDataSetChanged();
    }


}

