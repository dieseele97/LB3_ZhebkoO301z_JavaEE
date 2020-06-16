package ejbpackage;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@Named(value = "logonBean")
@ManagedBean
@RequestScoped
public class LogonBean {

  private String login="";
  private String pass="";
  
    private String login_admin = "admin";
    private String password_admin = "123";
    private String login_lector = "lector";
    private String password_lector = "123";  
    private String su_admin = "suadmin";
    private String password_su = "123";
    public LogonBean() {
       
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass.trim();
    }

    public void setPass(String pass) {
        this.pass = pass.trim();
    }      

    public String logon() {
       FacesContext ctx = FacesContext.getCurrentInstance();
       if (login == null || "".equals(login)) {
       ctx.addMessage("Form:login",new FacesMessage(FacesMessage.SEVERITY_WARN,"Пустий логін", "Ви повинні ввести логін!!"));
       }
     if (pass == null || "".equals(pass)) {
     ctx.addMessage("Form:pass",
     new FacesMessage(FacesMessage.SEVERITY_WARN,"Пустий пароль", "Ви повинні ввести пароль!!"));
       }
        if (ctx.getMessageList().size() != 0)
         return null;
      if (this.login_admin.equals(login) && this.password_admin.equals(pass)) {          
              return "menupage.jsp?faces-redirect=true";           
        } else if (this.login_lector.equals(login) && this.password_lector.equals(pass)) {   
            return "menupage.jsp?faces-redirect=true";   
        }
         else if (this.su_admin.equals(login) && this.password_su.equals(pass)) {   
            return "composite.xhtml?faces-redirect=true";   
        }
        else {
            return "error.xhtml?faces-redirect=true";
        } 

    }
}

    


 

