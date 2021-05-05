package tw.com.persion.nicky.apisample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_up.*
import tw.com.persion.nicky.apisample.scenes.base.BaseActivity
import tw.com.persion.nicky.apisample.scenes.main.MainViewModel

class SignUpActivity : BaseActivity() {

    private val mainViewModel:MainViewModel = MainViewModel()
    private var email = ""
    private var password = ""
    private var repassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setOnClick()
        setObseverse()
    }

    private fun setObseverse() {

        mainViewModel.resultSignUpToast.observe(this, Observer { signupToast ->
            toastMsg(signupToast)
        })

        mainViewModel.resultIsSignUp.observe(this, Observer { _ ->
            startActivity(LoginActivity::class.java)
        })
    }

    private fun setOnClick() {
        btn_signup_SignUp.setOnClickListener {
            email = ed_signup_email.text.toString()
            password = ed_signup_password.text.toString()
            repassword = ed_signup_repassword.text.toString()
            mainViewModel.doSignup(email, password, repassword)
        }
    }

}