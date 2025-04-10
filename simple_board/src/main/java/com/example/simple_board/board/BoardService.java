package com.example.simple_board.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardDao bDao;
    @Autowired
    private BoardDao boardDao;

    // 글을 저장하고 글 번호를 리턴하는 save
    public int save(Board board) {
        bDao.save(board);
        return board.getBno();
    }

    // 모든 글을 리턴하는 findAll
    public List<Board> findAll() {
        return bDao.findAll();
    }

    // 하나의 글을 리턴하는 findByBno
    public Board findByBno(int bno) {
        bDao.increaseReadCnt(bno);
        return bDao.findByBno(bno);
    }

    // 글을 변경하는 update
    // 1. 사용자로 부터 글번호, 제목, 내용, 비밀번호를 전달받아
    // 2. 저장된 글의 비밀번호와 사용자가 입력한 비밀번호가 불일치하면 변경 실패 -> false 리턴
    // 3. 일치하는 경우 글을 변경한 다음 결과를 boolean으로 리턴
    // storedPassword : 데이터베이스에 저장된 글의 비밀번호
    // board.getPassword() : 글을 변경하려고 입력한 비밀번호
    public boolean update(Board board) {
        String storePassword = bDao.findByBno(board.getBno()).getPassword();
        if (storePassword.equals(board.getPassword())){
            // storedPassword와 board.getPassword()가 일치하면 글을 변경
            bDao.update(board);
            return true;
        }
        return false;
    }

    // 글을 삭제하는 delete
    // 1. 사용자로 부터 글번호, 비밀번호를 전달받아
    // 2. 저장된 글의 비밀번호와 사용자가 입력한 비밀번호가 불일치하면 삭제 실패 -> false 리턴
    // 3. 일치하는 경우 글을 삭제한 다음 결과를 boolean으로 리턴
    public boolean delete(int bno, String password) {
        String storePassword = bDao.findByBno(bno).getPassword();
        if (storePassword.equals(password)) {
            // storePassword 와 board.getPassword()가 일치하면 글을 변경, 또는 삭제
            bDao.delete(bno);
            return true;
        }
        return false;
    }





}
