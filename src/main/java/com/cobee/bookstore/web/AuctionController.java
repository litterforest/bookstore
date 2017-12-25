package com.cobee.bookstore.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Auction")
public class AuctionController extends AbstractController {

	@GetMapping(value = "/acutionPage")
	public String acutionPage()
	{
		return "auction";
	}
	
	@PostMapping(value = "/doAuction", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Callable<Map<String, Object>> doAuction(final String userno)
	{
		
		SimpleAsyncTaskExecutor sate = null;
		
		return new Callable<Map<String, Object>>() {
			
	        public Map<String, Object> call() throws Exception {
	        	
				String threadName = Thread.currentThread().getName();
				// 模拟计算时间
				long startTime = System.currentTimeMillis();
				Thread.sleep(1000);
				long endTime = System.currentTimeMillis();
				String duration = String.valueOf((endTime - startTime) / 1000);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", "success");
				map.put("userno", userno);
				map.put("threadName", threadName);
				map.put("duration", duration);
	            return map;
	        }
	    };
	}
	
}
