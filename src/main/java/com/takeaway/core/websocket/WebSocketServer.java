package com.takeaway.core.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.modular.dao.model.Managers;

@ServerEndpoint(value = "/websocket/{merchantId}")
@Component
public class WebSocketServer {
	private static Logger log = Logger.getLogger(WebSocketServer.class);
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	// private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new
	// CopyOnWriteArraySet<WebSocketServer>();
	private static ConcurrentHashMap<String, List<Session>> webSocketSet = new ConcurrentHashMap<String, List<Session>>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	private static HttpSession httpSession;

	private static String merchantId;

	/**
	 * 连接建立成功调用的方法
	 */
/*	@OnOpen
	public void onOpen_bak(Session session, EndpointConfig config) {
		log.info("连接建立开始......");
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());

		Managers users = (Managers) httpSession.getAttribute("s_user");

		if (users != null) {
			this.session = session;
			this.httpSession = httpSession;
			if (this.webSocketSet.size() > 0) {
				List<Session> sessionList = this.webSocketSet.get(users
						.getMerchantId());
				if (sessionList != null) {
					sessionList.add(session);
					this.webSocketSet.put(users.getMerchantId(), sessionList);// 加入map中
				} else {
					List<Session> newList = new ArrayList<Session>();
					newList.add(session);
					this.webSocketSet.put(users.getMerchantId(), newList);// 加入map中
				}
			} else {
				List<Session> newList = new ArrayList<Session>();
				newList.add(session);
				this.webSocketSet.put(users.getMerchantId(), newList);// 加入map中
			}

			addOnlineCount(); // 在线数加1
			log.info("有新连接加入！当前在线人数为" + getOnlineCount());
			try {
				sendMessage(session, "连接成功");
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		} else {
			log.error("users不存在，session用户未登陆......");
			// 用户未登陆
			try {
				session.close();
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		}

		
		 * this.session = session; // webSocketSet.add(this); // 加入set中
		 * webSocketSet.put(jailId, session);//加入map中
		 * 
		 * addOnlineCount(); // 在线数加1 log.info("有新连接加入！当前在线人数为" +
		 * getOnlineCount()); try { sendMessage(jailId,"连接成功"); } catch
		 * (IOException e) { log.error("websocket IO异常"); }
		 
		log.info("连接建立结束......");
	}
*/
	@OnOpen
	public void onOpen(@PathParam("merchantId") String merchantId,
			Session session, EndpointConfig config) {
		log.info("连接建立开始......merchantId:"+merchantId);

		if (StringUtils.isNotBlank(merchantId)) {
			this.session = session;
			this.merchantId = merchantId;
			if (this.webSocketSet.size() > 0) {
				List<Session> sessionList = this.webSocketSet.get(merchantId);
				if (sessionList != null) {
					sessionList.add(session);
					this.webSocketSet.put(merchantId, sessionList);// 加入map中
				} else {
					List<Session> newList = new ArrayList<Session>();
					newList.add(session);
					this.webSocketSet.put(merchantId, newList);// 加入map中
				}
			} else {
				List<Session> newList = new ArrayList<Session>();
				newList.add(session);
				this.webSocketSet.put(merchantId, newList);// 加入map中
			}

			addOnlineCount(); // 在线数加1
			log.info("有新连接加入！当前在线人数为" + getOnlineCount());
			try {
				JSONObject result = new JSONObject();
		        result.put("code", 200);
		        result.put("msg", "连接成功");
		        sendMessage(session, result.toJSONString());
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		} else {
			log.error("users不存在，session用户未登陆......");
			// 用户未登陆
			try {
				session.close();
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		}

		/*
		 * this.session = session; // webSocketSet.add(this); // 加入set中
		 * webSocketSet.put(jailId, session);//加入map中
		 * 
		 * addOnlineCount(); // 在线数加1 log.info("有新连接加入！当前在线人数为" +
		 * getOnlineCount()); try { sendMessage(jailId,"连接成功"); } catch
		 * (IOException e) { log.error("websocket IO异常"); }
		 */
		log.info("连接建立结束......");
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		log.error("正常退出......"+webSocketSet);
		Iterator<Entry<String, List<Session>>> entries = webSocketSet.entrySet().iterator();  
		while (entries.hasNext()) {
			Entry<String, List<Session>> entry = entries.next(); 
			log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			List<Session> list=webSocketSet.get(entry.getKey());
			for(Session obj:list){
				if(session==obj){
					list.remove(obj);
					subOnlineCount(); // 在线数减1
					log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
				}
			}
			webSocketSet.put(entry.getKey(), list);
		}
		
/*		if (StringUtils.isNotBlank(merchantId)) {
			this.webSocketSet.remove(merchantId); // 从map中删除
			subOnlineCount(); // 在线数减1
			log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		}*/
		log.info("正常退出,连接关闭......"+webSocketSet);
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端的消息:" + message);

		// 群发消息
		/*
		 * for (WebSocketServer item : webSocketSet) { try {
		 * item.sendMessage(message); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
	}

	/**
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误......"+webSocketSet);
		Iterator<Entry<String, List<Session>>> entries = webSocketSet.entrySet().iterator();  
		while (entries.hasNext()) {
			Entry<String, List<Session>> entry = entries.next(); 
			log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			List<Session> list=webSocketSet.get(entry.getKey());
			if(list!=null&&list.size()>0){
				for(Session obj:list){
					if(session==obj){
						list.remove(obj);
					}
				}
				webSocketSet.put(entry.getKey(), list);
			}
		}
		
/*		if (StringUtils.isNotBlank(merchantId)) {
			this.webSocketSet.remove(merchantId); // 从map中删除
			subOnlineCount(); // 在线数减1
			log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		}*/
		log.info("发生错误,连接关闭......"+webSocketSet);
	//	error.printStackTrace();
	}

	public static void sendMessage(Session session, String message)
			throws IOException {
		session.getBasicRemote().sendText(message);
		// this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 * */
	public static void sendInfo(String merchantId,String message) throws IOException {
		log.info("merchantId:"+merchantId+"  message:"+message);
		if (StringUtils.isBlank(merchantId)) {
			return;
		}

		log.info("获取接收消息列表start");
		List<Session> sessionList = webSocketSet.get(merchantId);
		log.info("获取接收消息列表end");
		if(sessionList!=null&&sessionList.size()>0){
			for (Session session : sessionList) {
				log.info("开始每个发送消息:"+message);
				try {
					sendMessage(session, message);
				} catch (IOException e) {
					log.info("消息发送异常:"+message);
					continue;
				}
			}
		}

	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

	public static void main(String args[]){
		ConcurrentHashMap<String, List<Object>> webSocketSet = new ConcurrentHashMap<String, List<Object>>();
		List<Object> newList = new ArrayList<Object>();
		for(int i=0;i<2;i++){
			newList.add(new Object());
		}
		webSocketSet.put("21", newList);
		System.out.println("webSocketSet---"+webSocketSet.toString());
	}
}

