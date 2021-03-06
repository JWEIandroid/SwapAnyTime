package entiry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by weijie on 2018/2/8.
 */

public class Bill implements Parcelable {

    private int id;
    private String type;
    private float percent;
    private float count;

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }


    public int getId() {
        return id;
    }

    public Bill setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Bill setType(String type) {
        this.type = type;
        return this;
    }

    public float getPercent() {
        return percent;
    }

    public Bill setPercent(float percent) {
        this.percent = percent;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeFloat(this.percent);
        dest.writeFloat(this.count);
    }

    public Bill() {
    }

    protected Bill(Parcel in) {
        this.id = in.readInt();
        this.type = in.readString();
        this.percent = in.readFloat();
        this.count = in.readFloat();
    }

    public static final Creator<Bill> CREATOR = new Creator<Bill>() {
        @Override
        public Bill createFromParcel(Parcel source) {
            return new Bill(source);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };
}