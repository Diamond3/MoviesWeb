package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.MovieMapper;
import lt.vu.mybatis.model.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MoviesMyBatis {
    @Inject
    private MovieMapper movieMapper;

    @Getter
    private List<Movie> allMovies;

    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @PostConstruct
    public void init() {
        this.loadAllMovies();
    }

    private void loadAllMovies() {
        this.allMovies = movieMapper.selectAll();
    }

    @Transactional
    public String createMovie() {
        movieMapper.insert(movieToCreate);
        return "/myBatis/movies?faces-redirect=true";
    }
}
