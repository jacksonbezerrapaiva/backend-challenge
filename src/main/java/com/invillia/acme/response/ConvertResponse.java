package com.invillia.acme.response;

import com.invillia.acme.constants.Constants;
import org.springframework.stereotype.Component;

@Component
public class ConvertResponse {

    public ResponseToken retrieveLogin(String token) {
        ResponseToken response = new ResponseToken();
        response.setToken(token);
        return response;
    }

    public Response newStore() {
        Response response = new Response();
        response.setMessage(Constants.NEW_STORE_SUCCESS);
        return response;
    }

    public Response updateStore() {
        Response response = new Response();
        response.setMessage(Constants.UPDATE_STORE_SUCCESS);
        return response;
    }

    public Response newOrder() {
        Response response = new Response();
        response.setMessage(Constants.NEW_ORDER_SUCCESS);
        return response;
    }

    public Response newPayment() {
        Response response = new Response();
        response.setMessage(Constants.NEW_PAYMENT_SUCCESS);
        return response;
    }

    public Response newRefund() {
        Response response = new Response();
        response.setMessage(Constants.NEW_PAYMENT_SUCCESS);
        return response;
    }
}

