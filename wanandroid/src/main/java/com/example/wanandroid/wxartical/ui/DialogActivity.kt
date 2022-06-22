package com.example.wanandroid.wxartical.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.ActivityDialogBinding

class DialogActivity : BaseActivity<ActivityDialogBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb?.textView?.setOnClickListener {
            Thread() {
                Thread.sleep(3000L)
                runOnUiThread {
                    Log.d("fatal","dialog show()")
                    val dialog = AlertDialog.Builder(this)
                        .setTitle("我是Dialog")
                        .setMessage("哈哈哈哈哈或或")
                        .setCancelable(true)
                        .create()
                    dialog.show()
//                    Toast.makeText(this,"hahha",Toast.LENGTH_SHORT).show()
                }
            }.start()
        }
    }

    override fun getViewBing(): ActivityDialogBinding? {
        return ActivityDialogBinding.inflate(layoutInflater)
    }

    companion object {


        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DialogActivity::class.java)
            context?.startActivity(starter)
        }
    }
}