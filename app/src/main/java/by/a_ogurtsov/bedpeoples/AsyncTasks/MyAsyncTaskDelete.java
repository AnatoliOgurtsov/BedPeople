package by.a_ogurtsov.bedpeoples.AsyncTasks;


import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.Constants;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.Entity.Id;

public class MyAsyncTaskDelete extends AsyncTask <String, Void, FaceList> {
    long id;
    private MyAdapter myAdapter;
    public MyAsyncTaskDelete(long id, MyAdapter myAdapter) {
        this.id = id;
        this.myAdapter = myAdapter;
    }

    @Override
    protected FaceList doInBackground(String... params) {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        template.postForObject(Constants.URL.DELETE_ITEM + id, id, Id.class);

        return template.getForObject(Constants.URL.GET_PEOPLE, FaceList.class);
    }

    @Override
    protected void onPostExecute(FaceList faces) {
        myAdapter.setM_myData(faces);
        myAdapter.notifyDataSetChanged();
    }
}
