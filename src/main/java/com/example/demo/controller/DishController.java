package com.example.demo.controller;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Dish;
import com.example.demo.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cuisine")
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @GetMapping("/hello")
    public String welcome() {
        return "Welcome to the world of cuisine !!!";
    }

    @PostMapping("/add")
    public Dish addDish(@Valid @RequestBody Dish dish) {
        Dish dishh = this.dishRepo.save(dish);
        if (dishh == null) {
            throw new CustomException("Dish could not be added !!!");
        }
        return dishh;
    }

    @GetMapping("/{id}")
    public Dish getDishWithId(@PathVariable Long id) {
        Dish d = this.dishRepo.findDishForId(id);
        if (d == null) {
            throw new CustomException("Dish Not found for id " + id);
        }
        return d;
    }

    @GetMapping("/get")
    public Dish fetchDishForCountry(@RequestParam("name") String name) {
        Dish d = this.dishRepo.findDishUsingCountry(name);
        if (d == null) {
            throw new CustomException("Dish not found for " + name);
        }
        return d;
    }
}
