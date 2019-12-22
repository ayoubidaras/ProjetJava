CREATE TABLE SERVICE
(
    code VARCHAR(3) PRIMARY KEY NOT NULL,
    nom VARCHAR(30),
    batiment VARCHAR(10),
    directeur INT
)

CREATE TABLE CHAMBRE
(
    code_service VARCHAR(3),
	no_chambre INT,
    surveillant INT,
    nb_lits INT,
	CONSTRAINT FK_chambreCodeService FOREIGN KEY (code_service) REFERENCES SERVICE(code),
	CONSTRAINT PK_chambre  PRIMARY KEY (no_chambre, code_service)
)

CREATE TABLE EMPLOYE
(
    numero INT PRIMARY KEY NOT NULL,
    nom VARCHAR(30),
	prenom VARCHAR(30),
	tel VARCHAR(15),
    adresse VARCHAR(50),
	CONSTRAINT UC_EMPLOYE UNIQUE (nom, prenom, tel)
)

CREATE TABLE DOCTEUR
(
    numero INT PRIMARY KEY NOT NULL,
    specialite VARCHAR(20)
	CONSTRAINT FK_docteurNumero FOREIGN KEY (numero) REFERENCES EMPLOYE(numero)
)

CREATE TABLE INFIRMIER
(
    numero INT PRIMARY KEY NOT NULL,
    code_service VARCHAR(3),
    rotation VARCHAR(4),
    salaire INT,
	CONSTRAINT FK_infirmierNumero FOREIGN KEY (numero) REFERENCES EMPLOYE(numero)
)

CREATE TABLE MALADE
(
    numero INT PRIMARY KEY NOT NULL,
    nom VARCHAR(30),
	prenom VARCHAR(30),
	tel VARCHAR(15),
    adresse VARCHAR(50),
	mutuelle VARCHAR(10),
	CONSTRAINT UC_malade UNIQUE (nom, prenom, tel)
)

CREATE TABLE HOSPITALISATION
(
    no_malade INT PRIMARY KEY NOT NULL,
    code_service VARCHAR(3),
    no_chambre INT,
    lit INT,
	CONSTRAINT FK_hospitalisationCodeService FOREIGN KEY (code_service) REFERENCES EMPLOYE(numero),
	CONSTRAINT FK_hospitalisationNoChambre FOREIGN KEY (no_chambre) REFERENCES CHAMBRE(no_chambre),
	CONSTRAINT UC_hospitalisation UNIQUE (code_service, no_chambre, lit)
)

CREATE TABLE SOIGNE
(
    no_docteur INT,
    no_malade INT,
	CONSTRAINT FK_soigneNoDocteur FOREIGN KEY (no_docteur) REFERENCES EMPLOYE(numero),
	CONSTRAINT FK_soigneNoMalade FOREIGN KEY (no_malade) REFERENCES MALADE(numero),
	CONSTRAINT PK_soigne PRIMARY KEY (no_docteur, no_malade)
);