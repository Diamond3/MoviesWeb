package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Review;
import lt.vu.persistence.ReviewsDAO;
import lt.vu.rest.contracts.ReviewDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/reviews")
public class ReviewsController {

    @Inject
    @Setter @Getter
    private ReviewsDAO reviewsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Review review = reviewsDAO.findOne(id);
        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setName(review.getName());

        reviewDto.setMovieTitle(review.getMovie().getTitle());

        return Response.ok(reviewDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer reviewId,
            ReviewDto reviewData) {
        try {
            Review existingReview = reviewsDAO.findOne(reviewId);
            if (existingReview == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingReview.setName(reviewData.getName());
            reviewsDAO.update(existingReview);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
