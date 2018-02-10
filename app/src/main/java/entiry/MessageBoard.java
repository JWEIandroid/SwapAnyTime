package entiry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by weijie on 2018/2/5.
 */

public class MessageBoard implements Parcelable {


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

    private MessageBoard(Builder builder) {
        setId(builder.id);
        setUserid(builder.userid);
        setReceiverid(builder.receiverid);
        setType(builder.type);
        setContent(builder.content);
        setDate(builder.date);
        setUser(builder.user);
        setReceiver(builder.Receiver);
        setIsLeft(builder.isLeft);
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

    public int getIsLeft() {
        return isLeft;
    }

    public MessageBoard setIsLeft(int isLeft) {
        this.isLeft = isLeft;
        return this;
    }

    public static final class Builder {
        private int id;
        private int userid;
        private int receiverid;
        private int type;
        private String content;
        private String date;
        private User user;
        private User Receiver;
        private int isLeft;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userid(int val) {
            userid = val;
            return this;
        }

        public Builder receiverid(int val) {
            receiverid = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder Receiver(User val) {
            Receiver = val;
            return this;
        }

        public Builder isLeft(int val) {
            isLeft = val;
            return this;
        }

        public MessageBoard build() {
            return new MessageBoard(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userid);
        dest.writeInt(this.receiverid);
        dest.writeInt(this.type);
        dest.writeString(this.content);
        dest.writeString(this.date);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.Receiver, flags);
        dest.writeInt(this.isLeft);
    }

    protected MessageBoard(Parcel in) {
        this.id = in.readInt();
        this.userid = in.readInt();
        this.receiverid = in.readInt();
        this.type = in.readInt();
        this.content = in.readString();
        this.date = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.Receiver = in.readParcelable(User.class.getClassLoader());
        this.isLeft = in.readInt();
    }

    public static final Parcelable.Creator<MessageBoard> CREATOR = new Parcelable.Creator<MessageBoard>() {
        @Override
        public MessageBoard createFromParcel(Parcel source) {
            return new MessageBoard(source);
        }

        @Override
        public MessageBoard[] newArray(int size) {
            return new MessageBoard[size];
        }
    };

}

