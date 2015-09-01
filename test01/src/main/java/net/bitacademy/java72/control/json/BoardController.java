package net.bitacademy.java72.control.json;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import net.bitacademy.java72.domain.Board;
import net.bitacademy.java72.service.BoardService;
import net.bitacademy.java72.util.ResponseFactory;

@Controller("json.BoardController")
@RequestMapping("/json/board")
public class BoardController {
  @Autowired BoardService boardService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/delete.do")
  public Object delete(int no) {
    int count = boardService.delete(no); 
    // count에 들어가는 값은 삭제한 개수가 자동으로 들어감
    Map<String, Object> result = new HashMap<String, Object>();
    
    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    
    return result;
  }

  @RequestMapping("detail.do")
  public Object detail(int no, Board board) {
    Map<String, Object> result = new HashMap<String, Object>();
    
    board = boardService.getView(no);
    int tempView = board.getViewCount() + 1;
    board.setViewCount(tempView);
    boardService.updateView(board);
    board = boardService.get(no);
    
    // Web View로 [데이터를 전달해야 할 경우] 이 타입의 argument를 정의하고
    // 메소드 내부에서 View로 전달할 데이터를 추가해야 한다.
//    int view = board.getViewCount() + 1;
//    board.setViewCount(view);
//    boardService.views(board);
    result.put("data", boardService.get(no));

    return result;
  }
  
  @RequestMapping("/insert.do")
  public Object insert(Board board,
      @RequestParam(required=false) MultipartFile file1) {
    Map<String, Object> result = new HashMap<String, Object>();
    
//    String filename = MultipartUtils.getFilename(file1.getOriginalFilename());
//    File newPath = new File(servletContext.getRealPath("/files")
//        + "/" + filename);
//    file1.transferTo(newPath);
//    board.setAttachFile1(filename);
    
    int count = boardService.insert(board);
    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    
    return result;
  }
  
  @RequestMapping("/list.do")
  public Object list(  
      @RequestParam(required=false, defaultValue="1") int pageNo, 
      @RequestParam(required=false, defaultValue="3") int pageSize) {
    // <String>여기 담긴 값을 직접 클라이언트로 보내라
    
    Map<String, Object> result = new HashMap<String, Object>();
    // 페이징 처리
    
    result.put("pageNo", pageNo);
    int totalCount = boardService.countAll();
    int lastPageNo = totalCount / pageSize;
    if ((totalCount % pageSize) > 0) {
      lastPageNo++;
    }
    
    if (pageNo < lastPageNo) { // 다음 페이지가 있다면
      result.put("isNextPage", true);
    } else {
      result.put("isNextPage", false);
    }
    // 페이징 처리 끝.
    
    result.put("pageSize", pageSize);
    result.put("data", boardService.list(pageNo, pageSize));
    
    return result;
    
  }
  
  @RequestMapping("/update.do")
  public Object update(Board board, 
      @RequestParam(required=false) MultipartFile file1) {
    Map<String, Object> result = new HashMap<String, Object>();
    
//    String filename = MultipartUtils.getFilename(file1.getOriginalFilename());
//    File newPath = new File(servletContext.getRealPath("/files") + "/" + filename);
//    file1.transferTo(newPath);
//    
//    String tempFileName = board.getAttachFile1();
//    board.setAttachFile1(filename);
    
    int count = boardService.update(board); 
    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    return result;
  }
  
}
