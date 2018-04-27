
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Olga
 */
@Entity
@Table(name = "livre", catalog = "gestionlivres")
@NamedQuery(name = "Livre.findByEtatLivre", 
        query = "SELECT l FROM Livre l WHERE l.etatLivre = :etatLiVRE")
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLivre", nullable = false)
    private Integer idLivre;
    
    @Column(name = "titre", nullable = true)
    private String titreLivre;
    
    @Column(name = "auteur", nullable = true)
    private String auteurLivre;
    
    @Column(name = "langage", nullable = true)
    private String langage;
    
    @Column(name = "etatLivre", nullable = true)
    private String etatLivre;

    public Livre() {
    }

    /**
     * @return the idLivre
     */
    public Integer getIdLivre() {
        return idLivre;
    }

    /**
     * @param idLivre the idLivre to set
     */
    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    /**
     * @return the titreLivre
     */
    public String getTitreLivre() {
        return titreLivre;
    }

    /**
     * @param titreLivre the titreLivre to set
     */
    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    /**
     * @return the auteurLivre
     */
    public String getAuteurLivre() {
        return auteurLivre;
    }

    /**
     * @param auteurLivre the auteurLivre to set
     */
    public void setAuteurLivre(String auteurLivre) {
        this.auteurLivre = auteurLivre;
    }

    /**
     * @return the langage
     */
    public String getLangage() {
        return langage;
    }

    /**
     * @param langage the langage to set
     */
    public void setLangage(String langage) {
        this.langage = langage;
    }
    
    /**
     * @return the etatLivre
     */
    public String getEtatLivre() {
        return etatLivre;
    }

    /**
     * @param etatLivre the etatLivre to set
     */
    public void setEtatLivre(String etatLivre) {
        this.etatLivre = etatLivre;
    }

    @Override
    public String toString() {
        return "Livre{" + "idLivre=" + idLivre + ", titreLivre=" + titreLivre +
                ", auteurLivre=" + auteurLivre + ", langage=" + langage + 
                ", etatLivre=" + etatLivre + '}';
    }
    
    
}