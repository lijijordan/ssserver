package com.ss.server.api.rest;

import com.ss.server.domain.out.BaseResponse;
import com.ss.server.service.SSManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/ss/admin/api")
@Api(value = "SS Admin", description = "SS Admin API")
public class SSAdminController extends AbstractRestHandler {

    @Autowired
    private SSManager ssManager;

    @RequestMapping(value = "/key/Generation",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "生成秘钥", notes = "使用秘钥交换ss配置信息")
    public BaseResponse keyGeneration() {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        String key = ssManager.generateKey(8);
        response.setData(key);
        return response;
    }
}
