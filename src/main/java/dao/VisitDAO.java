package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.VisitVO;

public class VisitDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//방명록 전체조회
	public List<VisitVO> selectList(){
		List<VisitVO> list = sqlSession.selectList("v.visit_list");
		return list;
	}
	
	//게시글 삭제
	public int delete(int idx) {
		int res= sqlSession.delete("v.visit_delete", idx);
		return res;
		
	}
	
	//새글 쓰기 VisitVO를 파라미터로 받을것임
	public int insert(VisitVO vo) {
		
		int res =sqlSession.insert("v.visit_insert", vo); //파라미터는 1개씩만 보낼 수 있음. vo하나로 다보냄
		return res;

	}
	
	//수정할 게시글 한건 검색
	public VisitVO selectOne(int idx) {
		VisitVO vo = sqlSession.selectOne("v.visit_one", idx);
		//idx를 where절에 넣어서 그걸 VisitVO vo에 넣어줘라
		return vo;
	}
	
	//수정시작
	public int update(VisitVO vo) {
		int res = sqlSession.update("v.visit_update", vo);
		return res;
	}
	
}
