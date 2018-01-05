package api;

import java.util.List;

import entiry.Goods;
import entiry.HttpDefault;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/12/27.
 */

public interface GoodsAPI {

    //查询所有商品
    @GET("getgoods/result_goods")
    Observable<HttpDefault<List<Goods>>> QueryGoods();

    //查询用户发布的商品
    @POST("goods/getwithUserid")
    Observable<HttpDefault<List<Goods>>> getGoodwithUserId(
            @Query("userid") int id
    );

    //查询商品(具体)
    @POST("goods/getgoodbyname")
    Observable<HttpDefault<List<Goods>>> getGoodsWithUserid(
            @Query("name") String name
    );


    //查询商品(模糊查询)
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
    Observable<HttpDefault<List<String>>> QueryGoodsAllImgs(
            @Query("goodid") int id
    );

    //新增一条商品图片信息
    @POST("goodimgs/addimgs")
    Observable<HttpDefault<List<String>>> addGoodsImg(
            @Query("goodid") int id,
            @Query("url") String url
    );

}


