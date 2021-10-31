package com.cyd.learnandroid.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyd.learnandroid.R;
import com.cyd.learnandroid.ui.view.PostFabView;

public class PostFabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morph);

        View shadowLayout = findViewById(R.id.shadow_layout);
        TextView textView = findViewById(R.id.text_view_test);
        textView.setOnClickListener((v) -> {
            Toast.makeText(this, "阿快就到啦数据来看", Toast.LENGTH_SHORT).show();
        });

        PostFabView postFabView = findViewById(R.id.post_fab);
        postFabView.setShadowLayoutView(shadowLayout);

    }
}
