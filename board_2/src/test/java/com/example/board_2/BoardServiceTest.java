package com.example.board_2;

import com.example.board_2.board2.Board;
import com.example.board_2.board2.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService bServ;

//    @Test
    public void saveTest() {
        Board board = new Board(null, "aaa", "bbb", "spring", "1234", null, null);
        boolean result = bServ.save(board);
        assertEquals(true, result);
    }

//    @Test
    public void findByBnoTest() {
        assertNotNull(bServ.findByBno(5));
    }

//    @Test
    public void updateTest() {
        Board board = Board.builder().bno(5).title("change").content("change").password("1234").build();
        boolean result = bServ.update(board);
        assertEquals(true, result);

        board.setPassword("1111");
        result = bServ.update(board);
        assertEquals(false, result);
    }

    @Test
    public void deleteTest() {
        boolean result = bServ.delete(5, "1111");
        assertEquals(false, result);

        result = bServ.delete(5, "1234");
        assertEquals(true, result);


    }
}
