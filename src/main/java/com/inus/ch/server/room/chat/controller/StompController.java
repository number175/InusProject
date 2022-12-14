package com.inus.ch.server.room.chat.controller;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.inus.ch.server.room.vo.Chat;
import com.inus.ch.server.room.vo.Message;



@RestController
public class StompController {
	
	@Autowired
	private SimpMessagingTemplate smt;
	@Autowired
	private SqlSession ss;
	
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Message message(Message msg) throws Exception {
		return new Message(msg.getName(), msg.getMessage());
	}
	
	@MessageMapping("/user")
	public void user(Chat chat) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Timestamp tstp = Timestamp.valueOf(sdf.format(now));
		
		
		chat.setChatDate(tstp);
		ss.insert("ChatMapper.chattiing", chat);
		HashMap<String, Object> payload = new HashMap<>();
		List<Chat> list = ss.selectList("ChatMapper.getChat", chat);
		payload.put("list", list);
		smt.convertAndSend("/topic/" + chat.getChannelCode() + "/" + chat.getRoomCode(), payload);
	}
 }
