package com.sugar.leke.controller;

import com.sugar.leke.wechar.CallBackHandler;
import com.sugar.leke.wechar.dto.FollowDTO;
import com.sugar.leke.wechar.util.WecharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class WecharCallBackController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/wechar/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) {
		try {
			String signature = request.getParameter("signature");
	        //时间戳
	        String timestamp = request.getParameter("timestamp");
	        //随机数
	        String nonce = request.getParameter("nonce");
	        //随机字符串
	        String echostr = request.getParameter("echostr");

	        if (WecharUtils.checkSignature(signature, timestamp, nonce)) {
	            logger.info("[signature: "+signature + "]<-->[timestamp: "+ timestamp+"]<-->[nonce: "+nonce+"]<-->[echostr: "+echostr+"]");
	            PrintWriter writer = response.getWriter();
	            writer.write(echostr);
	            writer.flush();
	            writer.close();
	            return;
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*@PostMapping("/wechar/callback")
	public void callback2(HttpServletRequest request, HttpServletResponse response) {
		String content;
		try {
			content = IoUtils.parseToString(request.getInputStream());
			content = content.replace(" ", "");
			logger.info("微信回调阿尔法服务器,回调内容为{}", content);
			String event = WecharUtils.getCallBackEvent(content);

			PrintWriter writer = response.getWriter();
            writer.write("success");
            writer.flush();
            writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
