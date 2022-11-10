package com.korea.vs;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.VisitDAO;
import vo.VisitVO;

@Controller
public class VisitController {
	VisitDAO visit_dao;
	
	public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	@RequestMapping(value = {"/", "/list.do"})
	public String list(Model model) {
		
		//방명록 전체보기
		List<VisitVO> list = visit_dao.selectList();
		model.addAttribute("list", list);
		
		return "WEB-INF/views/visit/visit_list.jsp";	
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public String delete(int idx) {
		int res = visit_dao.delete(idx);
		
		String result ="no";
		
		if(res !=0) {
			result ="yes";
		}
		return result;
	}
}
