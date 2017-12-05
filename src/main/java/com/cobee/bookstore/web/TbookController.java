package com.cobee.bookstore.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}