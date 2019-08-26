package kurs.popnet.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import kurs.popnet.beans.MessageBean;
import kurs.popnet.beans.UserBean;
import kurs.popnet.service.MessageService;
import kurs.popnet.service.UserService;

public class MainCommand extends Command {

	public MainCommand(String jsp) {
		super(jsp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		UserService us = new UserService("jdbc:mysql://localhost/PopNet", "dev", "mysql");
		us.createConnection(true);
		
		MessageService ms = new MessageService("jdbc:mysql://localhost/PopNet", "dev", "mysql");
		ms.createConnection(true);
		
		List<MessageBean> lmb = new ArrayList<MessageBean>();
		
		UserBean ub = new UserBean();
		
		String name = req.getParameter("username");
		
		String pswd = req.getParameter("password");
		
		String msgId = req.getParameter("deleteMsg");
		if(msgId!=null) {
			Integer msId = Integer.parseInt(msgId);
			ms.deleteMessage(msId);
		}
		
		ub = us.logService(name, pswd);
		
		if(ub !=null) {
			req.setAttribute("owner", ub);
			Integer ownerId = ub.getUserId();
			
			String msqgId = req.getParameter("deleteMsg");
			if(msgId!=null) {
				Integer msId = Integer.parseInt(msgId);
				ms.deleteMessage(msId);
			}
			RestTemplate templ = new RestTemplate();
			
			ResponseEntity<List<MessageBean>> response = templ.exchange("http://localhost:8082/api/messages/" + ownerId, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<MessageBean>>() {
					});

			List<MessageBean> message = response.getBody();
			lmb = message;
			req.setAttribute("msgDisplay", lmb);
		}else if(name=="" || pswd=="" || ub==null) {
			nextPage = "PopController?cmd=login";
		}
		
		return nextPage;
	}

}
