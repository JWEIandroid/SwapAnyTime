package entiry;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Msg implements Parcelable {

    //评论内容
    private String content;
    //信息种类，0表示留言板，1表示评论
    private int type;
    //显示位置,0表示左边，1表示右边
    private int isLeft;

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getIsLeft() {
        return isLeft;
    }

    public Msg(String content, int type, int isLeft) {
        this.content = content;
        this.type = type;
        this.isLeft = isLeft;
    }

    private Msg(Builder builder) {
        content = builder.content;
        type = builder.type;
        isLeft = builder.isLeft;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeInt(this.type);
        dest.writeInt(this.isLeft);
    }

    public Msg() {
    }

    protected Msg(Parcel in) {
        this.content = in.readString();
        this.type = in.readInt();
        this.isLeft = in.readInt();
    }

    public static final Creator<Msg> CREATOR = new Creator<Msg>() {
        @Override
        public Msg createFromParcel(Parcel source) {
            return new Msg(source);
        }

        @Override
        public Msg[] newArray(int size) {
            return new Msg[size];
        }
    };

    public static final class Builder {
        private String content;
        private int type;
        private int isLeft;

        public Builder() {
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder isLeft(int val) {
            isLeft = val;
            return this;
        }

        public Msg build() {
            return new Msg(this);
        }
    }
}
