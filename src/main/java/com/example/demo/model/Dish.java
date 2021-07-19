package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "dish")
@Data
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be EMPTY !!!")
    private String name;

    @NotEmpty(message = "country cannot be EMPTY !!!")
    private String country;

    @Min(1)
    @Max(5)
    @NonNull
    private Integer rating;

    public Dish(@NotEmpty(message = "Name cannot be EMPTY !!!") String name, @NotEmpty(message = "country cannot be EMPTY !!!") String country, @NonNull @Min(1) @Max(5) Integer rating) {
        this.name = name;
        this.country = country;
        this.rating = rating;
    }
}
