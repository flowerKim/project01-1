package net.bitacademy.java72.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload/fileUpload.do")
public class FileUploadServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  class FileInfo { // 파일 경로를 획득해서 img src하기 위해 이너 클래스 생성
    // 이 클래스에서만 사용할 것이므로 이너 클래스면 된다.
    String originName;
    String realFilePath;
    
    public FileInfo(String originName, String realFilePath) { 
      this.originName = originName;
      this.realFilePath = realFilePath;
    }
    
  }
  
  @Override
  protected void doPost(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {

    /*
     * 멀티 파트 형식으로 전송된 데이터를 다음과 같이 getParameter()로
     * 꺼낼 수 없다.
     * String name = request.getParameter("name");
     * String photo = request.getParameter("photo");
    */
    
    // 1. 각 파트를 자바 객체로 만들어줄 공장을 준비한다. 
    DiskFileItemFactory itemFactory = new DiskFileItemFactory();
    
    // 2. 요청 프로토콜을 분석할 객체를 준비한다.
    //    이 객체는 분석한 데이터를 ItemFactory에 넘긴다.
    //    ItemFactory는 각 파트 데이터를 객체로 만든다.
    
    ServletFileUpload upload = new ServletFileUpload(itemFactory);
    
    // 3. 멀티 파트 데이터를 분석하라!
    // parseRequest(request)의 리턴값은 ItemFactory가 만들어준 객체 목록.
   
    
    HashMap<String, Object> paramMap = new HashMap<String, Object>();
    try { // 파싱하다가 오류가 날 수 있으므로
      List<FileItem> items = upload.parseRequest(request);
      for (FileItem item : items) {
        if (item.isFormField()) {
          // isFormField : type='file'이 아닌 모든 폼 
          paramMap.put(item.getFieldName(), item.getString("UTF-8"));
          // fileupload 클래스에서는 파라미터값을 얻는 메소드가 getFieldName이당.
          // 또 여기서는 request.setCharacterEncoding이 적용되지 않기 때문에 직접 명시해야 함. 
          
        } else {
          // 업로드한 파일을 (서버에)저장할 경로 지정해야 그리로 들어가겠지?
          ServletContext context = request.getServletContext();
          String realPath = context.getRealPath("/files");
          // context에서 /files라는 경로가 어디있는지 진짜 경로를 찾아서 리턴.
          
          File filePath = new File(realPath + "/" + item.getName());
          item.write(filePath);
          // 클라이언트가 임시로 보관하고 있던 데이터를 저 경로로 옮겨버린다. 
          
          // 4. FileItem을 분석하여 name 값과 photo 값을 꺼낸다.
//          paramMap.put(item.getFieldName(), filePath.getAbsolutePath());
          paramMap.put(item.getFieldName(), new FileInfo(item.getName(), filePath.getAbsolutePath()));
          
        }
      }
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    
    
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><body>");
    out.printf("<p>이름 : %s</p>\n", paramMap.get("name"));
    FileInfo fileInfo = (FileInfo)paramMap.get("photo");
    out.printf("<p>사진 : %s</p>\n", fileInfo.realFilePath);
    out.printf("<img src='../files/%s'>\n", fileInfo.originName);
    out.println("</body></html>");
    
  }

}
