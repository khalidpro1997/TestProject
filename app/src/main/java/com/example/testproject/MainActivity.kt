package com.example.testproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Main looper
        //Handles message for the main thread.
        //Loops through the messages and processes them.

        //Send messages to loopers for processing
        //Connection between signals, and threads.

        //Dispatcher means switch between threads.
        //Similar to the handler + looper combination
        //Use thread pools.
        val mainlooper = mainLooper //or Looper.getMainLooper()

        Log.d("TaskThread",Thread.currentThread().name)
        GlobalScope.launch(context = Dispatchers.IO) {
            Log.d("TaskThread",Thread.currentThread().name)

            val imageUrl = URL("https://wallpaperplays.com/wallpapers/full/e/a/f/647756.jpg")
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true //Receiving data.
            connection.connect()

            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)

//            runOnUiThread{
                launch(Dispatchers.Main){
                    Log.d("TaskThread",Thread.currentThread().name)
                    image.setImageBitmap(bitmap)
                } }
//        }
    }
}