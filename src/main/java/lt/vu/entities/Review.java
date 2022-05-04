package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Review.findAll", query = "select r from Review as r")
})
@Table(name = "REVIEW")
@Getter @Setter
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 300)
    @Column(name = "TEXT")
    private String text;

    @Column(name = "RATING")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Review() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
