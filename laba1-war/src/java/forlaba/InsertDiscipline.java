package forlaba;
import datapackage.Disciplines;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
@WebServlet(name = "InsertDiscipline", urlPatterns = {"/InsertDiscipline"})
public class InsertDiscipline extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String discipline = request.getParameter("discipline"); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        processRequest(request, response);
        Disciplines newdiscip = new Disciplines();
        newdiscip.discipline=discipline;      
        
        ValidatorFactory factoryV = Validation.buildDefaultValidatorFactory();
        Validator validator = factoryV.getValidator();
        Set<ConstraintViolation<Disciplines>> constraints = validator.validate(newdiscip);
        out.println("<html><body><center>");
        for (ConstraintViolation<Disciplines> constraint : constraints) {
        out.println("<h2>"+ constraint.getMessage()+"</h2>");         
        }
        if(constraints.size()==0){     
             EntityManagerFactory factory = Persistence.createEntityManagerFactory("laba1-warPU");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Disciplines newdisp = new Disciplines();         
        newdisp.setDiscipline(discipline);      
         
        entityManager.persist(newdisp);
        entityManager.flush();
         
        transaction.commit();
        entityManager.close();
        factory.close();
        out.println("<html><body><center><h2>Диcципліна успішно додана!!</h2>");           
        }
        out.println("<p><a href=menupage.jsp> Повернутися на головну</a></p></center></body></html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}