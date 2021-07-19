package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepo countryRepo;

    @GetMapping("/welcome")
    public String helloThere() {
        return "Welcome to the Country-Side Mate !!!";
    }

    @PostMapping("/add")
    public Country addCountry(@RequestBody Country country) {
        return this.countryRepo.save(country);
    }

    @GetMapping("/count")
    public List<Country> countCountries(@RequestParam("continent") String continent) {
        return this.countryRepo.countNumberOfCountries(continent);
    }


    @GetMapping("/count/{id}")
    public List<Country> getALlWithId(@PathVariable Integer id) {
        return this.countryRepo.getCountriesWithId(id);
    }

    @GetMapping("/max")
    public Country getCountryWithMaxId() {
        return this.countryRepo.getCountryWithMaximumId();
    }

    @GetMapping("/continent")
    public List<Country> getCountries() {
        return this.countryRepo.getCountryByContinent();
    }

    @GetMapping("/count/like")
    public Integer countThroughLike() {
        return this.countryRepo.countCountries();
    }

    @GetMapping("/count/between")
    public List<Country> getBetween() {
        return this.countryRepo.getBetweenIds();
    }

    @DeleteMapping("/delete")
    public void deleteIt(@RequestParam("name") String name) {
        this.countryRepo.deleteUsingNativeQuery(name);
    }

    @PutMapping("/update")
    public void updateIt(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        this.countryRepo.updateUserWithId(name,id);
    }

    @GetMapping("/complex")
    public List<Country> runComplexQuery(@RequestParam("name") String name, @RequestParam("id") Integer id){
        return  this.countryRepo.findByComplexQuery(id,name);
    }

    @GetMapping("/params")
    public String getParms(@RequestParam(name = "default") String custom){
        return custom;
    }
}
