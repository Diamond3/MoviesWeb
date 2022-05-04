package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Movie;
import lt.vu.entities.Review;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MoviesDAO;
import lt.vu.persistence.ReviewsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ReviewsForMovie implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private ReviewsDAO reviewsDAO;

    @Getter @Setter
    private Movie movie;

    @Getter @Setter
    private Review reviewToCreate = new Review();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.movie = moviesDAO.findOne(movieId);
    }

    @Transactional
    @LoggedInvocation
    public void createReview() {
        reviewToCreate.setMovie(this.movie);
        reviewsDAO.persist(reviewToCreate);
    }
}
