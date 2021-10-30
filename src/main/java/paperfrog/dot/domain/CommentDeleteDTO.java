package paperfrog.dot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteDTO {
    private Long commentId;
    private Long memberId;
}
