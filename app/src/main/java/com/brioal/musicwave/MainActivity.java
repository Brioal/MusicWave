package com.brioal.musicwave;

import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brioal.musicwave.wave.RendererFactory;
import com.brioal.musicwave.wave.WaveFromView;
import com.brioal.tools.summary.SummaryActivity;

//TODO 所有笔记
public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_SIZE = 256; // 获取这些数据, 用于显示
    private WaveFromView mWvWaveform;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Visualizer mVisualizer; // 音频可视化类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWvWaveform = (WaveFromView) findViewById(R.id.main_wave);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("音乐频谱测试类");
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                intent.putExtra("title", "音乐可视化谱测试");
                intent.putExtra("content", getResources().getString(R.string.content));
                startActivity(intent);
            }
        });
        RendererFactory rendererFactory = new RendererFactory();
        mWvWaveform.setRenderer(rendererFactory.createSimpleWaveformRender(ContextCompat.getColor(this, R.color.colorPrimary), Color.WHITE));
        startVisualiser();
    }


    // 设置音频线
    private void startVisualiser() {
        mVisualizer = new Visualizer(0); // 初始化
        mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                if (mWvWaveform != null) {
                    mWvWaveform.setWaveform(waveform);
                }
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {

            }
        }, Visualizer.getMaxCaptureRate(), true, false);
        mVisualizer.setCaptureSize(CAPTURE_SIZE);
        mVisualizer.setEnabled(true);
    }


}
