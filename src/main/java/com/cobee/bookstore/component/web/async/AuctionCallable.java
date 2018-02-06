package com.cobee.bookstore.component.web.async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cobee.bookstore.component.AuctionListHandler;
import com.cobee.bookstore.component.listener.AuctionListener;
import com.cobee.bookstore.vo.AuctionResult;

public class AuctionCallable implements Callable<Map<String, Object>> {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionCallable.class);
//	private static final Object lock = new Object();

	private String userno;
	private String isbn;

	public AuctionCallable() {
		super();
	}

	public AuctionCallable(String userno, String isbn) {
		super();
		this.userno = userno;
		this.isbn = isbn;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		
		// 模拟计算时间
		long startTime = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		AuctionResult result = null;
		try
		{
			// 1,循环查找队列
			while(true)
			{
//				Thread.sleep(500);
				result = AuctionListener.auctionResultMap.get(userno + ":" + isbn);
				if (result != null)
				{
					AuctionListener.auctionResultMap.remove(userno + ":" + isbn);
					break;
				}
			}
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		
		String threadName = Thread.currentThread().getName();
		long endTime = System.currentTimeMillis();
		String duration = String.valueOf((endTime - startTime) / 1000);
		map.put("status", "success");
		map.put("userno", userno);
		map.put("threadName", threadName);
		map.put("duration", duration);
		map.put("quality", result.getQuality());
		map.put("leftQuality", result.getLeftQuality());
        return map;
        
	}

}
