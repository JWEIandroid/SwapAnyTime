package api;

import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by weij on 2017/12/23.
 */

public interface UserAPI {


    @POST("user/login")
    Observable<HttpDefault<User>> login(
            @Query("name") String name,
            @Query("password") String password
    );

    @POST("user/register")
    Observable<HttpDefault> register(
            @Query("name") String name,
            @Query("password") String password
            );

    @POST("user/register")
    Observable<HttpDefault> resetPassword(
            @Query("name") String name,
            @Query("password") String password
    );

    //获取用户头像
    @POST("userimg/getimgbyid")
    Observable<HttpDefault<String>> getUserHeadImg(
            @Query("userid") int id
    );



}
