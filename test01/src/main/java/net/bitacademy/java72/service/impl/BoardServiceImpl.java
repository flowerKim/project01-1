package net.bitacademy.java72.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bitacademy.java72.dao.BoardDao;
import net.bitacademy.java72.domain.Board;
import net.bitacademy.java72.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
  @Autowired BoardDao boardDao;
  
  @Override
  public List<Board> list(int pageNo, int pageSize) {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    int startIndex = (pageNo - 1) * pageSize;
    if (startIndex < 0) {
      startIndex = 0;
    }
    paramMap.put("startIndex", startIndex);
    paramMap.put("pageSize", pageSize);
    return boardDao.list(paramMap);
  }

  @Override
  public int delete(int no) {
    return boardDao.delete(no);
  }
  
  @Override
  public int insert(Board board) {
    return boardDao.insert(board);
  }

  @Override
  public int update(Board board) {
    return boardDao.update(board);
  }

  @Override
  public Board get(int no) {
    return boardDao.get(no);
  }

  @Override
  public Board getView(int no) {
    return boardDao.get(no);
  }

  @Override
  public int updateView(Board board) {
    return boardDao.updateView(board);
  }

  @Override
  public int countAll() {
    return boardDao.countAll();
  }
  
  
  
  
  
}
