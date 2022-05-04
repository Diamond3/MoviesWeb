package lt.vu.persistence;

import lt.vu.entities.Director;
import lt.vu.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MoviesDAO {

    @Inject
    private EntityManager em;

    public List<Movie> loadAll() {
        return em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Movie movie){
        this.em.persist(movie);
    }

    public Movie findOne(Integer id) {
        return em.find(Movie.class, id);
    }

    public List<Director> loadDirectors(Integer id) {
//        Movie movie = findOne(id);
//        List<Director> dir = em.createNamedQuery("Director.findAll", Director.class).getResultList();
//        for (Director d: dir) {
//            if (dir.movies.contains)
//        }
        return null;
    }
}
