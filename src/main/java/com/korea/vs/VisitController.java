package com.korea.vs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.VisitDAO;
import vo.VisitVO;

@Controller
public class VisitController {

	@Autowired
	ServletContext application;
// 서블릿 안에서 제공해주는 클래스. @Autowired를 넣어주면 new없이도 자동으로 메모리 할당됌.

	VisitDAO visit_dao;

	public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}

	@RequestMapping(value = { "/", "/list.do" })
	public String list(Model model) {
		// 방명록 조회를 위한 dao 매서드 호출
		List<VisitVO> list = visit_dao.selectList();
		model.addAttribute("list", list); // 바인딩
		return "WEB-INF/views/visit/visit_list.jsp"; // 포워딩. 어느jsp로 갈지 정해주기
	}

	// 삭제메서드
	@RequestMapping("/delete.do")
	@ResponseBody
	// Ajax에 무조건 필요. 리턴값을 jsp등으로 인식하지 않고, 콜백 메서드인 function resultFunc()로 전달하기 위한
	// 키워드.
	public String delete(int idx, String filename) {
		 
		String webPath = "/resources/upload/";
		String savePath = application.getRealPath(webPath);
		
		int res = visit_dao.delete(idx); //지우는 코드 밑으로 내리기
		
		String result = "no";
		if (res != 0) {
			result = "yes";
			File f = new File(savePath, filename );
			
			if(f.exists()) {
				f.delete();
			}
		}
		// @ResponseBody가 적용되어 있으므로
		// result에 no 또는 yes 데이터는 콜백메서드로 돌아간다
		return result;
	}

	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return "WEB-INF/views/visit/visit_insert_form.jsp";

	}

	// 새 글 등록하기
	@RequestMapping("/insert.do")    //뭔가를 받아서 디비에 저장해주는 역할
	public String insert(VisitVO vo) {

		// 사진을 업로드할 절대 경로를 지정
		String webPath = "/resources/upload/";
		String savePath = application.getRealPath(webPath);
		System.out.println("절대 경로 : " + savePath); // 사진 잘올라갔나 확인해볼라고 넣어둠 콘솔창에 절대경로 표시됌

		// 업로드할 파일의 정보
		MultipartFile photo = vo.getPhoto();

		String filename = "no_file";

		// 업로드 된 파일이 존재한다면
		if (!photo.isEmpty()) {
			// 업로드될 파일의 파일명
			filename = photo.getOriginalFilename();

			// 파일을 저장할 경로
			File saveFile = new File(savePath, filename);

			if (!saveFile.exists()) { // 파일경로가 없다면
				saveFile.mkdirs();
			} else {
				long time = System.currentTimeMillis(); // 파일명 중복방지
				filename = String.format("$d_%s", time, filename);
				saveFile = new File(savePath, filename); // 중복된 파일명이니까 새롭게 파일명 저장.
			}
			// 업로드된 파일은 임시저장소에서 일정 시간이 지나면 사라지는데,
			// 현재 내가 지정한 upload폴더까지 경로로 사라지지 않도록 파일을 복사하는 메서드
			try {
				photo.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		vo.setFilename(filename); // vo에 파일명을 저장해서 DB로 전달
 
		int res = visit_dao.insert(vo);

		// sendRedirect("list.do");
		return "redirect:list.do"; // list.do 매핑을 호출하는 방법
	}

	// 글 수정 폼으로 이동
	@RequestMapping("/modify_form.do")
	public String modify_form(Model model, int idx) {
		// idx는 중복값이 없으니까 idx로 구분하겠다. idx를 모델에 담는다.

		VisitVO vo = visit_dao.selectOne(idx);

		if (vo != null) {
			model.addAttribute("vo", vo);
			// vo에 모든정보 담긴채로 모디파이로감
		}
		return "WEB-INF/views/visit/visit_modify_form.jsp";
	}

	// 게시글 수정
	@RequestMapping("/modify.do")
	public String modify(VisitVO vo) { // int idx로 받으면 안됌.
		int res = visit_dao.update(vo);

		return "redirect:list.do";
	}
}
