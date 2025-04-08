package com.example.mybatis5.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {
    @Autowired
    private MemoDao memoDao;

    // 글을 작성 -> 작성한 글 읽기로 이동
    public int save(Memo memo) {
        // 현재 memo 의 mno 는 값이 없다

        // Dao 에서 @SelectKey 를 이용해서 memo 의 mno 에 시퀀스 값을 저장
        memoDao.save(memo);

        // Dao 호출이 끝나고 하면, Dao 가 생성해서 저장한 mno 값이 memo 에 들어있다
        return memo.getMno();
    }

    public List<Memo> findAll(){
        return memoDao.findAll();
    }

    public Optional<Memo> findByMno(int mno) {
        return memoDao.findByMno(mno);
    }

    public boolean update(Memo memo) {
        return memoDao.update(memo) > 0;
    }

    public boolean delete(int mno) {
        return memoDao.delete(mno) > 0;
    }


}
