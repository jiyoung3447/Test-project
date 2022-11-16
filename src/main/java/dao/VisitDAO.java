package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.VisitVO;

public class VisitDAO {
	
SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	//전체보기
	public List<VisitVO> selectList(){
		List<VisitVO> list = sqlSession.selectList("v.visit_list");
		return list;
	}
	
	//게시글 삭제
	public int delete(int idx) {
		int res = sqlSession.delete("v.visit_delete", idx);
		return res;
	}
	
	//새글 쓰기
	public int insert(VisitVO vo) {
		int res = sqlSession.insert("v.visit_insert", vo);
		return res;	
	}
	
	//수정할 게시글 한건 검색
	public VisitVO selectOne(int idx) {
		VisitVO vo = sqlSession.selectOne("v.visit_one", idx);
		return vo;
	}
	
	//수정
	public int update(VisitVO vo) {
		int res = sqlSession.update("v.visit_modify", vo);
		return res;
	}

}
