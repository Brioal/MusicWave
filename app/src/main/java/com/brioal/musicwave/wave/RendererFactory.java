package com.brioal.musicwave.wave;

import android.support.annotation.ColorInt;

/**
 * Created by brioal on 16-2-14.
 */
public class RendererFactory {
    public WaveRenderer createSimpleWaveformRender(@ColorInt int foreground, @ColorInt int background) {
        return WaveRenderer.newInstance(background, foreground);
    }
}
