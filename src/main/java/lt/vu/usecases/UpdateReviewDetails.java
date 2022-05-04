package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Review;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ReviewsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateReviewDetails implements Serializable {

    private Review review;

    @Inject
    private ReviewsDAO reviewsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateReviewDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer reviewId = Integer.parseInt(requestParameters.get("reviewId"));
        this.review = reviewsDAO.findOne(reviewId);
    }

    @Transactional
    @LoggedInvocation
    public String updateReviewText() {
        try{
            reviewsDAO.update(this.review);
        } catch (OptimisticLockException e) {
            return "/reviewDetails.xhtml?faces-redirect=true&reviewId=" + this.review.getId() + "&error=optimistic-lock-exception";
        }
        return "reviews.xhtml?movieId=" + this.review.getMovie().getId() + "&faces-redirect=true";
    }
}
