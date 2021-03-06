package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
        public class MainActivity extends AppCompatActivity {
            //创建播放视频的控件对象
            private CustomVideoView videoview;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.beijing);
                initView();
            }

            private void initView() {
                //加载视频资源控件
                videoview = (CustomVideoView) findViewById(R.id.videoview);
                //设置播放加载路径
                videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.link));
                //播放
                videoview.start();
                //循环播放
                videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        videoview.start();
                    }
                });
            }

            //返回重启加载
            @Override
            protected void onRestart() {
                initView();
                super.onRestart();
            }

            //防止锁屏或者切出的时候，音乐在播放
            @Override
            protected void onStop() {
                videoview.stopPlayback();
                super.onStop();
            }
        }
