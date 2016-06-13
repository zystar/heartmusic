package com.heartmusic.service;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;


public class DownService extends Service {

        @Override
        public IBinder onBind(Intent arg0) {
                // TODO Auto-generated method stub
                return null;
        }

        
        public void onCreate() {
                // TODO Auto-generated method stub
                super.onCreate();
        }

        
        public void onDestroy() {
                // TODO Auto-generated method stub
                super.onDestroy();
        }

        
        public void onStart(Intent intent, int startId) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "服务开始", Toast.LENGTH_LONG).show();
                super.onStart(intent, startId);
        }
   
}