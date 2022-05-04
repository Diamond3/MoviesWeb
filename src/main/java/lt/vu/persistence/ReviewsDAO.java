package lt.vu.persistence;

import lt.vu.entities.Review;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ReviewsDAO {

    @Inject
    private EntityManager em;

    public void persist(Review review){
        this.em.persist(review);
    }

    public Review findOne(Integer id){
        return em.find(Review.class, id);
    }

    public Review update(Review review){
        return em.merge(review);
    }
}
