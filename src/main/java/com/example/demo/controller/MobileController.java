package com.example.demo.controller;

import com.example.demo.model.Mobile;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/mobile")
public class MobileController {
    @PersistenceContext
    private EntityManager em;

    @GetMapping
    public String hello() {
        return "Mobile Phone !!!";
    }
    @Transactional
    @PostMapping("/add")
    public Mobile addMobile(@RequestBody Mobile mobile) {
        em.persist(mobile);
        return mobile;
    }

    @GetMapping("/{id}")
    public  Mobile getMobile(@PathVariable int id){
        Mobile m= em.find(Mobile.class,id);
        return  m;
    }

}
