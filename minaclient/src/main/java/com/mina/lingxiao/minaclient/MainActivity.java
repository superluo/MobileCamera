package com.mina.lingxiao.minaclient;

import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.media.lingxiao.harddecoder.Client;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private Client mClient;
    private SurfaceHolder mHolder;
    private String mIp;
    private String mPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = findViewById(R.id.surfaceview);

        Intent intent = getIntent();
        mIp = intent.getStringExtra("ip");
        mPort = intent.getStringExtra("port");



    }

    @Override
    protected void onStart() {
        super.onStart();
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(new SurfaceCallBack());
    }
    private class SurfaceCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(final SurfaceHolder surfaceHolder) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mClient = new Client(mIp,Integer.valueOf(mPort));
                    mClient.play(surfaceHolder,1080,1920);
                }
            }).start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int formate, final int width, final int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (null != mClient){
                mClient.stopPlay();
            }
        }
    }
}
