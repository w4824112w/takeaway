package com.takeaway.modular.controller.api;


import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.websocket.RequestMessage;
import com.takeaway.core.websocket.ResponseMessage;
import com.takeaway.modular.dao.model.Users;
import com.takeaway.modular.service.UsersService;

/**
 * ws消息通知
 * 
 * @author hk
 *
 */
@Controller
public class WebSocketController {
	private static final Logger log = Logger
			.getLogger(WebSocketController.class);

	@MessageMapping("/welcome")
	// 当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
	@SendTo("/topic/getResponse")
	// 当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
	public ResponseMessage   say(RequestMessage  message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");

	}

}
