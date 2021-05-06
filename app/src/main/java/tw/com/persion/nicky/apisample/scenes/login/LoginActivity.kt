package tw.com.persion.nicky.apisample.scenes.login

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_log_in.*
import tw.com.persion.nicky.apisample.R
import tw.com.persion.nicky.apisample.scenes.signup.SignUpActivity
import tw.com.persion.nicky.apisample.scenes.base.BaseActivity
import tw.com.persion.nicky.apisample.scenes.main.MainActivity

class LoginActivity : BaseActivity() {

    private val logInViewModel: LogInViewModel = LogInViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btn_signin.setOnClickListener {
            logInViewModel.doLogIn(ed_email.text.toString(), ed_password.text.toString())
        }

        btn_signup.setOnClickListener {
            startActivity(SignUpActivity::class.java)
        }
    }

    private fun setObserver() {
        logInViewModel.resultToast.observe(this, Observer { toast ->
            toastMsg(toast)
        })

        logInViewModel.resultIsLogIn.observe(this, Observer { _ ->
            startActivity(MainActivity::class.java)
        })
    }
}