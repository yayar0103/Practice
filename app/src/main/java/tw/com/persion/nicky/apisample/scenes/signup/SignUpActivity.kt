package tw.com.persion.nicky.apisample.scenes.signup

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_up.*
import tw.com.persion.nicky.apisample.R
import tw.com.persion.nicky.apisample.scenes.base.BaseActivity
import tw.com.persion.nicky.apisample.scenes.login.LoginActivity

class SignUpActivity : BaseActivity() {

    private val signUpViewModel: SignUpViewModel = SignUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btn_signup_signup.setOnClickListener {
            signUpViewModel.doSignUp(
                ed_signup_email.text.toString(),
                ed_signup_password.text.toString(),
                ed_signup_repassword.text.toString()
            )
        }
    }

    private fun setObserver() {
        signUpViewModel.resultToast.observe(this, Observer { toast ->
            toastMsg(toast)
        })

        signUpViewModel.resultIsSignUp.observe(this, Observer { _ ->
            startActivity(LoginActivity::class.java)
        })
    }
}