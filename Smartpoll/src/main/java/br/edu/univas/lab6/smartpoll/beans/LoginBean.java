package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.edu.univas.lab6.smartpoll.entity.User;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.UserService;
import br.edu.univas.lab6.smartpoll.util.Encryption;
import br.edu.univas.lab6.smartpoll.util.JSFMessage;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String password;
	
	private boolean loggedIn;
	
	JSFMessage jsfMessage = new JSFMessage();
	
	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	UserService userService = new UserService(simpleEntityManager);
	
	Encryption encryption = new Encryption();
	
	public String loginValidity() {
		
		String passwordEncrypted = encryption.md5(password);
		
		User user = userService.findByEmailPassword(email, passwordEncrypted);
		
		if(user != null) {
			loggedIn = true;
			return "/pages/dashboard/index.xhtml?faces-redirect=true";
		}
		
		RequestContext.getCurrentInstance().update("growl");
		jsfMessage.sendErrorMessageToUser("Email or Password Invalid!!!");
		return "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
}
