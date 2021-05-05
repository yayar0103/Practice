package tw.com.persion.nicky.apisample.scenes.main

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tw.com.persion.nicky.apisample.manager.DBLogin
import tw.com.persion.nicky.apisample.manager.DBSignup
import tw.com.persion.nicky.apisample.scenes.main.ApiService.Companion.Api

class MainViewModel {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    val resultLogInToast: MutableLiveData<String> = MutableLiveData()
    val resultIsLogIn: MutableLiveData<Unit> = MutableLiveData()
    val resultSignUpToast:MutableLiveData<String> = MutableLiveData()
    val resultIsSignUp: MutableLiveData<Unit> = MutableLiveData()

    fun doLogin(email:String, password:String) {
        if (!email.equals("") && !password.equals("")){
            val disposable = Api.getLogIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: DBLogin ->
                    when(response.code){
                        404 -> resultLogInToast.value = "登入失敗"
                        200 -> resultIsLogIn.value = Unit
                    }
                }, { throwable: Throwable ->
                    Log.e("getLoginDataAPI", throwable.toString())
                })
            compositeDisposable.add(disposable)
        }else{
            resultLogInToast.value = "登入資訊請勿空白"
        }
    }

    fun doSignup(email: String, password: String, repassword:String) {
        if (isEmail(email).equals(true)) {
            if (password.equals(repassword)) {
                val disposable = Api.getSignUp(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response: DBSignup ->
                        when(response.code){
                            200 -> {
                                resultSignUpToast.value = "註冊成功"
                                resultIsSignUp.value = Unit
                            }
                            404 -> resultSignUpToast.value = "註冊失敗"
                        }
                    }, { throwable:Throwable ->
                        Log.e("getSignupDataAPI", throwable.toString())
                    })
                compositeDisposable.add(disposable)
            }else{
                resultSignUpToast.value = "確認密碼不正確"
            }
        }else{
            resultSignUpToast.value = "email格式不正確"
        }

    }

    private fun isEmail(mail:String): Boolean {
        return !TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }


}