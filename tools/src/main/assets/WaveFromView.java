import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

/**
 * Created by brioal on 16-2-14.
 */
public class WaveFromView extends View {
    private WaveRenderer mRenderer; // 绘制类
    private byte[] mWaveform; // 波纹形状

    public WaveFromView(Context context) {
        super(context);
    }

    public WaveFromView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveFromView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public WaveFromView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setRenderer(WaveRenderer renderer) {
        mRenderer = renderer;
    }

    public void setWaveform(byte[] waveform) {
        mWaveform = Arrays.copyOf(waveform, waveform.length); // 数组复制
        invalidate(); // 设置波纹之后, 需要重绘
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRenderer != null) {
            mRenderer.render(canvas, mWaveform);
        }
    }
}
