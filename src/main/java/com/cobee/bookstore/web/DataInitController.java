package com.cobee.bookstore.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cobee.bookstore.entity.Tbook;
import com.cobee.bookstore.service.ITbookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("DataInit")
public class DataInitController extends AbstractController {

	@Resource(name = "tbookServiceImpl")
	private ITbookService ITbookService;
	
	@GetMapping(value = "buildRedisData")
	public String buildRedisData(Model model)
	{
		String msg = "";
		Jedis jedis = jedisBean.getJedis();
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			List<Tbook> list = ITbookService.listAll();
			if (!CollectionUtils.isEmpty(list))
			{
				jedis.del("zset:books");
				jedis.del("hash:books");
				for (Tbook po : list)
				{
					String jsonStr = objectMapper.writeValueAsString(po);
					jedis.zadd("zset:books", po.getPdate().getTime(), po.getIsbn());
					jedis.hset("hash:books", po.getIsbn(), jsonStr);
				}
			}
			msg = "Redis数据创建成功";
		}
		catch(Exception e)
		{
			msg = "Redis数据创建失败";
			e.printStackTrace();
		}
		finally
		{
			jedisBean.closeJedis(jedis);
		}
		model.addAttribute("msg", msg);
		return "buildredis";
	}
	
}
