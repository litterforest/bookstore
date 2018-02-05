package com.cobee.bookstore.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cobee.bookstore.entity.Comment;
import com.cobee.bookstore.service.ICommentService;

/**
 * <pre>处理评论的controller</pre>
 * @author 陈淦森
 * @version 1.0.1
 * @date 2017年12月6日
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {
	
	@Resource(name = "ICommentServiceImpl")
	private ICommentService ICommentService;
	
	@GetMapping(value = "/commentForm")
	public String commentForm(@ModelAttribute(value = "isbn") String isbn, Model model)
	{
		// 加载这本书的评论
		List<Comment> commentList = ICommentService.listAll(isbn);
		model.addAttribute("commentList", commentList);
		return "commentForm";
	}
	
	@PostMapping(value = "/saveComment")
	public String saveComment(Comment comment)
	{
		comment.setCreateDate(new Date());
		ICommentService.add(comment);
		return "redirect:commentForm?isbn=" + comment.getIsbn();
	}
	
	@RequestMapping(value = "/dianzhang")
	@ResponseBody
	public Map<String, Object> dianzhang(String commentID)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = ICommentService.dianzhang(commentID);
			map.put("status", "success");
			map.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "fail");
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
