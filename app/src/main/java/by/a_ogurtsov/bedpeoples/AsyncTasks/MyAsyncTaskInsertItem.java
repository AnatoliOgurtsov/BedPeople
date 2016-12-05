package by.a_ogurtsov.bedpeoples.AsyncTasks;


import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.Constants;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;

public class MyAsyncTaskInsertItem extends AsyncTask <Face, Void, FaceList>{
    private Face my_Face;
    private MyAdapter myAdapter;
   public MyAsyncTaskInsertItem(Face face, MyAdapter myAdapter){
       my_Face = face;
       this.myAdapter = myAdapter;
   }

    @Override
    protected FaceList doInBackground(Face... params) {
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        template.postForObject(Constants.URL.SAVE_ITEM, my_Face, Face.class);

        return template.getForObject(Constants.URL.GET_PEOPLE, FaceList.class);
    }

    @Override
    protected void onPostExecute(FaceList faces) {
        myAdapter.setM_myData(faces);
        myAdapter.notifyDataSetChanged();
    }
}
