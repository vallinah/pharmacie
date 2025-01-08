-- Création des séquences pour chaque table
CREATE SEQUENCE IF NOT EXISTS laboratoire_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS maladie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS categorie_personne_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS unite_mesure_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS forme_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mode_administration_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_id_seq START WITH 1 INCREMENT BY 1;

-- Création des tables
CREATE TABLE laboratoire (
    id_laboratoire VARCHAR(50) DEFAULT nextval('laboratoire_id_seq') PRIMARY KEY,
    nom_laboratoire VARCHAR(50) NOT NULL
);

CREATE TABLE maladie (
    id_maladie VARCHAR(50) DEFAULT nextval('maladie_id_seq') PRIMARY KEY,
    nom_maladie VARCHAR(250) NOT NULL
);

CREATE TABLE categorie_personne (
    id_categorie_personne VARCHAR(50) DEFAULT nextval('categorie_personne_id_seq') PRIMARY KEY,
    categorie_personne VARCHAR(50) NOT NULL
);

CREATE TABLE unite_mesure (
    id_unite_mesure VARCHAR(50) DEFAULT nextval('unite_mesure_id_seq') PRIMARY KEY,
    unite_mesure VARCHAR(50) NOT NULL
);

CREATE TABLE forme (
    id_forme VARCHAR(50) DEFAULT nextval('forme_id_seq') PRIMARY KEY,
    forme VARCHAR(250) NOT NULL,
    id_unite_mesure VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_unite_mesure) REFERENCES unite_mesure (id_unite_mesure)
);

CREATE TABLE mode_administration (
    id_mode_administration VARCHAR(50) DEFAULT nextval('mode_administration_id_seq') PRIMARY KEY,
    mode_administration VARCHAR(250) NOT NULL
);

CREATE TABLE produit (
    id_produit VARCHAR(50) DEFAULT nextval('produit_id_seq') PRIMARY KEY,
    nom_produit VARCHAR(150) NOT NULL,
    prix_vente_unitaire NUMERIC(18, 2) NOT NULL,
    nombre INTEGER NOT NULL,
    prix_achat_unitaire NUMERIC(18, 2) NOT NULL,
    quantite VARCHAR(50),
    description TEXT NOT NULL,
    id_forme VARCHAR(50) NOT NULL,
    id_mode_administration VARCHAR(50) NOT NULL,
    id_laboratoire VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_forme) REFERENCES forme (id_forme),
    FOREIGN KEY (id_mode_administration) REFERENCES mode_administration (id_mode_administration),
    FOREIGN KEY (id_laboratoire) REFERENCES laboratoire (id_laboratoire)
);

CREATE TABLE produit_categorie_personne (
    id_produit VARCHAR(50),
    id_categorie_personne VARCHAR(50),
    PRIMARY KEY (id_produit, id_categorie_personne),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_categorie_personne) REFERENCES categorie_personne (id_categorie_personne)
);

CREATE TABLE produit_maladie (
    id_produit VARCHAR(50),
    id_maladie VARCHAR(50),
    PRIMARY KEY (id_produit, id_maladie),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_maladie) REFERENCES maladie (id_maladie)
);