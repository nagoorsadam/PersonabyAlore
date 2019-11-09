package com.plash.configurator.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalResponse<T> {
    private ResponseCodeJson responseCodeJson;
    private List list;
    private T object;
    private Integer reqid;
}
