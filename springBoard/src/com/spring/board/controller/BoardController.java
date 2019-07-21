package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		List<String> boardType = new ArrayList<String>();
		boardType = boardService.selectBoardType();
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		List<ComCodeVo> list = new ArrayList<ComCodeVo>();
		list=boardService.selectBoardOption();
		model.addAttribute("listOption",list);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("boardType",boardType);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		List<ComCodeVo> list = new ArrayList<ComCodeVo>();
		
		list=boardService.selectBoardOption();
		model.addAttribute("listOption",list);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method ={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	
	@RequestMapping(value="/board/boardDeleteAction.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String boardDelete(Locale locale,BoardVo boardVo) throws Exception{
			
			HashMap<String, String> result = new HashMap<String, String>();
			CommonUtil commonUtil = new CommonUtil();
			
			int resultCnt = boardService.boardDelete(boardVo);
			
			result.put("success", (resultCnt > 0)?"Y":"N");
			String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
			
			System.out.println("callbackMsg::"+callbackMsg);
			
			return callbackMsg;
	}
	
	@RequestMapping(value="/board/boardModify.do", method = RequestMethod.GET)
	public String boardModify(Locale locale, Model model,@RequestParam(value="boardNum",required=false)int boardNum,@RequestParam(value="boardType",required=false)String boardType,@RequestParam(value="boardTitle",required=false)String boardTitle,@RequestParam(value="boardComment",required=false)String boardComment) throws Exception{
		model.addAttribute("boardNum",boardNum);
		model.addAttribute("boardTitle",boardTitle);
		model.addAttribute("boardComment",boardComment);
		model.addAttribute("boardType",boardType);
		return "board/boardModify";
	}
	@RequestMapping(value="/board/boardModifyAction.do",method =RequestMethod.POST)
	@ResponseBody
	public String boardModifyAction(Locale locale,BoardVo boardVo) throws Exception{
		
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardModify(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	@RequestMapping(value="/board/boardSearchAction.do",method ={RequestMethod.POST,RequestMethod.GET})
	public String boardSearchAction(Locale locale,@RequestParam(value="list[]",required=false)List<String> list,Model model) throws Exception{
		int totalCnt = 0;
		totalCnt = boardService.selectBoardCnt();
		
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("list", list);
		List<BoardVo> boardList1 = new ArrayList<BoardVo>();
		boardList1 = boardService.boardSearch(map);
		List<ComCodeVo> list1 = new ArrayList<ComCodeVo>();
		
		list1=boardService.selectBoardOption();
		model.addAttribute("listOption",list1);
		model.addAttribute("boardList",boardList1);
		model.addAttribute("totalCnt", totalCnt);
		return "board/boardList";
	}
}
