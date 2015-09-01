package net.bitacademy.java72.dao;

import java.util.List;
import java.util.Map;

import net.bitacademy.java72.domain.Member;

public interface MemberDao {
  List<Member> list(Map<String, Object> paramMap);
  int insert(Member member);
  int delete(int no);
  Member get(int no);
  int update(Member member);
  Member exist(Map<String, String> map);
  int countAll();
  // SQL과 일치해야 하므로 파라미터를 두 개 넘길 수 없다.
  // 따라서 Map으로 바꿔야 함.
  int countEmail(String email);
  
}
