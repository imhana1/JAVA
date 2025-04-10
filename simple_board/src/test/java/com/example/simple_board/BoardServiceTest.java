package com.example.simple_board;

import com.example.simple_board.board.Board;
import com.example.simple_board.board.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService bServ;

//    @Test
    public void initTest() {
        assertNotNull(bServ);
    }

    // @Test
    public void saveTest() {
        Board board = Board.builder().title("Test").content("TestTest").nickname("spring").password("1234").build();
        // BoardService 의 리턴 값은 시퀀스로 생성한 새로운 글 번호
        // 개발자는 정확한 값을 예측하기 어렵다
        int bno = bServ.save(board);
        assertEquals(true, bno > 0);
    }

    // @Test
    public void findAllTest(){
        // 바로 위 테스트에서 글을 추가했으므로 게시판에 글의 숫자는 0보다 크다
        assertEquals(true, bServ.findAll().size() > 0);
    }

    // 변경하는 테스트 -> 값이 변경되므로 재 테스트가 불가능 -> 테스트 한 다음 결과를 rollback -> 재 테스트 가능
    @Transactional
    // @Test
    public void findByBnoTest() {
        // 테스트하려는 글의 글 번호와 조회수를 먼저 확인
        // 1번 글의 조회수가 현재 0이라면
        Board board = bServ.findByBno(1);

        // 읽기에 성공했다면 board 가 null 이 아니고 bno 는 0에서 증가해서 1이 되었다
        assertEquals(1, board.getBno());
    }

    @Transactional
    // @Test
    public void updatefailTest() {
        // 비밀번호 틀림
        Board board = Board.builder().bno(1).title("change").content("change").password("1111").build();
        assertEquals(false, bServ.update(board));
    }

    @Transactional
    // @Test
    public void updatepassTest() {
        Board board = Board.builder().bno(1).title("change").content("change").password("1234").build();
        assertEquals(true, bServ.update(board));
    }

    @Transactional
    @Test
    public void deletepassTest() {
        assertEquals(true, bServ.delete(1, "1234"));
    }


}
