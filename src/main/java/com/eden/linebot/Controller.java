package com.eden.linebot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/")
    public String helloWorld(){
        return "hello world";
    }

    @RequestMapping("/user")
    public String test(){
        return "this is test";
    }


}
