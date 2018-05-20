/*package com.takeaway.core.websocket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@WebListener
public class RequestListener implements ServletRequestListener {

	private static Logger log = Logger.getLogger(RequestListener.class);
	
	public void requestInitialized(ServletRequestEvent sre) {
		log.info("request--:"+sre.getServletRequest().getLocalAddr());
		//将所有request请求都携带上httpSession----这样会创建新的session!导致重新创建了会话
		((HttpServletRequest) sre.getServletRequest()).getSession();

	}

	public RequestListener() {
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
	}
}
*/