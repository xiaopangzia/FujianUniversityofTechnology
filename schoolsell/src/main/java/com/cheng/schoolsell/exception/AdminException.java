package com.cheng.schoolsell.exception;

import com.cheng.schoolsell.enums.AdminResultEnum;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午8:39
 */
@Getter
public class AdminException extends RuntimeException {

    private String url;

    private String token;

    public AdminException(String message, String url) {
        super(message);
        this.url = url;
    }

    public AdminException(String message, String url, String token) {
        super(message);
        this.url = url;
        this.token = token;
    }

    public AdminException(AdminResultEnum adminResultEnum) {
        super(adminResultEnum.getMessage());
        this.url = adminResultEnum.getUrl();
    }

    public AdminException(AdminResultEnum adminResultEnum,String token) {
        super(adminResultEnum.getMessage());
        this.url = adminResultEnum.getUrl();
        this.token = token;
    }

}
