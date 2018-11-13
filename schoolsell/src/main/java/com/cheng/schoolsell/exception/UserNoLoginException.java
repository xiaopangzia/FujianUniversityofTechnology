package com.cheng.schoolsell.exception;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-19
 * Time: 上午10:04
 */
@Getter
public class UserNoLoginException extends RuntimeException{

    private String url;

    public UserNoLoginException(String url) {
        this.url = url;
    }


}
