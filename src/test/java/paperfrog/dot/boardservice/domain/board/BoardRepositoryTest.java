package paperfrog.dot.boardservice.domain.board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;


class BoardRepositoryTest {
    BoardRepository boardRepository=new BoardRepository();
    @AfterEach
    public void clear(){
        boardRepository.clear();
    }

    @Test
    @DisplayName("저장 제목 확인")
    public void save_title(){
        //given
        Board board=new Board("title","content");
        //when
        Board saveBoard=boardRepository.save(board);
        //then
        Assertions.assertThat(saveBoard.getTitle()).isEqualTo(board.getTitle());
    }
    @Test
    @DisplayName("저장 내용 확인")
    public void save_content(){
        //given
        Board board=new Board("title","content");
        //when
        Board saveBoard=boardRepository.save(board);
        //then
        Assertions.assertThat(saveBoard.getContent()).isEqualTo(board.getContent());
    }
    @Test
    @DisplayName("저장 날짜 확인")
    public void save_date(){
        //given
        Date date=new Date();
        Board board=new Board("title","content");
        //when
        Board saveBoard=boardRepository.save(board);
        //then
        Assertions.assertThat(saveBoard.getDate()).isEqualTo(board.getDate());
    }

    @Test
    @DisplayName("id로 검색")
    public void find_by_id(){
        //given
        Board board=new Board("title","content");
        //when
        Board saveBoard=boardRepository.save(board);
        Board findBoard=boardRepository.findById(saveBoard.getId());
        //then
        Assertions.assertThat(findBoard).isEqualTo(saveBoard);
    }

    @Test
    @DisplayName("전체 검색(3개 저장)")
    public void find_all_three(){
        //given
        Board board1=new Board("title1","content1");
        Board board2=new Board("title2","content2");
        Board board3=new Board("title3","content3");
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        //when
        List<Board> list=boardRepository.findAll();
        //then
        Assertions.assertThat(list.size()).isEqualTo(3);
    }
    @Test
    @DisplayName("전체 검색(100개 저장)")
    public void find_all_100(){
        //given
        for(int i=1;i<=100;i++) {
            boardRepository.save(new Board( "title1"+i, "content"+i));
        }
        //when
        List<Board> list=boardRepository.findAll();
        //then
        Assertions.assertThat(list.size()).isEqualTo(100);
    }
}

