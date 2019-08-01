package com.cheng.schoolsell.service;

import com.cheng.schoolsell.enums.PaypalPaymentIntent;
import com.cheng.schoolsell.enums.PaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cheng
 * @Date: 2018-11-29
 */
public interface PaypalService {

    /**
     *
     * @param total
     * @param currency
     * @param method
     * @param intent
     * @param description
     * @param cancelUrl
     * @param successUrl
     * @return
     */
    Payment createPayment(Double total,
                          String currency,
                          PaypalPaymentMethod method,
                          PaypalPaymentIntent intent,
                          String description,
                          String cancelUrl,
                          String successUrl) throws PayPalRESTException;

    /**
     *
     * @param paymentId
     * @param payerId
     * @return
     * @throws PayPalRESTException
     */
    Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException;

}
