package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponseModel;
import com.tencent.wxcloudrun.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatMessageController {
    final Logger logger;

    public WechatMessageController(@Autowired CounterService counterService) {
        //this.counterService = counterService;
        this.logger = LoggerFactory.getLogger(CounterController.class);
    }
    @PostMapping(value = "/api/test")
    ApiResponseModel test(){
        logger.info("/api/test:callBack Test 1");
        return ApiResponseModel.ok();
    }
}
