package ejbpackage;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "customComponentBean")
@SessionScoped
public class CustomComponentBean {

    private String input;
    private String output;

    public String getInput() {return input;}
    public void setInput(String input) {this.input = input;}

    public String getOutput() {return output;}
    public void setOutput(String output) {this.output = output;}

    public void click() {
     output = input+ "! Вітаємо, ви потрапили до адміністративної панелі.Вам присвоєнно нове ім'я!";
    } 
}
