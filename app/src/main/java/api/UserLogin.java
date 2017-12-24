package api;

import entiry.HttpDefault;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by weij on 2017/12/23.
 */

public interface UserLogin {

    @POST("user/login")
    Observable<HttpDefault> login(
            @Query("name")String name,
            @Query("password")String password
    );



}
