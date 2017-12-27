package entiry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weij on 2017/12/24.
 */

public class HttpDefault<T> {

    //    {
//            "data": "success",
//            "error_code": "0",
//            "message": "200"
//    }
    @SerializedName("error_code")
    private int error_code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public int getError_code() {
        return error_code;
    }

    public HttpDefault setError_code(int error_code) {
        this.error_code = error_code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpDefault setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public HttpDefault setData(T data) {
        this.data = data;
        return this;
    }
}
