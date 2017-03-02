package com.winter.shaderview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private ShaderView mShaderView;
    private Button mBtnTop, mBtnTopLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShaderView = (ShaderView) findViewById(R.id.sv);
        mBtnTop = (Button) findViewById(R.id.btn_top);
        mBtnTopLeft = (Button) findViewById(R.id.btn_top_left);

        mBtnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShaderView.setRectBitmap(R.drawable.corner_top);
            }
        });

        mBtnTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShaderView.setRectBitmap(R.drawable.corner_top_bottom_left);
            }
        });
    }
}
