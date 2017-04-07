package com.ss.server.api.rest;

import com.ss.server.domain.HostDto;
import com.ss.server.domain.SSKeyRequest;
import com.ss.server.domain.in.SentenceRequest;
import com.ss.server.domain.out.BaseResponse;
import com.ss.server.entity.SSKey;
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

/**
 * The type Ss admin controller.
 */
@RestController
@RequestMapping(value = "/ss/admin/api")
@Api(value = "SS Admin", description = "SS Admin API")
public class SSAdminController extends AbstractRestHandler {

    @Autowired
    private SSManager ssManager;

    /**
     * Key generation base response.
     *
     * @param request the request
     * @return the base response
     */
    @RequestMapping(value = "/key",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "生成秘钥")
    public BaseResponse keyGeneration(@RequestBody SSKeyRequest request) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        SSKey ssKey = ssManager.generateKey(request.getLength(), request.getKeyHost(), request.getFlow(), request.getKeyType());
        response.setData(ssKey);
        return response;
    }


    /**
     * Create guide sentence base response.
     *
     * @param request the request
     * @return the base response
     */
    @RequestMapping(value = "/sentence/create",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建导语")
    public BaseResponse createGuideSentence(@RequestBody SentenceRequest request) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        this.ssManager.saveGuideSentence(request);
        return response;
    }


    /**
     * Create guide sentence base response.
     *
     * @return the base response
     */
    @RequestMapping(value = "/host/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建Host")
    public BaseResponse createHost(@RequestBody HostDto hostDto) {
        BaseResponse response = new BaseResponse(RESPONSE_SUCCESS);
        this.ssManager.createHost(hostDto.getIp());
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
