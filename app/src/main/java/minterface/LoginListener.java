package minterface;

import entiry.User;

/**
 * Created by weij on 2018/2/8.
 */

public interface LoginListener {

    void login(int userid);
    void not_login(String message);

}
