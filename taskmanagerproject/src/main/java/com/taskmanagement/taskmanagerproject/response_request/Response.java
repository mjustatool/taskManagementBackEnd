package com.taskmanagement.taskmanagerproject.response_request;

import lombok.Data;

@Data
public class Response {
    private String accesstoken;
    private String refreshToken;

    public Response(String accesstoken) {
        this.accesstoken = accesstoken;
        this.refreshToken = null;
    }

    public Response(String accesstoken, String refreshToken) {
        this.accesstoken = accesstoken;
        this.refreshToken = refreshToken;
    }

}
