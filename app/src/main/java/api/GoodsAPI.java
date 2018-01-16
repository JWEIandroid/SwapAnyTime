package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entiry.Goods;
import entiry.HttpDefault;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface GoodsAPI {

    //查询所有商品
    @POST("goods/result_goods")
    Observable<HttpDefault<List<Goods>>> QueryGoods(
            @Query("pagenum")int pagenum
    );

    //查询用户发布的商品
    @POST("goods/getwithUserid")
    Observable<HttpDefault<List<Goods>>> getGoodwithUserId(
            @Query("userid") int id
    );

//    //查询商品(具体)
//    @POST("goods/getgoodbyname")
//    Observable<HttpDefault<List<Goods>>> getGoodsWithUserid(
//            @Query("name") String name
//    );

    //新增一条商品信息
    @FormUrlEncoded
    @POST("goods/addgoods")
    Observable<HttpDefault<Goods>> addgoods(
            @Field("name") String name,
            @Field("status") String status,
            @Field("type") String type,
            @Field("price_before") float price_before,
            @Field("price_sale") float price_sale,
            @Field("description") String description,
            @Field("express") int express,
            @Field("userid") int userid
    );


    //查询商品(模糊查询)
    @FormUrlEncoded
    @POST("goods/getgoodbyname")
    Observable<HttpDefault<List<Goods>>> SearchGoods(
            @Query("name") String name
    );

    //删除商品
    @POST("goods/delwithid")
    Observable<HttpDefault<List<Goods>>> DeleteGoods(
            @Query("id") int id
    );

    //更新商品
    @FormUrlEncoded
    @POST("goods/updategoods")
    Observable<HttpDefault<String>> UpdateGoods(
            @Query("name") String name,
            @Query("imgUrls") String imgUrls,
            @Query("descrtption") String descrtption,
            @Query("date_pulish") String date_pulish,
            @Query("price_before") String price_before,
            @Query("price_after") String price_after,
            @Query("adress") String adress
    );


    //删除某一商品的全部图片
    @POST("goodimgs/deleteall")
    Observable<HttpDefault<String>> DeleteGoodsImgs(
            @Query("goodid") int id
    );

    //查询商品的全部图片
    @POST("goodimgs/getImgByGoodid")
    Observable<HttpDefault<ArrayList<String>>> QueryGoodsAllImgs(
            @Query("goodid") int id
    );

    //新增一条商品图片信息
    @FormUrlEncoded
    @POST("goodimgs/addimgs")
    Observable<HttpDefault<List<String>>> addGoodsImg(
            @Query("goodid") int id,
            @Query("url") String url
    );



    //上传多张商品图片
    @Multipart
    @POST("file/upload2")
    Observable<HttpDefault<List<String>>> uploadPics(
            @Query("id") int goodid,
            @Part MultipartBody.Part file,
            @Query("type")int type
            );

}


