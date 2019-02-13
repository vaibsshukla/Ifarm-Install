package com.b3ds.ifarm.installation.wssocket;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Log4jWebSession 
{
	private StompSession session = null;
	
	public void sendLog(byte[] bytes ) throws UnsupportedEncodingException
	{
        Gson gson = new Gson();
        JsonObject json=new JsonObject();
        String str=new String(bytes, "UTF-8");
        json.addProperty("name", str);
        String jsonString =gson.toJson(json);
        session.send("/app/logs", jsonString.getBytes()).getReceiptId();		
	}
	
	public void connects() throws InterruptedException, ExecutionException
	   {
		   WebSocketClient transport = new StandardWebSocketClient();
		   WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		   stompClient.setMessageConverter(new StringMessageConverter());
	        String url = "ws://localhost:8081/gs-guide-websocket";
	        StompSessionHandler handler = new stompHandler();
	        ListenableFuture<StompSession> f = stompClient.connect(url,handler);
	        session = f.get();       
	   }
}


class stompHandler extends StompSessionHandlerAdapter {
	
	public static org.apache.logging.log4j.Logger logger=LogManager.getLogger(stompHandler.class);
	
	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println("Connected");
	}   
}
