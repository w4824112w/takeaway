/*package com.takeaway.core.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.catalina.session.StandardSessionFacade;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class HttpSessionConfigurator extends Configurator {
	private static Logger log = Logger.getLogger(WebSocketServer.class);

	 修改握手,就是在握手协议建立之前修改其中携带的内容 
	@Override
	public void modifyHandshake(ServerEndpointConfig sec,
			HandshakeRequest request, HandshakeResponse response) {
		 如果没有监听器,那么这里获取到的HttpSession是null 
		HttpSession httpSession = (HttpSession) request.getHttpSession();
        if (httpSession!=null){
            log.info("获取到session id:"+httpSession.getId());
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }else{
        	log.info("modifyHandshake 获取到null session");
        }
		
	}

}
*/