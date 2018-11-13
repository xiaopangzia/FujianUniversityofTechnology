package com.cheng.schoolsell.exception;

import com.cheng.schoolsell.enums.BusinessResultEnum;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-14
 * Time: 下午2:30
 */
@Getter
public class BusinessException extends RuntimeException {

    private String url;

    private String token;

    public BusinessException(String message, String url) {
        super(message);
        this.url = url;
    }

    public BusinessException(BusinessResultEnum businessResultEnum) {
        super(businessResultEnum.getMessage());
        this.url = businessResultEnum.getUrl();
    }

    /*public BusinessException(BusinessResultEnum businessResultEnum,String token) {
        super(businessResultEnum.getMessage());
        this.url = businessResultEnum.getUrl();
        this.token = token;
    }*/
}
