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

import javax.servlet.http.HttpServletRequest;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

/**
 * The type Tool service controller.
 */
@RestController
@RequestMapping(value = "/tool/service/api")
@Api(value = "SS Admin", description = "SS Admin API")
public class ToolServiceController extends AbstractRestHandler {

    @Autowired
    private SSManager ssManager;

    /**
     * Exchange base response.
     *
     * @param userInfo the user info
     * @return the base response
     */
    @RequestMapping(value = "/exchange",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "换取ss配置", notes = "使用秘钥交换ss配置信息")
    public BaseResponse exchange(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        userInfo.setIp(request.getRemoteAddr());
        UserConfigDto ssConfig = this.ssManager.getUserConfig(userInfo);
        response.setData(ssConfig);
        return response;
    }


    /**
     * Gets value.
     *
     * @param mac the mac
     * @return the value
     */
    @RequestMapping(value = "/lastFlow/get/{mac}",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询剩余流量")
    public BaseResponse getValue(@PathVariable String mac) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        response.setData(this.ssManager.getOverFlowByMac(mac));
        return response;
    }

    /**
     * Gets guide sentence.
     *
     * @return the guide sentence
     */
    @RequestMapping(value = "/sentence/get",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询导语")
    public BaseResponse getGuideSentence() {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        response.setData(this.ssManager.getGuideSentence());
        return response;
    }


    /**
     * Find hosts base response.
     *
     * @return the base response
     */
    @RequestMapping(value = "/host/find",
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "查询HOST")
    public BaseResponse findHosts() {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        response.setData(this.ssManager.findAllHosts());
        return response;
    }
    
}
