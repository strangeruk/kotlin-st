package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val builder1 = getNotificationBuilder("channel1", "첫번째 채널")
            builder1.setSmallIcon(android.R.drawable.ic_menu_search)
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder1.setLargeIcon(bitmap)
            builder1.setNumber(100)
            builder1.setContentTitle("Title 1")
            builder1.setContentText("MESSAGE 1")

            val notification = builder1.build()

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            manager.notify(10, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림 메시지를 관리하는 객체를 추출한다.
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //채널객체생성
            val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            // 메시지 출력시 단말기 LED를 사용할 것인가.
            channel.enableLights(true)
            channel.lightColor = Color.RED

            channel.enableVibration(true)

            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, id)
            return builder


        } else {
            val builder = NotificationCompat.Builder(this)
            return builder
        }
    }
}