package com.takeaway.core.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	public void onClose() {
		if (StringUtils.isNotBlank(merchantId)) {
			this.webSocketSet.remove(merchantId); // 从map中删除
			subOnlineCount(); // 在线数减1
			log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		}
		log.info("连接关闭！");
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
		log.error("发生错误");
		if (StringUtils.isNotBlank(merchantId)) {
			this.webSocketSet.remove(merchantId); // 从map中删除
			subOnlineCount(); // 在线数减1
			log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
		}
		error.printStackTrace();
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
		log.info(message);
		if (StringUtils.isBlank(merchantId)) {
			return;
		}

		List<Session> sessionList = webSocketSet.get(merchantId);
		for (Session session : sessionList) {
			try {
				sendMessage(session, message);
			} catch (IOException e) {
				continue;
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

}
