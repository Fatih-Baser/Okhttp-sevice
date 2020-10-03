package com.example.webbaglama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webbaglanti();

    }




    public void webbaglanti(){
        mTextViewResult = findViewById(R.id.text_view_result);
        OkHttpClient client = new OkHttpClient();
        String url = "http://moodytest-env.eba-mmzgp9iv.eu-central-1.elasticbeanstalk.com/api/categories";
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6Ikc3SEFpVGRrb3VzWnF3cDlOYlRkaitETllXWE5aSVFhaVJ1QzI1aVFoaDA9IiwibmJmIjoxNjAxNjc0MTkwLCJleHAiOjE2MDE3NjA1OTAsImlhdCI6MTYwMTY3NDE5MH0.7TDSiI-skVHFcg2CaqzL5h_Nalb-X8DQpSAnTLzgv2c")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println(myResponse);
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}