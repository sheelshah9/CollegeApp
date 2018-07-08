package com.example.sheel9.nirmauniversityapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class News extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener
{

    TextView newstext;
    private List<MyData> data_List;

    SliderLayout sliderLayout ;

    HashMap<String, String> newsdata ;


    boolean f=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        newstext = (TextView)findViewById(R.id.news_text);
        data_List = new ArrayList<>();
        load_data_from_server(0);


        sliderLayout.stopAutoCycle();



    }

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider", "Page Changed: " + position);
        newstext.setText(data_List.get(position).getDescription());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void load_data_from_server(final int id){
        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected void onPreExecute() {
                f=false;
            }

            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://dbitstudent.webatu.com/script.php?id="+id).build();
                newsdata = new HashMap<String, String>();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject object = array.getJSONObject(i);
                        MyData data = new MyData(object.getInt("id"), object.getString("url"), object.getString("textarea"));
                        data_List.add(data);
                        newsdata.put("Date"+Integer.toString(i),data_List.get(i).getImage_link());
                        System.out.println(data_List.get(i).getId()+"   "+data_List.get(i).getImage_link());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e){
                    System.out.println("End of content");
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                for (String name : newsdata.keySet()) {

                    TextSliderView textSliderView = new TextSliderView(News.this);

                    textSliderView
                            .description(name)
                            .image(newsdata.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit);


                    textSliderView.bundle(new Bundle());

                    textSliderView.getBundle()
                            .putString("extra", name);

                    sliderLayout.addSlider(textSliderView);


                }
                sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderLayout.setCustomAnimation(new DescriptionAnimation());
                sliderLayout.setDuration(3000);
                sliderLayout.addOnPageChangeListener(News.this);
            }
        };
        task.execute(id);
    }

}
