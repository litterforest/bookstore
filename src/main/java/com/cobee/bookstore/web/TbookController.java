package com.cobee.bookstore.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cobee.bookstore.entity.Tbook;
import com.cobee.bookstore.service.ITbookService;

@Controller
@RequestMapping("tbook")
public class TbookController extends AbstractController {

	@Resource(name = "tbookServiceRedisImpl")
	private ITbookService ITbookService;
	
	@GetMapping("list")
	public String list(Model model)
	{
		List<Tbook> tbookList = ITbookService.listAll();
		model.addAttribute("tbookList", tbookList);
		return "list";
	}
	
	@GetMapping("form/{isbn}")
	public String form(@PathVariable String isbn, Model model)
	{
		// 访问书商品详情的时候，给页面访问次数增加1
		ITbookService.addBookPageView(isbn);
		Tbook tbook = ITbookService.get(isbn);
		model.addAttribute("book", tbook);
		return "bookform";
	}
	
}
