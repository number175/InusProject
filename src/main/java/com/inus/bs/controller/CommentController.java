package com.inus.bs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.inus.bs.vo.CommentVo;


@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private SqlSession sqlSession;
	
	// 댓글 등록
	@RequestMapping("/InsertComment")
	public  ModelAndView InsertComment(CommentVo vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println(vo.getInus_boardNum());
		System.out.println("vo");
		System.out.println(vo.getInus_commentNum());
		sqlSession.insert("com.inus.board.CommentMapper.CommentRegist", vo);
		mv.addObject("inus_boardNum", vo.getInus_boardNum());
		mv.addObject("inus_commentNum", vo.getInus_commentNum());
		mv.setViewName("redirect:/comment/CommentList");
		return mv;
	}
	
	// 댓글 목록
	@RequestMapping("/CommentList")
	public @ResponseBody void CommentList(HttpServletResponse res, CommentVo vo) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> data = new HashMap<String, Object>();
		List<CommentVo> list = sqlSession.selectList("com.inus.board.CommentMapper.getList", vo);
		if(list.size() > 0) {
			data.put("list", list);
		}
		
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(gson.toJson(data));
	}
	
	// 댓글 삭제
	@RequestMapping(value="/CommentDelete/{inus_boardNum}/{inus_commentNum}")
	public @ResponseBody void CommentDelete(HttpServletResponse res, CommentVo vo) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> data = new HashMap<String, Object>();
		int a = sqlSession.delete("com.inus.board.CommentMapper.CommentDelete", vo);
		List<CommentVo> list = sqlSession.selectList("com.inus.board.CommentMapper.getList", vo);
		if(list.size() > 0) {
			data.put("list", list);
		}
		res.setCharacterEncoding("UTF-8");
		res.getWriter().print(gson.toJson(data));
	}
	
}
//@RequestMapping("/getRoom")
//public @ResponseBody void getRoom(HttpServletResponse res, Room room) throws Exception{
//   Gson gson = new Gson();
//   Map<String, Object> data = new HashMap<String, Object>();
//  List<Room> list = ss.selectList("ChatMapper.getChatRoom", room);
//  if(list.size() > 0) {
//      data.put("list", list);
//   }
//   res.getWriter().print(gson.toJson(data));
//}
