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

    @Query(value = "select * from country  where continent =:continent", nativeQuery = true)
    public List<Country> countNumberOfCountries(String continent);

    @Query(value = "select * from country where id >:id", nativeQuery = true)
    public List<Country> getCountriesWithId(Integer id);

    @Query(value = "select * from country where id =(select max(id) from country)", nativeQuery = true)
    public Country getCountryWithMaximumId();

    @Query(value = "select * from country c where " + "c.continent like lower('ASIA') ", nativeQuery = true)
    public List<Country> getCountryByContinent();

    @Query(value = "select count(name) from country where continent like('%America')", nativeQuery = true)
    public Integer countCountries();

    @Query(value = "select * from country where id between 2 and 5", nativeQuery = true)
    public List<Country> getBetweenIds();

    @Modifying
    @Transactional
    @Query(value = "delete from country c where c.name=?1", nativeQuery = true)
    public Integer deleteUsingNativeQuery(String name);

    @Modifying
    @Transactional
    @Query(value = "update country c set c.name=?1 where c.id=?2", nativeQuery = true)
    public void updateUserWithId(String name,Integer id);

    @Query(value = "select * from country where id >?1 and continent =?2", nativeQuery = true)
    List<Country> findByComplexQuery(Integer id, String continent);

}
