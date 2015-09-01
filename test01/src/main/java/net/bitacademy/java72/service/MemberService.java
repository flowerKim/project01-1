package net.bitacademy.java72.service;

import java.util.List;

import net.bitacademy.java72.domain.Member;

public interface MemberService {
  List<Member> list(int pageNo, int pageSize);
  int insert(Member member);
  int delete(int no);
  int update(Member member);
  Member get(int no);
  Member getUser(String email, String password);
  int countAll();
  boolean existEmail(String email);
}
