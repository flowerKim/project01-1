package net.bitacademy.java72.control.json;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bitacademy.java72.domain.Member;
import net.bitacademy.java72.service.MemberService;

@Controller("json.MemberController")
@RequestMapping("/json/member")
public class MemberControl {
  @Autowired MemberService memberService;
  @Autowired ServletContext sc;
  
  @RequestMapping("/delete")
  public Object delete(int no) throws Exception {
    Map<String, Object> result = new HashMap<String, Object>();
    
    int count = memberService.delete(no);

    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    
    return result;
  }
  
  @RequestMapping("/detail")
  public Object detail(int no, Model model) {
    Map<String, Object> result = new HashMap<String, Object>();
    
    result.put("data", memberService.get(no));
    
    return result;
  }

  @RequestMapping("/insert")
  public Object insert(Member member, 
      @RequestParam(required=false) MultipartFile photo1) {
    Map<String, Object> result = new HashMap<String, Object>();
    
    int count = memberService.insert(member);
    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    
    return result;
  }
  
  @RequestMapping("/list")
  public Object list(
      @RequestParam(required=false, defaultValue="1") int pageNo, 
      @RequestParam(required=false, defaultValue="3") int pageSize) {
    
    Map<String, Object> result = new HashMap<String, Object>();

    result.put("pageNo", pageNo);
    int totalCount = memberService.countAll();
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
    result.put("data", memberService.list(pageNo, pageSize));

    return result;
  }
  
  @RequestMapping("/update")
  public Object update(Member member, 
      @RequestParam(required=false) MultipartFile photo1) {
    Map<String, Object> result = new HashMap<String, Object>();
    
    int count = memberService.update(member);
    if (count > 0) {
      result.put("data", "success");
    } else {
      result.put("data", "fail");
    }
    
    return result;
  }
  
  @RequestMapping("/existEmail")
  public Object existEmail(String email) {
    Map<String, Object> result = new HashMap<String, Object>();
    
    if (memberService.existEmail(email)) {
      result.put("data", "yes");
    } else {
      result.put("data", "no");
    }
    
    return result;
  }
  
  
}
