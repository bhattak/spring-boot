package com.example.demo.service;

import com.example.demo.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @PersistenceContext
    private EntityManager entityManager;
//  private static final Logger LOGGER= LoggerFactory.getLogger(MovieServiceImpl.class);


    @Override
    public Movie addMovies(Movie movie) {
        entityManager.persist(movie);
        return movie;
    }

    @Override
    public String getLog() {
        log.info("This is informative message");
        return "This si logging implementation";
    }
}
