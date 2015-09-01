package net.bitacademy.java72.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int    no;
  protected String name;
  protected String email;
  protected String password;
  protected String tel;
  protected String filePath;
  protected Date   createDate;
  protected String attachFile1;
  protected String photo;
  protected String yyyyMMdd; 
  
  public String getYyyyMMdd() {
    return yyyyMMdd; // yyyy-[M]M-[d]d
  }
  public void setYyyyMMdd(String yyyyMMdd) {
    this.setCreateDate(Date.valueOf(yyyyMMdd));
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public String getAttachFile1() {
    return attachFile1;
  }
  public void setAttachFile1(String attachFile1) {
    this.attachFile1 = attachFile1;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.yyyyMMdd = createDate.toString();
  }
  
  
  
}
