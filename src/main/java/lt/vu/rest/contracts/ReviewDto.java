package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewDto {

    private Integer Id;

    private String Name;

    private String Text;
    private String MovieTitle;

    private Integer Rating;

    private Integer MovieId;
}
