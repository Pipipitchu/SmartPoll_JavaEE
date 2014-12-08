package br.edu.univas.lab6.smartpoll.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.edu.univas.lab6.smartpoll.entity.User;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.UserService;
import br.edu.univas.lab6.smartpoll.util.Encryption;
import br.edu.univas.lab6.smartpoll.util.JSFMessage;

@ManagedBean(name = "changepassword")
@SessionScoped
public class ChangePasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String password;
	
	private String newPassword;
	
	private String confirmPassword;
	
	private LoginBean loginBean = new LoginBean();
	
	Encryption encryption = new Encryption();
	
	JSFMessage jsfMessage = new JSFMessage();

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	UserService userService = new UserService(simpleEntityManager);
	
	
	public User getUserLogged() throws IOException, ServletException {
		
		//HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("login");
		return loginBean.getUserLogged();
	}
	
	public String changePassword() throws IOException, ServletException {
		User user = getUserLogged();
		
		String passwordEncrypted = encryption.md5(password);
		
		System.out.println(user.getPassword());
		
		System.out.println(passwordEncrypted);
		
		
		if(passwordEncrypted.equals(user.getPassword())) {
			
			System.out.println(passwordEncrypted);
			
			if(newPassword.equals(confirmPassword)) {
				String newPasswordEncrypted = encryption.md5(newPassword);
				
				System.out.println(newPasswordEncrypted);
				user.setPassword(newPasswordEncrypted);
				userService.update(user);
				
				RequestContext.getCurrentInstance().update("growl");
				jsfMessage.sendInfoMessageToUser("Change Password Succesfully!!!");
				
				password = "";
				newPassword = "";
				confirmPassword = "";
				
				return "";
			}
		}
			RequestContext.getCurrentInstance().update("growl");
			jsfMessage.sendErrorMessageToUser("Error Change Password!!!");
			return "";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
