package net.bitacademy.java72.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

public class ResponseFactory {
  public static ResponseEntity<String> createResponse(Object obj){
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "text/plain;charset=UTF-8");
    
    return new ResponseEntity<String>(new Gson().toJson(obj), headers, HttpStatus.OK);
    // obj는 toJson을 통해 json 형식으로 작성되고 그 문자열이 첫 번째 파라미터에 놓인다.
  }
  
  
}
