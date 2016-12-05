package by.a_ogurtsov.bedpeoples.AsyncTasks;


import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.Constants;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;


public class MyAsyncTaskViewMy extends AsyncTask<Long, Void, FaceList>{
    private MyAdapter myAdapter;
    private String user;

    public MyAsyncTaskViewMy(String user, MyAdapter myAdapter) {
    this.user = user;
    this.myAdapter = myAdapter;
    }

    @Override
    protected FaceList doInBackground(Long... params) {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return  template.getForObject(Constants.URL.GET_PEOPLE, FaceList.class);
    }

    @Override
    protected void onPostExecute(FaceList faceList) {
        FaceList faceList_sort = new FaceList();
        for (Face face : faceList) {

        if (face.getUser().equals(user)) {
                faceList_sort.add(face);
            }
        }
        myAdapter.setM_myData(faceList_sort);
        myAdapter.notifyDataSetChanged();
    }
}
