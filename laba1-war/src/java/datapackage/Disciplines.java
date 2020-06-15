package datapackage;

import datapackage.Lecturers;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DISCIPLINES", catalog = "", schema = "ROOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplines.findAll", query = "SELECT d FROM Disciplines d")
    , @NamedQuery(name = "Disciplines.findByDisid", query = "SELECT d FROM Disciplines d WHERE d.disid = :disid")
    , @NamedQuery(name = "Disciplines.findByDiscipline", query = "SELECT d FROM Disciplines d WHERE d.discipline = :discipline")})
public class Disciplines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DISID")
    public Integer disid;
    @Valid
    @NotNull(message="Введіть назву дисципліни!!")
    @Pattern(regexp = "[a-zA-Z]*", message = "Некоректні символи у назві!!")
    @Size(min = 2, message="Довжина назви дисципліни повинна бути більше 2 символів!!")
    @Column(name = "DISCIPLINE")
    public String discipline;
    @JoinColumn(name = "LECTID", referencedColumnName = "LECTID")
    @ManyToOne
    public Lecturers lectid;
    
    public Disciplines() {
    }

    public Disciplines(Integer disid) {
        this.disid = disid;
    }

    public Disciplines(String discipline) {
        this.discipline = discipline;
    }

    public Disciplines(Integer disid, String discipline, Lecturers lectid) {
        this.disid = disid;
        this.discipline = discipline;
        this.lectid = lectid;
    }

    public Integer getDisid() {
        return disid;
    }

    public void setDisid(Integer disid) {
        this.disid = disid;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getLectid() {
        return lectid.getLectid();
    }

    public void setLectid(Lecturers lectid) {
        this.lectid = lectid;
    }
  /*  public String getLecturers() {
        return lecturers.getName();
    }

    public void setLecturers(Lecturers lecturers) {
        this.lecturers = lecturers;
    }
   public String getLecturerssn() {
        return lecturers.getSurname();
    }

    public void setLecturerssn(Lecturers lecturers) {
        this.lecturers = lecturers;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disid != null ? disid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Disciplines)) {
            return false;
        }
        Disciplines other = (Disciplines) object;
        if ((this.disid == null && other.disid != null) || (this.disid != null && !this.disid.equals(other.disid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return discipline;
    }

  }
    

