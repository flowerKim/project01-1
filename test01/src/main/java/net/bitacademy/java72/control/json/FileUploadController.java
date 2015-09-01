package net.bitacademy.java72.control.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bitacademy.java72.domain.Board;
import net.bitacademy.java72.domain.FileItem;
import net.bitacademy.java72.domain.Member;
import net.bitacademy.java72.util.MultipartUtils;

@Controller("json.FileUploadController")
@RequestMapping("/json/file")
public class FileUploadController {
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/upload.do")
  public Object insert(Board board, Member member, @RequestParam(required=false) 
    MultipartFile[] file, HttpServletRequest request) throws IllegalStateException, IOException {
    Map<String, Object> result = new HashMap<String, Object>();
    
    String filename = null;
    File newPath = null;
    ArrayList<FileItem> files = new ArrayList<FileItem>();
    for (MultipartFile f : file){
      filename = MultipartUtils.getFilename(f.getOriginalFilename());
      newPath = new File(servletContext.getRealPath("/files") + "/" + filename);
      f.transferTo(newPath);
      
      files.add(new FileItem()
          .setName(filename)
          .setOriginName(f.getOriginalFilename())
          .setSize(f.getSize())
          .setUrl(request.getContextPath() + "/files/" + filename));
      System.out.printf("%s, %d\n", f.getOriginalFilename(), f.getSize());
    }
    
    result.put("data", files);
    
    return result;
  }
  
  
  
}
