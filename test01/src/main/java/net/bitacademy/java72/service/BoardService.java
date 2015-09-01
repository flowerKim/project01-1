package net.bitacademy.java72.service;

import java.util.List;

import net.bitacademy.java72.domain.Board;

public interface BoardService {
  List<Board> list(int pageNo, int pageSize); 
  int insert(Board board);
  int update(Board board);
  int delete(int no);
  Board get(int no);
  Board getView(int no);
  int updateView(Board board);
  int countAll();
//  void views(Board board);
  
}
