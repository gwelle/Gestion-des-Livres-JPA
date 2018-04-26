/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Olga
 * Created: 25 avr. 2018
 */
#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Adherent
#------------------------------------------------------------

CREATE TABLE Adherent(
        idAdherent int (11) Auto_increment  NOT NULL ,
        nom        Varchar (200) ,
        prenom     Varchar (200) ,
        PRIMARY KEY (idAdherent )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Livre
#------------------------------------------------------------

CREATE TABLE Livre(
        idLivre            int (11) Auto_increment  NOT NULL ,
        titre              Varchar (100) ,
        auteur             Varchar (200) ,
        etatLivre Varchar (100) ,

        PRIMARY KEY (idLivre )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Emprunter
#------------------------------------------------------------

CREATE TABLE Emprunter(
        idEmprunter int (11) Auto_increment  NOT NULL ,
        idAdherent  Int NOT NULL ,
        idLivre     Int NOT NULL ,
        PRIMARY KEY (idEmprunter )
)ENGINE=InnoDB;


ALTER TABLE Emprunter ADD CONSTRAINT FK_Emprunter_idAdherent FOREIGN KEY (idAdherent) REFERENCES Adherent(idAdherent);
ALTER TABLE Emprunter ADD CONSTRAINT FK_Emprunter_idLivre FOREIGN KEY (idLivre) REFERENCES Livre(idLivre);

