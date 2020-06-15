package datapackage;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "lecturers", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturers.findAll", query = "SELECT l FROM Lecturers l")
    , @NamedQuery(name = "Lecturers.findByLectid", query = "SELECT l FROM Lecturers l WHERE l.lectid = :lectid")
    , @NamedQuery(name = "Lecturers.findByName", query = "SELECT l FROM Lecturers l WHERE l.name = :name")
    , @NamedQuery(name = "Lecturers.findBySurname", query = "SELECT l FROM Lecturers l WHERE l.surname = :surname")})
public class Lecturers implements Serializable {

    @OneToMany(mappedBy = "lectid")
    private List<Disciplines> disciplinesList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LECTID")
    public Integer lectid;
    @Size(max = 100)
    @Valid
    @Column(name = "NAME")
    @NotNull(message="Ім'я повинно бути задане!")
    @Pattern(regexp = "[a-zA-Z]*", message = "Некоректні символи у імені!!")
    public String name;
    @Valid
    @NotNull(message="Введіть прізвище!!")
    @Pattern(regexp = "[a-zA-Z]*", message = "Некоректні символи у прізвищі!!")
    @Size(min = 3, message="Довжина прізвища повинна бути більше 3 символів!!")
    @Column(name = "SURNAME")
    public String surname;  
      
        
    public Lecturers() {
    }

    public Lecturers(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Lecturers(Integer lectid, String name, String surname) {
        this.lectid = lectid;
        this.name = name;
        this.surname = surname;
    }

    public Integer getLectid() {
        return lectid;
    }

    public void setLectid(Integer lectid) {
        this.lectid = lectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lectid != null ? lectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {        
        if (!(object instanceof Lecturers)) {
            return false;
        }
        Lecturers other = (Lecturers) object;
        if ((this.lectid == null && other.lectid != null) || (this.lectid != null && !this.lectid.equals(other.lectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  name + " "+ surname;
    }

    @XmlTransient
    public List<Disciplines> getDisciplinesList() {
        return disciplinesList;
    }

    public void setDisciplinesList(List<Disciplines> disciplinesList) {
        this.disciplinesList = disciplinesList;
    } 
    
    
}
