package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/welcome")
    public String hello() {
        return "Welcome to the world of MOVIES !!!";
    }

    @Transactional
    @PostMapping("/add")
    public Movie addMovies(@RequestBody Movie movie) {
        entityManager.persist(movie);
        return movie;
    }
    /*
        Implementing service layer
        we can save data using GET as well but request is exposed to URL
    */
    @Transactional
    @GetMapping("/add/new")
    public ResponseEntity<String> addMoviesService(@RequestBody Movie movie) {
       Movie m= movieService.addMovies(movie);
       return new ResponseEntity<String>("Movie has been added", HttpStatus.OK);
    }

    /*
       Logging
    */
    @GetMapping("/log")
    public  String checkLogMessage(){
        return movieService.getLog();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Integer id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie == null) {
            throw new NotFoundException("Movie not found for id " + id);
        }
        return movie;
    }

    @GetMapping("/query")
    public List<Movie> queryMovie() {
        List<Movie> m = entityManager.createQuery("select m from Movie m ").getResultList();
        return m;
    }

    /*
        There is confusing method
     */
//    @PutMapping("/update/{id}")
//    public Integer updateTheMovie(@PathVariable Integer id) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Movie m = em.find(Movie.class, id);
//        if (m == null) {
//            throw new NotFoundException("Movie NOT found with id " + id);
//        }
//
//        //em.detach(m);
////        Movie newMovie = (Movie) entityManager.createQuery("update Movie m  set name=?1 where m.id=?2").setParameter(1, "New Movie").setParameter(2, id);
//        Movie newMovie = (Movie) em.createQuery("update Movie   set name=: name where id=: id").setParameter("name", "New Name").setParameter("id", id).getResultList();
////        em.getTransaction().commit();
//
//        Query q = em.createQuery("update Movie   set name=: name where id=: id");
//        q.setParameter("name", "Name");
//        q.setParameter("id", id);
//        Integer result = q.executeUpdate();
//        em.getTransaction().commit();
//        return result;
//    }

    /*
        This method is working fine
        returns boolean
     */
//    @DeleteMapping("/delete/{id}")
//    public boolean delIt(@PathVariable Integer id) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Movie m = em.find(Movie.class, id);
//        if (m == null) {
//            throw new NotFoundException("Movie NOT found with id " + id);
//        }
//        em.remove(m);
//        em.getTransaction().commit();
//        return true;
//    }

    /*
        This method is working fine
     */

    @GetMapping("/check/{id}")
    public List<Movie> checkMovie(@PathVariable Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select m from Movie m where m.id =: id");
        q.setParameter("id", id);
        List<Movie> m = (List<Movie>) q.getResultList();
        if (m.isEmpty()) {
            throw new NotFoundException("Movie NOT found with id " + id);
        }
        return m;
    }


    /*
        This method is working fine
     */
//    @GetMapping("/country")
//    public List<Movie> getRangeData(@RequestParam("country") String country) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
//        EntityManager em = emf.createEntityManager();
//        List<Movie> m = (List<Movie>) em.createQuery("select m from Movie m where m.country =: country ").setParameter("country", country).getResultList();
//        if (m.isEmpty()) {
//            throw new NotFoundException("Movie NOT found with country " + country);
//        }
//        return m;
//    }

    /*
    This update query works fine
     */
  /*  @PutMapping("/test/update/{id}")
    public Integer testUpdate(@RequestParam("name") String movie_name, @RequestParam("country") String country, @PathVariable Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Integer result = em.createQuery("update Movie m set m.name =: movie_name ,m.country =: country where m.id =: id").
                setParameter("movie_name", movie_name).setParameter("country", country)
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        return result;
    }*/

    /*
        This query works fine
     */
//    @GetMapping("/test/country")
//    public List<Movie> testGet(@RequestParam("country") String country) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
//        EntityManager em = emf.createEntityManager();
//        List<Movie> result = em.createQuery("select m from Movie m where m.country =: country")
//                .setParameter("country", country)
//                .getResultList();
//        return result;
//    }

    /*
    This method works fine
    But when using TransactionScoped em shows following error -->>
    Not allowed to create transaction on shared EntityManager - use Spring transactions or EJB CMT instead
     */
//    @DeleteMapping("/test/delete/{id}")
//    public Integer testDelete(@PathVariable Integer id) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xmlconfig");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Movie m = em.find(Movie.class, id);
//        if (m == null) {
//            throw new NotFoundException("Movie with id " + id + " not found !!!");
//        }
//        Integer result = em.createQuery("delete from Movie m where m.id =: id")
//                .setParameter("id", id)
//                .executeUpdate();
//        em.getTransaction().commit();
//        return result;
//    }


    @GetMapping("/test/entity/{id}")
    public List<Movie> testEntity(@PathVariable Integer id) {
        List<Movie> m = entityManager
                .createQuery("select m from Movie m where id =: id")
                .setParameter("id", id)
                .getResultList();
        if (m.isEmpty()) {
            throw new NotFoundException("Movie with id " + id + " not found !!!");
        }
        return m;
    }

    @Transactional
    @PutMapping("/test/update2/{id}")
    public Integer update2Query(@PathVariable Integer id){
        Integer result= entityManager
                .createQuery("update Movie m set m.name =: movie_name ,m.country =: country where m.id =: id")
                .setParameter("movie_name","UpdatedMovie")
                .setParameter("country","Nepal")
                .setParameter("id",id)
                .executeUpdate();
        return result;
    }
}
