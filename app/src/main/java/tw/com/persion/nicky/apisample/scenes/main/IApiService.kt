package tw.com.persion.nicky.apisample.scenes.main

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import tw.com.persion.nicky.apisample.manager.DBLogin
import tw.com.persion.nicky.apisample.manager.DBSignup

interface IApiService {

    @FormUrlEncoded
    @POST("login")
    fun getLogIn(
        @Field("account") account:String,
        @Field("password") password:String
    ): Observable<DBLogin>

    @FormUrlEncoded
    @POST("register/participant")
    fun getSignUp(
        @Field("account") account:String,
        @Field("password") password: String,
        @Field("agree_receive_messages") agree:String = "1"
    ): Observable<DBSignup>


}
