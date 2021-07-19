package com.example.demo.repository;

import com.example.demo.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepo extends CrudRepository<Dish, Long> {
    @Query(value = "select * from dish where country=?1", nativeQuery = true)
    public Dish findDishUsingCountry(String country);

    @Query(value = "select * from dish where id=?1", nativeQuery = true)
    public Dish findDishForId(Long id);
}
