package net.bitacademy.java72.domain;

import java.io.Serializable;

/*
 * Sirialzable 인터페이스
 * -> 클래스의 데이터를 바이트 배열로 직렬화할 수 있음을 표시할 때 사용.
 * -> 메소드가 없다. 단지 JVM에게 직렬화를 허용한다고 표시하는 용도로 사용
 */

import java.sql.Date;

public class Board implements Serializable {
  /**
   * 직렬화될 때 데이터의 버젼 정보도 함께 저장된다.
   * (한글 hwp 저장할 때 파일에 버전 정보도 함께 저장되는 것과 같은 원리)
   * 나중에 바이트 배열을 다시 객체로 복원할 때 버젼을 검사하는 데 사용한다.
   * 
   */
  private static final long serialVersionUID = 1L;
 
  // DB와 이름을 일치시키지 마라. DB는 DB고 자바는 자바다.
  protected int    no;
  protected String title;
  protected String content;
  protected Date   createDate;
  protected int    viewCount;
  protected String attachFile1;
  protected String attachFile2;
  protected String attachFile3;
  protected String password;
  protected String yyyyMMdd; // for JSON
  // 서브 클래스에서 필드를 추가하고 확장할 수 있기 때문에 protected 접근 제어자를
  // 통해 접근할 수 있도록 하는 것이 관례다.
  
  public String getYyyyMMdd() {
    return yyyyMMdd; // yyyy-[M]M-[d]d
  }
  public void setYyyyMMdd(String yyyyMMdd) {
    this.setCreateDate(Date.valueOf(yyyyMMdd));
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.yyyyMMdd = createDate.toString();
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public String getAttachFile1() {
    return attachFile1;
  }
  public void setAttachFile1(String attachFile1) {
    this.attachFile1 = attachFile1;
  }
  public String getAttachFile2() {
    return attachFile2;
  }
  public void setAttachFile2(String attachFile2) {
    this.attachFile2 = attachFile2;
  }
  public String getAttachFile3() {
    return attachFile3;
  }
  public void setAttachFile3(String attachFile3) {
    this.attachFile3 = attachFile3;
  }
  
  
  
}
