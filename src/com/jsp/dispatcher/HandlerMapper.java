package com.jsp.dispatcher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.jsp.action.Action;

public class HandlerMapper {

	private HandlerMapper() {}
	
	private static Map<String,Action> commandMap = new HashMap<String,Action>();
	
	static {
		String path = "com/jsp/properties/url";
		
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		
		Set<String> actionSetHome = rbHome.keySet();
		
		Iterator<String> it = actionSetHome.iterator();
		
		while(it.hasNext()) {
			String command = it.next();
			
			String actionClassName = rbHome.getString(command);
			
			System.out.println(actionClassName);
			
			try {
			Class actionClass = Class.forName(actionClassName);
			Action commandAction = (Action) actionClass.newInstance();
			
			commandMap.put(command, commandAction);
			
			System.out.println(command + ":" + commandAction + " 가 준비되었습니다.");
			}catch(ClassNotFoundException e) {
				System.out.println(actionClassName + "이 존재하지 않습니다.");
			}catch(InstantiationException e) {
				System.out.println(actionClassName + "인스턴스를 생성할 수 없습니다.");
			}catch(IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
		
		public static Action getAction(String command) {
			Action action = commandMap.get(command);
			return action;
		}
	}
