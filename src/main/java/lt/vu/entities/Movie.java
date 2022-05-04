package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "select m from Movie as m")
})
@Table(name = "MOVIE")
@Getter @Setter
public class Movie {

    public Movie(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @ManyToMany()
    @JoinTable(
            name = "MOVIE_DIRECTOR",
            joinColumns = {@JoinColumn(name = "director_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )

    private List<Director> directors;
}
