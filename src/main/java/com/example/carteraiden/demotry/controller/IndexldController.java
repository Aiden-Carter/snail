package com.example.carteraiden.demotry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexldController {
    //前台模块的名
    @GetMapping("/")
    public String indxe(){

        return "index";
        //对应html文件
    }
}
