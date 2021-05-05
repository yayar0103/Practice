package tw.com.persion.nicky.apisample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_log_in.*
import tw.com.persion.nicky.apisample.scenes.base.BaseActivity
import tw.com.persion.nicky.apisample.scenes.main.MainActivity
import tw.com.persion.nicky.apisample.scenes.main.MainViewModel

class LoginActivity : BaseActivity() {

    private val mainViewModel:MainViewModel = MainViewModel()
    private var email:String = ""
    private var password:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        setOnClick()
        setOberseve()
    }

    private fun setOberseve() {

        mainViewModel.resultLogInToast.observe(this, Observer { loginToast ->
            toastMsg(loginToast)
        })

        mainViewModel.resultIsLogIn.observe(this, Observer { _ ->
            startActivity(MainActivity::class.java)
        })

    }

    private fun setOnClick() {
        btn_signin.setOnClickListener {
            email = ed_email.text.toString()
            password = ed_password.text.toString()
            mainViewModel.doLogin(email, password)
        }

        btn_signup.setOnClickListener {
            startActivity(SignUpActivity::class.java)
        }
    }
}