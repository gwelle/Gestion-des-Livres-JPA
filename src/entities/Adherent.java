
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olga
 */
@Entity
@Table(name = "adherent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adherent.findAll", query = "SELECT a FROM Adherent a")
    , @NamedQuery(name = "Adherent.findByIdAdherent", query = "SELECT a FROM Adherent a WHERE a.idAdherent = :idAdherent")
    , @NamedQuery(name = "Adherent.findByNom", query = "SELECT a FROM Adherent a WHERE a.nom = :nom")
    , @NamedQuery(name = "Adherent.findByPrenom", query = "SELECT a FROM Adherent a WHERE a.prenom = :prenom")})
public class Adherent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdherent")
    private Integer idAdherent;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "nbLivreEmprunter")
    private int nbLivreEmprunter;
    
//    @JoinColumn(name = "idLivre", referencedColumnName = "idLivre")
//    @OneToOne(optional = false)
//    private Livre idLivre;

    public Adherent() {
    }

    /**
     * @return the idAdherent
     */
    public Integer getIdAdherent() {
        return idAdherent;
    }

    /**
     * @param idAdherent the idAdherent to set
     */
    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
 
    /**
     * @return the nbLivreEmprunter
     */
    public int getNbLivreEmprunter() {
        return nbLivreEmprunter;
    }

    /**
     * @param nbLivreEmprunter the nbLivreEmprunter to set
     */
    public void setNbLivreEmprunter(int nbLivreEmprunter) {
        this.nbLivreEmprunter = nbLivreEmprunter;
    }
    
//        /**
//     * @return the idLivre
//     */
//    public Livre getIdLivre() {
//        return idLivre;
//    }
//
//    /**
//     * @param idLivre the idLivre to set
//     */
//    public void setIdLivre(Livre idLivre) {
//        this.idLivre = idLivre;
//    }
    
    

    @Override
    public String toString() {
        return "Adherent{" + "nom=" + nom + ", prenom=" + prenom 
                + ", nbLivreEmprunter=" + nbLivreEmprunter + '}';
    }
    
    /**
     * Incrémenter le nombre de Livres d'un Adhérent lorsqu'il emprunte un Livre
     * @return 
     */
    public int incrementerVariableNbLivreEmprunter(){
        
        int compteur = nbLivreEmprunter + 1 ;
        
        return compteur ;
    }
    
    /**
     * Décrémenter le nombre de Livres d'un Adhérent lorsqu'il rends un Livre
     * @return 
     */
    public int decrementerVariableNbLivreEmprunter(){
        
        int compteur = nbLivreEmprunter - 1 ;
        
//        if(compteur == 0){
//            
//            nbLivreEmprunter = compteur ;
//        }
//        
        return compteur ;
    }
}