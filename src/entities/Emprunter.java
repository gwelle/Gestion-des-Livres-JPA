
package entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olga
 */
@Entity
@Table(name = "emprunter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunter.findAll", query = "SELECT e FROM Emprunter e ORDER BY e")
    , @NamedQuery(name = "Emprunter.findByIdEmprunter", query = "SELECT e FROM Emprunter e WHERE e.idEmprunter = :idEmprunter")})
public class Emprunter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmprunter")
    private Integer idEmprunter;
    
    @JoinColumn(name = "idLivre", referencedColumnName = "idLivre")
    @ManyToOne(optional = false)
    private Livre idLivre;
    
    @JoinColumn(name = "idAdherent", referencedColumnName = "idAdherent")
    @ManyToOne(optional = false)
    private Adherent idAdherent;

    public Emprunter() {
    }

    /**
     * @return the idEmprunter
     */
    public Integer getIdEmprunter() {
        return idEmprunter;
    }

    /**
     * @param idEmprunter the idEmprunter to set
     */
    public void setIdEmprunter(Integer idEmprunter) {
        this.idEmprunter = idEmprunter;
    }

    /**
     * @return the idLivre
     */
    public Livre getIdLivre() {
        return idLivre;
    }

    /**
     * @param idLivre the idLivre to set
     */
    public void setIdLivre(Livre idLivre) {
        this.idLivre = idLivre;
    }

    /**
     * @return the idAdherent
     */
    public Adherent getIdAdherent() {
        return idAdherent;
    }

    /**
     * @param idAdherent the idAdherent to set
     */
    public void setIdAdherent(Adherent idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Override
    public String toString() {
        return "Emprunter{" + "idEmprunter=" + idEmprunter + ", idLivre=" + idLivre +
                ", idAdherent=" + idAdherent + '}';
    }

    
    
}
