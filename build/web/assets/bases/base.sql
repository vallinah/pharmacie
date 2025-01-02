-- CREATE DATABASE Pharmacie;
-- GRANT ALL PRIVILEGES ON DATABASE Pharmacie TO mihary;
-- ALTER DATABASE Pharmacie OWNER TO mihary;
-- Suppression des tables existantes

-- Création des séquences pour chaque table
CREATE SEQUENCE IF NOT EXISTS laboratoire_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS maladie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS type_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS unite_mesure_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS categorie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sous_categorie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS symptome_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS forme_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mode_administration_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS type_mouvement_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_forme_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mouvement_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_sous_categorie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS maladie_symptome_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_symptome_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS categorie_sous_categorie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS type_categorie_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS produit_forme_laboratoire_id_seq START WITH 1 INCREMENT BY 1;

-- Création des tables
CREATE TABLE laboratoire (
    id_laboratoire VARCHAR(50) DEFAULT nextval('laboratoire_id_seq') PRIMARY KEY,
    nom_laboratoire VARCHAR(50) NOT NULL
);

CREATE TABLE maladie (
    id_maladie VARCHAR(50) DEFAULT nextval('maladie_id_seq') PRIMARY KEY,
    nom_maladie VARCHAR(250) NOT NULL
);

CREATE TABLE "type" (
    id_type VARCHAR(50) DEFAULT nextval('type_id_seq') PRIMARY KEY,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE unite_mesure (
    id_unite_mesure VARCHAR(50) DEFAULT nextval('unite_mesure_id_seq') PRIMARY KEY,
    unite_mesure VARCHAR(50) NOT NULL
);

CREATE TABLE categorie (
    id_categorie VARCHAR(50) DEFAULT nextval('categorie_id_seq') PRIMARY KEY,
    categorie VARCHAR(150) NOT NULL
);

CREATE TABLE sous_categorie (
    id_sous_categorie VARCHAR(50) DEFAULT nextval('sous_categorie_id_seq') PRIMARY KEY,
    sous_categorie VARCHAR(150) NOT NULL
);

CREATE TABLE symptome (
    id_symptome VARCHAR(50) DEFAULT nextval('symptome_id_seq') PRIMARY KEY,
    symptome VARCHAR(250) NOT NULL
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

CREATE TABLE type_mouvement (
    id_type_mouvement VARCHAR(50) DEFAULT nextval('type_mouvement_id_seq') PRIMARY KEY,
    type_mouvement VARCHAR(150) NOT NULL
);

CREATE TABLE produit (
    id_produit VARCHAR(50) DEFAULT nextval('produit_id_seq') PRIMARY KEY,
    nom_produit VARCHAR(150) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    id_type VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_type) REFERENCES "type" (id_type)
);

CREATE TABLE produit_forme (
    id_produit_forme VARCHAR(50) DEFAULT nextval('produit_forme_id_seq') PRIMARY KEY,
    age_maximum INT NOT NULL,
    quantite VARCHAR(50),
    prix_achat_unitaire NUMERIC(18, 2) NOT NULL,
    age_minimum INT NOT NULL,
    nombre INT NOT NULL,
    prix_vente_unitaire NUMERIC(18, 2) NOT NULL,
    id_mode_administration VARCHAR(50) NOT NULL,
    id_forme VARCHAR(50) NOT NULL,
    id_produit VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_mode_administration) REFERENCES mode_administration (id_mode_administration),
    FOREIGN KEY (id_forme) REFERENCES forme (id_forme),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit)
);

CREATE TABLE mouvement (
    id_mouvement VARCHAR(50) DEFAULT nextval('mouvement_id_seq') PRIMARY KEY,
    quantite INT NOT NULL,
    prix_unitaire NUMERIC(18, 2) NOT NULL,
    date_mouvement DATE NOT NULL,
    id_produit_forme VARCHAR(50) NOT NULL,
    id_type_mouvement VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_produit_forme) REFERENCES produit_forme (id_produit_forme),
    FOREIGN KEY (id_type_mouvement) REFERENCES type_mouvement (id_type_mouvement)
);

CREATE TABLE produit_sous_categorie (
    id_produit_sous_categorie VARCHAR(50) DEFAULT nextval('produit_sous_categorie_id_seq') PRIMARY KEY,
    id_produit VARCHAR(50),
    id_sous_categorie VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_sous_categorie) REFERENCES sous_categorie (id_sous_categorie)
);

CREATE TABLE maladie_symptome (
    id_maladie_symptome VARCHAR(50) DEFAULT nextval('maladie_symptome_id_seq') PRIMARY KEY,
    id_maladie VARCHAR(50),
    id_symptome VARCHAR(50),
    FOREIGN KEY (id_maladie) REFERENCES maladie (id_maladie),
    FOREIGN KEY (id_symptome) REFERENCES symptome (id_symptome)
);

CREATE TABLE produit_symptome (
    id_produit_symptome VARCHAR(50) DEFAULT nextval('produit_symptome_id_seq') PRIMARY KEY,
    id_produit VARCHAR(50),
    id_symptome VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_symptome) REFERENCES symptome (id_symptome)
);

CREATE TABLE categorie_sous_categorie (
    id_categorie_sous_categorie VARCHAR(50) DEFAULT nextval('categorie_sous_categorie_id_seq') PRIMARY KEY,
    id_categorie VARCHAR(50),
    id_sous_categorie VARCHAR(50),
    FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie),
    FOREIGN KEY (id_sous_categorie) REFERENCES sous_categorie (id_sous_categorie)
);

CREATE TABLE type_categorie (
    id_type_categorie VARCHAR(50) DEFAULT nextval('type_categorie_id_seq') PRIMARY KEY,
    id_type VARCHAR(50),
    id_categorie VARCHAR(50),
    FOREIGN KEY (id_type) REFERENCES "type" (id_type),
    FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie)
);

CREATE TABLE produit_forme_laboratoire (
    id_produit_forme_laboratoire VARCHAR(50) DEFAULT nextval('produit_forme_laboratoire_id_seq') PRIMARY KEY,
    id_laboratoire VARCHAR(50),
    id_produit_forme VARCHAR(50),
    FOREIGN KEY (id_laboratoire) REFERENCES laboratoire (id_laboratoire),
    FOREIGN KEY (id_produit_forme) REFERENCES produit_forme (id_produit_forme)
);