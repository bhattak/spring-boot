package com.example.demo.repository;

import com.example.demo.model.Country;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CountryRepo extends CrudRepository<Country, Long> {

    @Query("select c from Country c where c.id between 5 and 9")
    List<Country> fetchCountry();

    @Query(value = "select * from country  where continent =:continent", nativeQuery = true)
    List<Country> countNumberOfCountries(String continent);

    @Query(value = "select * from country where id >:id", nativeQuery = true)
    List<Country> getCountriesWithId(Integer id);

    @Query(value = "select * from country where id =(select max(id) from country)", nativeQuery = true)
    Country getCountryWithMaximumId();

    @Query(value = "select * from country c where " + "c.continent like lower('ASIA') ", nativeQuery = true)
    List<Country> getCountryByContinent();

    @Query(value = "select count(name) from country where continent like('%America')", nativeQuery = true)
    Integer countCountries();

    @Query(value = "select * from country where id between 2 and 5", nativeQuery = true)
    List<Country> getBetweenIds();

    @Modifying
    @Transactional
    @Query(value = "delete from country c where c.name=?1", nativeQuery = true)
    Integer deleteUsingNativeQuery(String name);

    @Modifying
    @Transactional
    @Query(value = "update country c set c.name=?1 where c.id=?2", nativeQuery = true)
    void updateUserWithId(String name, Integer id);

    @Query(value = "select * from country where id >?1 and continent =?2", nativeQuery = true)
    List<Country> findByComplexQuery(Integer id, String continent);

}
