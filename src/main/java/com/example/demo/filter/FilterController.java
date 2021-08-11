package com.example.demo.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter2")
public class FilterController {

    @GetMapping("/hello")
    public String hello() {
        return "This is filter !!!";
    }
    @PostMapping("/check")
    public String postRequest() {
        return "This is POST Request !!!";
    }


}
