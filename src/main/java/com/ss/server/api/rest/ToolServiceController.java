package com.ss.server.api.rest;

import com.ss.server.domain.UserConfigDto;
import com.ss.server.domain.in.UserInfo;
import com.ss.server.domain.out.BaseResponse;
import com.ss.server.service.SSManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/tool/service/api")
@Api(value = "SS Admin", description = "SS Admin API")
public class ToolServiceController extends AbstractRestHandler {

    @Autowired
    private SSManager ssManager;

    @RequestMapping(value = "/exchange",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "换取ss配置", notes = "使用秘钥交换ss配置信息")
    public BaseResponse exchange(@RequestBody UserInfo userInfo) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        UserConfigDto ssConfig = this.ssManager.getConfig(userInfo);
        response.setData(ssConfig);
        return response;
    }
}
