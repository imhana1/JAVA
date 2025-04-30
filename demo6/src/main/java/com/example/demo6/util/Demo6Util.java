package com.example.demo6.util;

import com.example.demo6.dto.PostDto;
import com.example.demo6.entity.Post;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

public class Demo6Util {
  // try ~ catch 는 예외 처리 작업
  // throws 는 예외를 처리하지 않고, 일 시킨 사람에게 떠넘김
  // 지금은 서비스에서 업로드한 이미지를 base64 로 바꿔라 그러면 문제가 발생하면 대처도 서비스가 해야지

  public static String convertToBase64(MultipartFile file) throws IOException {
    byte[] fileBytes = file.getBytes();
    // contentType 은 파일의 형식 ex)image/jpg |  image/png
    // base64 형식으로 데이터를 브라우저에 출력할 때
    //    웹브라우저가 데이터의 종류를 모르면 저장 메뉴를 띄운다
    //    데이터 앞에 파일 형식을 지정하면, 웹브라우저가 처리함
    return "data." + file.getContentType() + ";base64," +
        Base64.getEncoder().encodeToString(fileBytes);
  }

  public static PostDto.Pages getPages(int pageno, int pagesize, int blocksize, int totalcount, List<Post> posts){
    // 전체 페이지의 개수
    // 글 개수 : 123, pagesize : 10
    // 글 개수/pagesize 는 123/10 -> 12가 나온다 -> Math.ceil(12) -> 결국 12
    // 우리가 원하는 값은 Math.ceil(12.3) -> 13이 나와야한다
    //      123/10이 12.3이려면 (double) 필요

    // Math.ceil(12.3) 의 결과는 13.0 이다. 그래서 (int) 필요
    int numberOfPages = (int)Math.ceil((double)totalcount/pagesize);

    // pageno   prev    ---> pageno/BLOCK_SIZE
    //  1 ~ 5     0           (1~4)/5 -> 0,  5/5->1     그래서(pageno-1)/5 로 나눠주면 0*5 -> 0
    //  6 ~ 10    5                                     (5 ~ 9)/5               결과는 1 *5 -> 5
    // pageno 1~5 : 0, 6~10 : 5
    int prev = (pageno - 1)/blocksize * blocksize;
    int start = prev + 1;
    int end = prev + blocksize;
    int next = end + 1;

    // 위처럼 계산하면 pageno 가 11일때 end 는 15, next 는 16이다
    // 하지만 실제 페이지의 개수는 13이므로 end 는 13, next 는 0이 되도록 수정
    if (end>=numberOfPages) {
      end = numberOfPages;
      next = 0;
    }
    return new PostDto.Pages(prev, start, end, next, pageno, posts);

  }
}
