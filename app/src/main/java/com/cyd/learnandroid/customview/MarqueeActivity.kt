package com.cyd.learnandroid.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.R
import com.cyd.learnandroid.databinding.ActivityMarqueeBinding
import com.gongwen.marqueen.SimpleMF
import com.gongwen.marqueen.SimpleMarqueeView

class MarqueeActivity : AppCompatActivity() {

    var viewBinding: ActivityMarqueeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMarqueeBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        val textList = listOf("撒打算看两节课极乐空间卢克","s啊实打实大奥多所多")

        val marqueeView : SimpleMarqueeView<String>? = viewBinding?.marqueeView as SimpleMarqueeView<String>?

        var marqueeFactory = SimpleMF<String>(this)

        marqueeFactory.data = textList

        marqueeView?.setMarqueeFactory(marqueeFactory)

        marqueeView?.startFlipping()



    }
}