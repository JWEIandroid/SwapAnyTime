package api;

import java.util.List;
import java.util.Map;

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
            @Query("tel") String tel,
            @Query("password") String password
    );

    @POST("user/register")
    Observable<HttpDefault<User>> register(
            @Query("tel") String tel,
            @Query("password") String password
    );

    @POST("user/getUserMsg")
    Observable<HttpDefault<User>> getUserdata(
            @Query("token") String token,
            @Query("id") int id
    );


    @POST("user/resetpsd")
    Observable<HttpDefault> resetPassword(
            @Query("name") String tel,
            @Query("password") String password,
            @Query("oldpsd") String oldpsd
    );


}
