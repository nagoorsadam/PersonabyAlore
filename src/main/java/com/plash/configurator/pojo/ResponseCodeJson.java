package com.plash.configurator.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCodeJson {

    private String message;
    private Integer errorcode;
    private Integer reqId;
    public ResponseCodeJson() {

    }

    public ResponseCodeJson(String message, Integer errorcode) {
        this.message = message;
        this.errorcode = errorcode;
    }
}
