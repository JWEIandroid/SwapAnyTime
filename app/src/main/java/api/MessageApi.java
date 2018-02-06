package api;

import java.util.List;

import entiry.Comment;
import entiry.HttpDefault;
import entiry.MessageBoard;
import entiry.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by weijie on 2018/2/5.
 */

public interface MessageApi {


    /**
     * 插入一条评论
     *
     * @param userid
     * @param receiverid
     * @param goodsid
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("comment/save")
    Observable<HttpDefault<String>> insertOneCommment(
            @Query("userid") int userid,
            @Query("receiverid") int receiverid,
            @Query("goodsid") int goodsid,
            @Field("content") String content
    );

    /**
     * 删除一条评论
     *
     * @param userid
     * @param date
     * @return
     */
    @FormUrlEncoded
    @POST("commment/deletebyuseridanddate")
    Observable<HttpDefault<String>> deleteOneCommment(
            @Query("userid") int userid,
            @Field("date") String date
    );


    /**
     * 查询一个商品的全部评论
     *
     * @param goodsid
     * @return
     */
    @POST("comment/getcommmentByGoodsId")
    Observable<HttpDefault<List<Comment>>> SelectAllCommment(
            @Query("goodsid") int goodsid
    );


//    ============ 留言板 ==============================================

    /**
     * 查询两个用户的留言记录
     *
     * @param userid
     * @param receiverid
     * @return
     */
    @FormUrlEncoded
    @POST("message/getallmessagewith2id")
    Observable<HttpDefault<List<MessageBoard>>> SelectAllMessage(
            @Query("userid") int userid,
            @Query("receiverid") int receiverid
    );

    /**
     * 根据一件商品评论的全部用户信息
     *
     * @param receiverid
     * @return
     */
    @POST("message/getuserwithreceiverid")
    Observable<HttpDefault<List<User>>> getUsersFromReceiverid(
            @Query("receiverid") int receiverid
    );

    /**
     * 插入一条聊天记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("message/insertOneMessageRecord")
    Observable<HttpDefault<String>> insertOneMessage(
            @Query("userid") int userid,
            @Query("receiverid") int receiverid,
            @Query("goodsid") int goodsid,
            @Query("content") String content
    );


}
