package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDTO {
    private String text;
    private Long writerId;
}
