-- Active: 1735375537127@@127.0.0.1@5432@pharmacie@public
-- Suppression des séquences si elles existent

-- Suppression des tables si elles existent
DROP TABLE IF EXISTS produit_maladie CASCADE;
DROP TABLE IF EXISTS produit_categorie_personne CASCADE;
DROP TABLE if EXISTS mouvement;
DROP TABLE IF EXISTS produit CASCADE;
DROP TABLE IF EXISTS mode_administration CASCADE;
DROP TABLE IF EXISTS forme CASCADE;
DROP TABLE IF EXISTS unite_mesure CASCADE;
DROP TABLE IF EXISTS categorie_personne CASCADE;
DROP TABLE IF EXISTS maladie CASCADE;
DROP TABLE IF EXISTS laboratoire CASCADE;
DROP Table if EXISTS conseil_du_mois CASCADE;
DROP Table if EXISTS client CASCADE;

DO $$ 
BEGIN
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'laboratoire_id_seq') THEN DROP SEQUENCE laboratoire_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'maladie_id_seq') THEN DROP SEQUENCE maladie_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'categorie_personne_id_seq') THEN DROP SEQUENCE categorie_personne_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'unite_mesure_id_seq') THEN DROP SEQUENCE unite_mesure_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'forme_id_seq') THEN DROP SEQUENCE forme_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'mode_administration_id_seq') THEN DROP SEQUENCE mode_administration_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'produit_id_seq') THEN DROP SEQUENCE produit_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'produit_categorie_personne_id_seq') THEN DROP SEQUENCE produit_categorie_personne_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'produit_maladie_id_seq') THEN DROP SEQUENCE produit_maladie_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'mouvement_id_seq') THEN DROP SEQUENCE mouvement_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'conseil_du_mois_id_seq') THEN DROP SEQUENCE conseil_du_mois_id_seq; END IF;
    IF EXISTS (SELECT 1 FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'client_id_seq') THEN DROP SEQUENCE client_id_seq; END IF;
END $$;

-- Création des séquences
CREATE SEQUENCE laboratoire_id_seq START 1;
CREATE SEQUENCE maladie_id_seq START 1;
CREATE SEQUENCE categorie_personne_id_seq START 1;
CREATE SEQUENCE unite_mesure_id_seq START 1;
CREATE SEQUENCE forme_id_seq START 1;
CREATE SEQUENCE mode_administration_id_seq START 1;
CREATE SEQUENCE produit_id_seq START 1;
CREATE SEQUENCE mouvement_id_seq START 1;
CREATE SEQUENCE produit_categorie_personne_id_seq START 1;
CREATE SEQUENCE produit_maladie_id_seq START 1;
CREATE SEQUENCE conseil_du_mois_id_seq START 1;
CREATE SEQUENCE client_id_seq START 1;

-- Création des tables
CREATE TABLE laboratoire (
    id_laboratoire VARCHAR(50) DEFAULT CONCAT('LAB', LPAD(nextval('laboratoire_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    nom_laboratoire VARCHAR(50) NOT NULL
);

CREATE TABLE maladie (
    id_maladie VARCHAR(50) DEFAULT CONCAT('MLD', LPAD(nextval('maladie_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    nom_maladie VARCHAR(250) NOT NULL
);

CREATE TABLE categorie_personne (
    id_categorie_personne VARCHAR(50) DEFAULT CONCAT('CATP', LPAD(nextval('categorie_personne_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    categorie_personne VARCHAR(50) NOT NULL
);

CREATE TABLE unite_mesure (
    id_unite_mesure VARCHAR(50) DEFAULT CONCAT('UNI', LPAD(nextval('unite_mesure_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    unite_mesure VARCHAR(50) NOT NULL
);

CREATE TABLE forme (
    id_forme VARCHAR(50) DEFAULT CONCAT('FRM', LPAD(nextval('forme_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    forme VARCHAR(250) NOT NULL,
    id_unite_mesure VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_unite_mesure) REFERENCES unite_mesure (id_unite_mesure)
);

CREATE TABLE mode_administration (
    id_mode_administration VARCHAR(50) DEFAULT CONCAT('MOD', LPAD(nextval('mode_administration_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    mode_administration VARCHAR(250) NOT NULL
);

CREATE TABLE produit (
    id_produit VARCHAR(50) DEFAULT CONCAT('PRD', LPAD(nextval('produit_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
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

CREATE Table client (
    id_client VARCHAR(50) DEFAULT CONCAT('CLT', LPAD(nextval('client_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    nom_client VARCHAR(100) NOT NULL
)

CREATE TABLE mouvement(
   id_mouvement VARCHAR(50) DEFAULT CONCAT('MVT', LPAD(nextval('mouvement_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
   quantite INTEGER NOT NULL,
   prix_achat_unitaire NUMERIC(18,2)  DEFAULT 0,
   prix_vente_unitaire NUMERIC(18,2)  DEFAULT 0,
   date_mouvement DATE NOT NULL,
   id_produit VARCHAR(50)  NOT NULL,
   id_client VARCHAR(50),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit),
   FOREIGN KEY(id_client) REFERENCES client(id_client)
);


CREATE TABLE produit_categorie_personne (
    id_produit_categorie_personne VARCHAR(50) DEFAULT CONCAT('PCP', LPAD(nextval('produit_categorie_personne_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    id_produit VARCHAR(50),
    id_categorie_personne VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_categorie_personne) REFERENCES categorie_personne (id_categorie_personne)
);

CREATE TABLE produit_maladie (
    id_produit_maladie VARCHAR(50) DEFAULT CONCAT('PMD', LPAD(nextval('produit_maladie_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    id_produit VARCHAR(50),
    id_maladie VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit),
    FOREIGN KEY (id_maladie) REFERENCES maladie (id_maladie)
);


CREATE Table conseil_du_mois (
    id_conseil_du_mois VARCHAR(50) DEFAULT CONCAT('CDM', LPAD(nextval('conseil_du_mois_id_seq')::TEXT, 8, '0')) PRIMARY KEY,
    date_debut DATE,
    date_fin date,
    id_produit VARCHAR(50),
    FOREIGN KEY (id_produit) REFERENCES produit (id_produit)
);


-- Insertion des données dans `laboratoire`
INSERT INTO laboratoire (nom_laboratoire)
VALUES 
    ('Sanofi'), ('Pfizer'), ('Roche'), ('Novartis'), ('Merck'),
    ('Johnson & Johnson'), ('GSK'), ('AstraZeneca'), ('AbbVie'), ('Bayer');

-- Insertion des données dans `maladie`
INSERT INTO maladie (nom_maladie)
VALUES 
    ('Hypertension'), ('Diabète'), ('Cancer du sein'), ('Cancer du poumon'), 
    ('Hépatite C'), ('HIV/SIDA'), ('Malaria'), ('Tuberculose'), ('Grippe'), ('COVID-19');

-- Insertion des données dans `categorie_personne`
INSERT INTO categorie_personne (categorie_personne)
VALUES 
    ('Enfant'), ('Adolescent'), ('Adulte'), ('Senior'), ('Femme enceinte'),
    ('Nourrisson'), ('Personne immunodéprimée'), ('Athlète'), ('Travailleur en usine'), ('Étudiant');

-- Insertion dans `unite_mesure`
INSERT INTO unite_mesure (unite_mesure)
VALUES 
    ('mg'), ('ml'), ('g'), ('L'), ('tablette');

-- Insertion dans `forme`
INSERT INTO forme (forme, id_unite_mesure)
SELECT CONCAT('Forme_', id_unite_mesure), id_unite_mesure FROM unite_mesure;

-- Insertion dans `mode_administration`
INSERT INTO mode_administration (mode_administration)
VALUES 
    ('Oral'), ('Intraveineuse'), ('Injection'), ('Topique'), ('Inhalation');

--insertion  produit

DO $$
DECLARE
    forme_id VARCHAR(50);
    mode_id VARCHAR(50);
    labo_id VARCHAR(50);
BEGIN
    FOR i IN 1..10 LOOP
        SELECT id_forme INTO forme_id FROM forme LIMIT 1 OFFSET ((i-1) % 5);
        SELECT id_mode_administration INTO mode_id FROM mode_administration LIMIT 1 OFFSET ((i-1) % 5);
        SELECT id_laboratoire INTO labo_id FROM laboratoire LIMIT 1 OFFSET ((i-1) % 10);
        INSERT INTO produit (nom_produit, prix_vente_unitaire, nombre, prix_achat_unitaire, quantite, description, id_forme, id_mode_administration, id_laboratoire)
        VALUES (
            CONCAT('Produit_', i), 
            10.0 * i, 
            i * 10, 
            5.0 * i, 
            CONCAT(i * 10, ' unités'), 
            CONCAT('Description du produit_', i), 
            forme_id, 
            mode_id, 
            labo_id
        );
    END LOOP;
END $$;