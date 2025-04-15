package com.example.board_2.board2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardDao bDao;
    @Autowired
    private CommentDao cDao;

    // 글 작성
    public boolean save(Board board){
        board.setWriteTime(LocalDateTime.now());
        return bDao.save(board) > 0;
    }

    // 글 목록
    public List<Board> findAll() {
        return bDao.findAll();
    }

    // 글
    public Map<String, Object> findByBno(int bno) {
        bDao.increaseReadCnt(bno);
        Board board = bDao.findByBno(bno);
        List<Comment> comments = cDao.findByBno(bno);
        return Map.of("board", board, "comments", comments);
    }

    // 글 변경
    // 사용자가 입력한 비밀번호와 저장된 비밀번호가 불일치하면 return false
    // 일치하면 변경 후 return dao.update()>0
    public boolean update(Board board) {
        String storePassword = bDao.findByBno(board.getBno()).getPassword();
        if (!board.getPassword().equals(storePassword))
            return false;
        return bDao.update(board) > 0;
    }

    // 글 삭제
    // 사용자가 입력한 비밀번호와 저장된 비밀번호가 불일치하면 return false
    // 일지하면 삭제 후 return dao.delete()>0
    public boolean delete (int bno, String password){
        String storePassword = bDao.findByBno(bno).getPassword();
        if (!password.equals(storePassword))
            return false;
        cDao.deleteByBno(bno);
        return bDao.delete(bno) > 0;

    }
}





















