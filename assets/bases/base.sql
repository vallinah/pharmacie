DROP Table produit_forme_laboratoire;
DROP Table type_categorie;
DROP Table categorie_sous_categorie;
DROP Table produit_symptome;
DROP TABLE maladie_symptome;
DROP Table produit_sous_categorie;
DROP Table mouvement;
DROP Table produit_forme;
DROP Table produit;
DROP Table type_mouvement;
DROP Table mode_administration;
DROP Table forme;
DROP Table symptome;
DROP Table sous_categorie;
DROP Table categorie;
DROP Table unite_mesure;
DROP TABLE `type`;
DROP Table maladie;
DROP Table laboratoire;

CREATE TABLE laboratoire (
    id_laboratoire VARCHAR(50),
    nom_laboratoire VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_laboratoire)
);

CREATE TABLE maladie (
    id_maladie VARCHAR(50),
    nom_maladie VARCHAR(250) NOT NULL,
    PRIMARY KEY (id_maladie)
);

CREATE TABLE type (
    id_type VARCHAR(50),
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_type)
);

CREATE TABLE unite_mesure (
    id_unite_mesure VARCHAR(50),
    unite_mesure VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_unite_mesure)
);

CREATE TABLE categorie (
    id_categorie VARCHAR(50),
    categorie VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_categorie)
);

CREATE TABLE sous_categorie (
    id_sous_categorie VARCHAR(50),
    sous_categorie VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_sous_categorie)
);

CREATE TABLE symptome (
    id_symptome VARCHAR(50),
    symptome VARCHAR(250) NOT NULL,
    PRIMARY KEY (id_symptome)
);

CREATE TABLE forme (
    id_forme VARCHAR(50),
    forme VARCHAR(250) NOT NULL,
    id_unite_mesure VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_forme),
    FOREIGN KEY (id_unite_mesure) REFERENCES unite_mesure (id_unite_mesure)
);

CREATE TABLE mode_administration (
    id_mode_administration VARCHAR(50),
    mode_administration VARCHAR(250) NOT NULL,
    PRIMARY KEY (id_mode_administration)
);

CREATE TABLE type_mouvement (
    id_type_mouvement VARCHAR(50),
    type_mouvement VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_type_mouvement)
);

CREATE TABLE produit (
    id_produit VARCHAR(50),
    nom_produit VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    id_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_produit),
    UNIQUE (nom_produit),
    FOREIGN KEY (id_type) REFERENCES type (id_type)
);

CREATE TABLE produit_forme (
    id_produit_forme VARCHAR(50),
    age_maximum INT NOT NULL,
    quantite VARCHAR(50),
    prix_achat_unitaire DECIMAL(18, 2) NOT NULL,
    age_minimum INT NOT NULL,
    nombre INT NOT NULL,
    prix_vente_unitaire DECIMAL(18, 2) NOT NULL,
    id_mode_administration VARCHAR(50) NOT NULL,
    id_forme VARCHAR(50) NOT NULL,
    id_produit VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_produit_forme),
    FOREIGN KEY (id_mode_administration) REFERENCES mode_administration (id_mode_administration),
    FOREIGN KEY (id_forme) REFERENCES forme (id_forme),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit)
);

CREATE TABLE mouvement (
    id_mouvement VARCHAR(50),
    quantite INT NOT NULL,
    prix_unitaire DECIMAL(18, 2) NOT NULL,
    date_mouvement DATE NOT NULL,
    id_produit_forme VARCHAR(50) NOT NULL,
    id_type_mouvement VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_mouvement),
    FOREIGN KEY (id_produit_forme) REFERENCES produit_forme (id_produit_forme),
    FOREIGN KEY (id_type_mouvement) REFERENCES type_mouvement (id_type_mouvement)
);

CREATE TABLE produit_sous_categorie (
    id_produit_sous_categorie VARCHAR(50) PRIMARY KEY,
    id_produit VARCHAR(50),
    id_sous_categorie VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_sous_categorie) REFERENCES sous_categorie (id_sous_categorie)
);

CREATE TABLE maladie_symptome (
    id_maladie_symptome VARCHAR(50) PRIMARY KEY,
    id_maladie VARCHAR(50),
    id_symptome VARCHAR(50),
    FOREIGN KEY (id_maladie) REFERENCES maladie (id_maladie),
    FOREIGN KEY (id_symptome) REFERENCES symptome (id_symptome)
);

CREATE TABLE produit_symptome (
    id_produit_symptome VARCHAR(50) PRIMARY KEY,
    id_produit VARCHAR(50),
    id_symptome VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_symptome) REFERENCES symptome (id_symptome)
);

CREATE TABLE categorie_sous_categorie (
    id_categorie_sous_categorie VARCHAR(50) PRIMARY KEY,
    id_categorie VARCHAR(50),
    id_sous_categorie VARCHAR(50),
    FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie),
    FOREIGN KEY (id_sous_categorie) REFERENCES sous_categorie (id_sous_categorie)
);

CREATE TABLE type_categorie (
    id_type_categorie VARCHAR(50) PRIMARY KEY,
    id_type VARCHAR(50),
    id_categorie VARCHAR(50),
    FOREIGN KEY (id_type) REFERENCES type (id_type),
    FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie)
);

CREATE TABLE produit_forme_laboratoire (
    id_produit_forme_laboratoire VARCHAR(50) PRIMARY KEY,
    id_laboratoire VARCHAR(50),
    id_produit_forme VARCHAR(50),
    FOREIGN KEY (id_laboratoire) REFERENCES laboratoire (id_laboratoire),
    FOREIGN KEY (id_produit_forme) REFERENCES produit_forme (id_produit_forme)
);