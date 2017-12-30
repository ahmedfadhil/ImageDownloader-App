package com.example.android.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view) {
//        https://www.google.it/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwievvyew7HYAhVEtxQKHd7uBfoQjRwIBw&url=http%3A%2F%2Fwww.starwars.com%2Fnews%2Fstar-wars-the-last-jedi-theatrical-poster-revealed&psig=AOvVaw1U1k4_KqYlcFQs-bdpk6PA&ust=1514716074560917

        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap myImage;

        try {


//            myImage = imageDownloader.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            myImage = imageDownloader.execute("http://starwarsblog.starwars.com/wp-content/uploads/2017/10/the-last-jedi-theatrical-poster-tall-A.jpg").get();
            downloadedImage.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Tapped", "Button Tapped");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = (ImageView) findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;


        }
    }
}
