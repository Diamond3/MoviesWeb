package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Director;
import lt.vu.entities.Movie;
import lt.vu.persistence.MoviesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Movies {

    @Inject
    private MoviesDAO moviesDAO;

    @Getter
    @Setter
    private Movie movieToCreate = new Movie();

    @Getter
    private List<Movie> allMovies;

    @Getter
    private List<Director> allDirectors;

    @PostConstruct
    public void init() {
        loadAllMovies();
    }

    @Transactional
    public void createMovie() {
        this.moviesDAO.persist(movieToCreate);
    }

    private void loadAllMovies() {
        this.allMovies = moviesDAO.loadAll();
    }
    private void loadAllDirectors(){
        //this.allDirectors = moviesDAO.loadDirectors();
    }


}
