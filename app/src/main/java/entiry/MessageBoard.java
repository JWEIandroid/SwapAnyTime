package entiry;

/**
 * Created by weijie on 2018/2/5.
 */

public class MessageBoard {


    private int id;
    private int userid;
    private int receiverid;
    private int type;
    private String content;
    private String date;
    private User user;
    private User Receiver;
    //显示位置,0表示左边，1表示右边
    private int isLeft;

    public int getIsLeft() {
        return isLeft;
    }

    public MessageBoard setIsLeft(int isLeft) {
        this.isLeft = isLeft;
        return this;
    }

    public int getId() {
        return id;
    }

    public MessageBoard setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public MessageBoard setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public MessageBoard setReceiverid(int receiverid) {
        this.receiverid = receiverid;
        return this;
    }

    public int getType() {
        return type;
    }

    public MessageBoard setType(int type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageBoard setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDate() {
        return date;
    }

    public MessageBoard setDate(String date) {
        this.date = date;
        return this;
    }

    public User getUser() {
        return user;
    }

    public MessageBoard setUser(User user) {
        this.user = user;
        return this;
    }

    public User getReceiver() {
        return Receiver;
    }

    public MessageBoard setReceiver(User receiver) {
        Receiver = receiver;
        return this;
    }
}
