package com.example.demo.springAOP;


import com.example.demo.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/aop")
public class AopControl {

    @GetMapping("/hello/{id}")
    public String welcome(@PathVariable Integer id) {
        System.out.println(" *** Inside function ***");
        return "This is AOP !!!,see the console for magic . ID is " + id;
    }

    @GetMapping("/hi/around")
    public String around() {
        System.out.println(" *** Inside function ***");
        return "This is AOP around advice";
    }

    @GetMapping("/addition")
    public Integer aopAdd() {
        System.out.println("Performing addition");
        return 5 + 6;
    }

    @GetMapping("/subtraction")
    public Integer aopSubtract() {
        System.out.println("Performing subtraction");
        return 7 - 6;
    }

    @GetMapping("/exception")
    public String aopException() {
        System.out.println("Throwing Exception");
        throw new NotFoundException("Resource not found");
    }
}
