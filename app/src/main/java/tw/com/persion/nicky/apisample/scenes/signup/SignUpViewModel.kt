package tw.com.persion.nicky.apisample.scenes.signup

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tw.com.persion.nicky.apisample.entity.SignupResponse
import tw.com.persion.nicky.apisample.manager.api.ApiService

class SignUpViewModel {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val resultToast: MutableLiveData<String> = MutableLiveData()
    val resultIsSignUp: MutableLiveData<Unit> = MutableLiveData()

    fun doSignUp(email: String, password: String, rePassword: String) {
        if (!isEmail(email)) {
            resultToast.value = "email格式不正確"
            return
        }

        if (password != rePassword) {
            resultToast.value = "確認密碼不正確"
            return
        }

        val disposable = ApiService.Api.getSignUp(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: SignupResponse ->
                when (response.code) {
                    200 -> {
                        resultToast.value = "註冊成功"
                        resultIsSignUp.value = Unit
                    }
                    404 -> resultToast.value = "註冊失敗"
                }
            }, { throwable: Throwable ->
                Log.e("getSignUpDataAPI", throwable.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun isEmail(mail: String): Boolean {
        return !TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail)
            .matches()
    }
}