package tw.com.persion.nicky.apisample.scenes.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*

open class BaseActivity : AppCompatActivity() {

    open var rate: Float = 0.0F
    open var screenWidth: Float = 0.0F
    open var screenHeight: Float = 0.0F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseRate: Float = 640F / 360F
        screenWidth = resources.displayMetrics.widthPixels.toFloat()
        screenHeight = resources.displayMetrics.heightPixels.toFloat()
        rate = screenHeight / screenWidth / baseRate
    }

    fun hideStatusBar() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent))
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
    }

    fun fullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    fun setStatusBarColor(color: Int) {
        window.statusBarColor = getResColor(color)
    }

    fun getResColor(name: Int): Int = ContextCompat.getColor(this, name)

    fun startActivity(targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        startActivity(intent)
    }

    fun startActivity(bundle: Bundle, targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startActivityResult(bundle: Bundle?, targetClass: Class<*>) {
        val intent = Intent(this, targetClass)
        bundle?.let {
            intent.putExtras(it)
        }
        this.startActivityForResult(intent, 200)
    }

    fun toastMsg(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    fun toastMsg(resMsg: Int) = Toast.makeText(this, resMsg, Toast.LENGTH_SHORT).show()

    fun hideKeyBoard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(Objects.requireNonNull(currentFocus)?.windowToken, 0)
        } catch (e: NullPointerException) {
            Log.e("hideKeyBoard", e.toString())
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyBoard()
        return super.dispatchTouchEvent(ev)
    }
}