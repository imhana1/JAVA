package com.example.demo6.service;

import com.example.demo6.dao.CommentDao;
import com.example.demo6.dao.PostDao;
import com.example.demo6.dto.PostDto;
import com.example.demo6.entity.Comment;
import com.example.demo6.entity.Post;
import com.example.demo6.util.Demo6Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;
    private static final int BLOCK_SIZE = 5;

    public PostDto.Pages findAll(int pageno, int pagesize){
        // prev, start, end, next, List<Post>
        int totalcount = postDao.count();
        List<Post> posts = postDao.findAll(pageno, pagesize);
        return Demo6Util.getPages(pageno, pagesize, BLOCK_SIZE, totalcount, posts);
    }

    public Map<String, Object> findByPno(int pno, String loginId) {
        // orElseThrow(Supplier)
        // Supplier : 입력은 없고, 출력은 있다
        // Consumer : 입력은 없고, 출력은 있다 -> 예외를 발생
        Map<String, Object> post = postDao.findByPnoWithComments(pno).orElseThrow(() -> new RuntimeException());
        if (loginId != null && post.get("writer").equals(loginId)) {
            postDao.increaseReadCnt(pno);
        }
        return post;


        // loginId : 비로그인이면 null, 로그인 했으면 로그인 아이디
        // findByPno 한 결과 Optional 에서 값을 꺼내서 post 에 저장
        // 만약 없다면 예외를 발생 -> ControllerAdvice 로 가서 오류메시지 출력
//        Post post = postDao.findByPno(pno).orElseThrow(() -> new RuntimeException());
//        List<Comment> comments = commentDao.findByPno(pno);
//        comments.forEach(a->System.out.println(a));
//        if (!post.getWriter().equals(loginId))
//            postDao.increaseReadCnt(pno);
//        // 로그인 아이디는 null 이 될 수 있기 때문에 post 를 먼저 사용해야함
//        return Map.of("post", post, "comments", comments);
    }
}


























