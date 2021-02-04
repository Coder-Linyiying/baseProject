package com.linyy.kitty.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
