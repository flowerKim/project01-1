package net.bitacademy.java72.control;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bitacademy.java72.domain.Board;
import net.bitacademy.java72.service.BoardService;
import net.bitacademy.java72.util.MultipartUtils;

@Controller
@RequestMapping("/board")
public class BoardController {
  @Autowired BoardService boardService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/delete.do")
  public String delete(int no) throws Exception {
  
    boardService.delete(no);
    
    return "redirect:list.do";
  }

  @RequestMapping("detail.do")
  public String detail(int no, Model model, Board board) throws Exception {
    
    board = boardService.getView(no);
    int tempView = board.getViewCount() + 1;
    System.out.println(tempView);
    board.setViewCount(tempView);
    boardService.updateView(board);
    board = boardService.get(no);
    
    // Web View로 [데이터를 전달해야 할 경우] 이 타입의 argument를 정의하고
    // 메소드 내부에서 View로 전달할 데이터를 추가해야 한다.
//    int view = board.getViewCount() + 1;
//    board.setViewCount(view);
//    boardService.views(board);
    model.addAttribute("board", board);

    return "board/BoardDetail";
  }
  
  @RequestMapping("/insert.do")
  public String insert(Board board,
      @RequestParam MultipartFile file1) throws Exception {

    String filename = MultipartUtils.getFilename(file1.getOriginalFilename());
    File newPath = new File(servletContext.getRealPath("/files")
        + "/" + filename);
    file1.transferTo(newPath);
    board.setAttachFile1(filename);
    
    boardService.insert(board);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("/list.do")
  public String list(Model model, 
      @RequestParam(required=false, defaultValue="1") int pageNo, 
      @RequestParam(required=false, defaultValue="3") int pageSize) throws Exception {

    // 페이징 처리
    if (pageNo > 1) {
      model.addAttribute("prevPageNo", pageNo - 1);
    }
    
    int totalCount = boardService.countAll();
    int lastPageNo = totalCount / pageSize;
    if ((totalCount % pageSize) > 0) {
      lastPageNo++;
    }
    
    if (pageNo < lastPageNo) { // 다음 페이지가 있다면
      model.addAttribute("nextPageNo", pageNo + 1);
    }
    model.addAttribute("pageSize", pageSize);
    // 페이징 처리 끝.
    
    model.addAttribute("boards", boardService.list(pageNo, pageSize));

    return "board/BoardList";
  }
  
  @RequestMapping("/update.do")
  public String update(Board board, @RequestParam MultipartFile file1) throws Exception {

    String filename = MultipartUtils.getFilename(file1.getOriginalFilename());
    File newPath = new File(servletContext.getRealPath("/files") + "/" + filename);
    file1.transferTo(newPath);
    
    String tempFileName = board.getAttachFile1();
    
    if(filename != null) {
      board.setAttachFile1(filename);
    } else {
      board.setAttachFile1(tempFileName);
    }
    boardService.update(board);
    
    return "redirect:list.do";
  }
  
}
