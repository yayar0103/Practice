package tw.com.persion.nicky.apisample.scenes.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tw.com.persion.nicky.apisample.entity.LoginResponse
import tw.com.persion.nicky.apisample.manager.api.ApiService

class LogInViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val resultToast: MutableLiveData<String> = MutableLiveData()
    val resultIsLogIn: MutableLiveData<Unit> = MutableLiveData()

    fun doLogIn(email: String, password: String) {
        if (email == "" || password == "") {
            resultToast.value = "登入資訊請勿空白"
            return
        }

        val disposable = ApiService.Api.getLogIn(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: LoginResponse ->
                when (response.code) {
                    404 -> resultToast.value = "登入失敗"
                    200 -> resultIsLogIn.value = Unit
                }
            }, { throwable: Throwable ->
                Log.e("getLoginDataAPI", throwable.toString())
            })
        compositeDisposable.add(disposable)
    }
}