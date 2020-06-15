package forlaba;
import datapackage.Lecturers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@WebServlet(name = "InsertLecturers", urlPatterns = {"/InsertLecturers"})
public class InsertLecturers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");      
        HttpSession session = request.getSession();
        session.setAttribute("url", "google.com");
        session.removeAttribute("url");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        String name = request.getParameter("name");   
        String surname = request.getParameter("surname");    
        processRequest(request, response);  
        Lecturers newlectors = new Lecturers();
        newlectors.name=name;
        newlectors.surname=surname;
        
        ValidatorFactory factoryV = Validation.buildDefaultValidatorFactory();
        Validator validator = factoryV.getValidator();
        Set<ConstraintViolation<Lecturers>> constraints = validator.validate(newlectors);
        out.println("<html><body><center>");
        for (ConstraintViolation<Lecturers> constraint : constraints) {
        out.println("<h2>"+ constraint.getMessage()+"</h2>");         
        }
        if(constraints.size()==0){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba1-warPU");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();      
        Lecturers newlector = new Lecturers();      
        newlector.setName(name);
        newlector.setSurname(surname);         
        entityManager.persist(newlector);
        entityManager.flush();         
        transaction.commit();
        entityManager.close();
        factory.close();
        out.println("<html><body><center><h2>Викладач успішно доданий!!</h2>");       
        }
        out.println("<p><a href=menupage.jsp> Повернутися на головну</a></p></center></body></html>");
       }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}