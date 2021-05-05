package tw.com.persion.nicky.apisample.scenes.main

import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*
import tw.com.persion.nicky.apisample.R
import tw.com.persion.nicky.apisample.scenes.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}