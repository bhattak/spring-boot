package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mobile {
    /*
        If we don't specify generation type we have to manually assign id
     */
    @Id
    private Integer id;
    private String company;
    private String model;
    private String year;
    private Integer price;

    public Mobile(Integer id, String company, String model, String year, Integer price) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.year = year;
        this.price = price;
    }
}
