-- Active: 1735375537127@@127.0.0.1@5432@pharmacie@public

-- DROP TABLE 
DROP TABLE IF EXISTS produit_categorie_personne CASCADE;
DROP TABLE IF EXISTS produit_maladie CASCADE;
DROP TABLE IF EXISTS vente_detail CASCADE;
DROP TABLE IF EXISTS vente CASCADE;
DROP TABLE IF EXISTS conseil_du_mois CASCADE;
DROP TABLE IF EXISTS historique_prix_produit CASCADE;
DROP TABLE IF EXISTS vendeur CASCADE;
DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS produit CASCADE;
DROP TABLE IF EXISTS genre CASCADE;
DROP TABLE IF EXISTS commission CASCADE;
DROP TABLE IF EXISTS mode_administration CASCADE;
DROP TABLE IF EXISTS categorie_personne CASCADE;
DROP TABLE IF EXISTS maladie CASCADE;

-- DROP SEQUENCE 

DROP SEQUENCE IF EXISTS maladie_id_seq;
DROP SEQUENCE IF EXISTS categorie_personne_id_seq;
DROP SEQUENCE IF EXISTS mode_administration_id_seq;
DROP SEQUENCE IF EXISTS commission_id_seq;
DROP SEQUENCE IF EXISTS genre_id_seq;
DROP SEQUENCE IF EXISTS produit_id_seq;
DROP SEQUENCE IF EXISTS client_id_seq;
DROP SEQUENCE IF EXISTS vendeur_id_seq;
DROP SEQUENCE IF EXISTS historique_prix_produit_id_seq;
DROP SEQUENCE IF EXISTS conseil_du_mois_id_seq;
DROP SEQUENCE IF EXISTS vente_id_seq;
DROP SEQUENCE IF EXISTS vente_detail_id_seq;

-- CREATE SEQUENCE 

CREATE SEQUENCE IF NOT EXISTS maladie_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS categorie_personne_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS mode_administration_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS commission_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS genre_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS produit_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS client_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS vendeur_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS historique_prix_produit_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS conseil_du_mois_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS vente_id_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS vente_detail_id_seq START WITH 1;


CREATE TABLE maladie( 
    id_maladie VARCHAR(50) DEFAULT CONCAT('MLD', LPAD(nextval('maladie_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    nom_maladie VARCHAR(250) NOT NULL
);

CREATE TABLE categorie_personne( 
    id_categorie_personne VARCHAR(50) DEFAULT CONCAT('CATP', LPAD(nextval('categorie_personne_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    categorie_personne VARCHAR(150) NOT NULL
);

CREATE TABLE mode_administration( 
    id_mode_administration VARCHAR(50) DEFAULT CONCAT('MODA', LPAD(nextval('mode_administration_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    mode_administration VARCHAR(250) NOT NULL
);

CREATE TABLE commission( 
    id_commission VARCHAR(50) DEFAULT CONCAT('COM', LPAD(nextval('commission_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    commission NUMERIC(15,2) NOT NULL, 
    date_commission DATE NOT NULL
);

CREATE TABLE genre( 
    id_genre VARCHAR(50) DEFAULT CONCAT('GEN', LPAD(nextval('genre_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    genre VARCHAR(50) NOT NULL
);

CREATE TABLE produit( 
    id_produit VARCHAR(50) DEFAULT CONCAT('PRO', LPAD(nextval('produit_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    nom_produit VARCHAR(150) NOT NULL, 
    prix_vente_unitaire NUMERIC(18,2) NOT NULL, 
    description TEXT NOT NULL, 
    date_modification DATE NOT NULL, 
    id_mode_administration VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_mode_administration) REFERENCES mode_administration(id_mode_administration)
);

CREATE TABLE client( 
    id_client VARCHAR(50) DEFAULT CONCAT('CLI', LPAD(nextval('client_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    nom_client VARCHAR(250) NOT NULL, 
    id_genre VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE vendeur( 
    id_vendeur VARCHAR(50) DEFAULT CONCAT('VEN', LPAD(nextval('vendeur_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    nom_vendeur VARCHAR(250) NOT NULL, 
    id_genre VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE historique_prix_produit( 
    id_historique_prix_produit VARCHAR(50) DEFAULT CONCAT('HPP', LPAD(nextval('historique_prix_produit_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    prix_vente_unitaire NUMERIC(15,2) NOT NULL, 
    date_modification DATE NOT NULL, 
    id_produit VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE conseil_du_mois( 
    id_conseil_du_mois VARCHAR(50) DEFAULT CONCAT('CDM', LPAD(nextval('conseil_du_mois_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    date_debut DATE NOT NULL, 
    date_fin DATE NOT NULL, 
    id_produit VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE vente( 
    id_vente VARCHAR(50) DEFAULT CONCAT('VNT', LPAD(nextval('vente_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    prix_total NUMERIC(15,2) NOT NULL, 
    date_vente DATE NOT NULL, 
    id_commission VARCHAR(50) NOT NULL, 
    id_vendeur VARCHAR(50) NOT NULL, 
    id_client VARCHAR(50) NOT NULL, 
    id_produit VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_commission) REFERENCES commission(id_commission), 
    FOREIGN KEY(id_vendeur) REFERENCES vendeur(id_vendeur), 
    FOREIGN KEY(id_client) REFERENCES client(id_client), 
    FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE vente_detail( 
    id_vente_detail VARCHAR(50) DEFAULT CONCAT('VDT', LPAD(nextval('vente_detail_id_seq')::TEXT, 8, '0')) PRIMARY KEY, 
    quantite INTEGER NOT NULL, 
    prix_unitaire NUMERIC(18,2) NOT NULL, 
    id_vente VARCHAR(50) NOT NULL, 
    FOREIGN KEY(id_vente) REFERENCES vente(id_vente)
);

CREATE TABLE produit_maladie( 
    id_produit VARCHAR(50) NOT NULL, 
    id_maladie VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id_produit, id_maladie), 
    FOREIGN KEY(id_produit) REFERENCES produit(id_produit), 
    FOREIGN KEY(id_maladie) REFERENCES maladie(id_maladie)
);

CREATE TABLE produit_categorie_personne( 
    id_produit VARCHAR(50) NOT NULL, 
    id_categorie_personne VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id_produit, id_categorie_personne), 
    FOREIGN KEY(id_produit) REFERENCES produit(id_produit), 
    FOREIGN KEY(id_categorie_personne) REFERENCES categorie_personne(id_categorie_personne)
);

-- Création de la fonction déclencheur
CREATE OR REPLACE FUNCTION update_historique_prix_produit()
RETURNS TRIGGER AS $$
BEGIN
    -- Vérifier si le prix de vente unitaire a changé
    IF NEW.prix_vente_unitaire IS DISTINCT FROM OLD.prix_vente_unitaire THEN
        -- Insérer dans historique_prix_produit uniquement si le prix de vente unitaire a changé
        INSERT INTO historique_prix_produit (id_produit, prix_vente_unitaire, date_modification)
        VALUES (NEW.id_produit, NEW.prix_vente_unitaire, NEW.date_modification);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du déclencheur
CREATE TRIGGER trg_historique_prix_produit
AFTER INSERT OR UPDATE ON produit
FOR EACH ROW
EXECUTE FUNCTION update_historique_prix_produit();

-- Insertion dans la table maladie
INSERT INTO maladie (nom_maladie) VALUES 
('Grippe'),
('Diabète'),
('Hypertension'),
('Asthme'),
('Cancer'),
('Varicelle'),
('Tuberculose'),
('Hépatite'),
('Covid-19'),
('Pneumonie'),
('Anémie'),
('Sida'),
('Alzheimer'),
('Maladie de Parkinson'),
('Arthrite'),
('Goutte'),
('Cirrhose'),
('Bronchite'),
('Lupus'),
('Epilepsie');

-- Insertion dans la table categorie_personne
INSERT INTO categorie_personne (categorie_personne) VALUES 
('Enfant'),
('Adolescent'),
('Adulte'),
('Sénior'),
('Femme enceinte'),
('Homme'),
('Femme'),
('Personne en situation de handicap'),
('Sportif'),
('Non sportif'),
('Personne âgée'),
('Personne atteinte de maladies chroniques'),
('Personne immunodéprimée'),
('Personne en surpoids'),
('Personne sous traitement médical'),
('Personne atteinte de troubles mentaux'),
('Personne en rééducation'),
('Personne avec allergies'),
('Personne active'),
('Personne retraitée');

-- Insertion dans la table mode_administration
INSERT INTO mode_administration (mode_administration) VALUES 
('Orale'),
('Injectable'),
('Topique'),
('Inhalation'),
('Transdermique'),
('Rectale'),
('Sublinguale'),
('Intranasale'),
('Intraveineuse'),
('Intramusculaire'),
('Sous-cutanée'),
('Intraoculaire'),
('Intra-articulaire'),
('Intracardiaque'),
('Vaginale'),
('Urologique'),
('Pédiatrique'),
('Gériatrique'),
('Chirurgicale'),
('Anesthésique');

-- Insertion dans la table commission
INSERT INTO commission (commission, date_commission) VALUES 
(150.00, '2025-02-01'),
(200.50, '2025-02-02'),
(180.75, '2025-02-03'),
(210.20, '2025-02-04'),
(190.00, '2025-02-05'),
(160.00, '2025-02-06'),
(220.30, '2025-02-07'),
(250.75, '2025-02-08'),
(280.00, '2025-02-09'),
(300.50, '2025-02-10'),
(310.00, '2025-02-11'),
(330.00, '2025-02-12'),
(340.25, '2025-02-13'),
(350.10, '2025-02-14'),
(360.00, '2025-02-15'),
(370.20, '2025-02-16'),
(380.50, '2025-02-17'),
(390.75, '2025-02-18'),
(400.00, '2025-02-19'),
(410.10, '2025-02-20');

-- Insertion dans la table genre
INSERT INTO genre (genre) VALUES 
('Homme'),
('Femme'),
('Non binaire'),
('Transgenre'),
('Intersexué'),
('Cisgenre'),
('Autre'),
('Masculin'),
('Féminin'),
('Ne se prononce pas'),
('Androgyne'),
('Bigender'),
('Genderfluid'),
('Agender'),
('Demi-gender'),
('Gender non-conforme'),
('Transmasculin'),
('Transféminin'),
('Non spécifié'),
('Intergenre');

-- Insertion dans la table produit
INSERT INTO produit (nom_produit, prix_vente_unitaire, description, date_modification, id_mode_administration) VALUES 
('Paracétamol', 5.99, 'Analgésique et antipyrétique', '2025-01-01', 'MODA00000001'),
('Ibuprofène', 8.49, 'Anti-inflammatoire et analgésique', '2025-01-02', 'MODA00000002'),
('Aspirine', 4.99, 'Antalgiques et anti-inflammatoires', '2025-01-03', 'MODA00000003'),
('Antibiotiques', 15.00, 'Médicaments pour lutter contre les infections bactériennes', '2025-01-04', 'MODA00000004'),
('Vitamines C', 3.00, 'Complément alimentaire pour renforcer le système immunitaire', '2025-01-05', 'MODA00000005'),
('Sirops contre la toux', 6.99, 'Médicament pour soulager la toux', '2025-01-06', 'MODA00000006'),
('Médicament contre l''hypertension', 18.00, 'Traitement de l''hypertension', '2025-01-07', 'MODA00000007'),
('Pansement', 1.50, 'Pansement adhésif pour petites blessures', '2025-01-08', 'MODA00000008'),
('Crème hydratante', 9.99, 'Crème pour hydrater la peau', '2025-01-09', 'MODA00000009'),
('Détenteur de fièvre', 12.99, 'Médicament pour faire baisser la température', '2025-01-10', 'MODA00000010'),
('Gélules de calcium', 10.00, 'Complément alimentaire pour les os', '2025-01-11', 'MODA00000011'),
('Thérapie contre le stress', 7.50, 'Médicament pour réduire le stress', '2025-01-12', 'MODA00000012'),
('Médicament contre l''acné', 8.00, 'Crème ou gel contre l''acné', '2025-01-13', 'MODA00000013'),
('Pilules contraceptives', 14.99, 'Méthode contraceptive orale', '2025-01-14', 'MODA00000014'),
('Antidépresseur', 20.00, 'Médicament pour traiter les troubles dépressifs', '2025-01-15', 'MODA00000015'),
('Probiotiques', 5.00, 'Complément pour la flore intestinale', '2025-01-16', 'MODA00000016'),
('Crème solaire', 11.00, 'Protection solaire pour la peau', '2025-01-17', 'MODA00000017'),
('Collyre', 4.00, 'Médicament pour les yeux', '2025-01-18', 'MODA00000018'),
('Médicament contre les allergies', 7.20, 'Antihistaminique pour soulager les allergies', '2025-01-19', 'MODA00000019'),
('Antidouleur musculaire', 9.80, 'Gel pour soulager les douleurs musculaires', '2025-01-20', 'MODA00000020');

-- Insertion dans la table client
INSERT INTO client (nom_client, id_genre) VALUES 
('Jean Dupont', 'GEN00000001'),
('Marie Dubois', 'GEN00000002'),
('Alexis Martin', 'GEN00000001'),
('Lucie Lefevre', 'GEN00000002'),
('Claire Bernard', 'GEN00000002'),
('Paul Thomas', 'GEN00000001'),
('Sophie Dubois', 'GEN00000002'),
('Olivier Rousseau', 'GEN00000001'),
('Isabelle Dufresne', 'GEN00000002'),
('Marc Lefevre', 'GEN00000001'),
('Chantal Dupuis', 'GEN00000002'),
('Michel Hebert', 'GEN00000001'),
('Nathalie Vidal', 'GEN00000002'),
('Jean-Pierre Caron', 'GEN00000001'),
('Julie Pires', 'GEN00000002'),
('Bernard Lefevre', 'GEN00000001'),
('Monique Aubert', 'GEN00000002'),
('Éric Martin', 'GEN00000001'),
('Catherine Delacroix', 'GEN00000002'),
('Yves Charpentier', 'GEN00000001');

-- Insertion dans la table vendeur
INSERT INTO vendeur (nom_vendeur, id_genre) VALUES 
('Luc Besson', 'GEN00000001'),
('Marie Curie', 'GEN00000002'),
('Jean Dupont', 'GEN00000001'),
('Sophie Dubois', 'GEN00000002'),
('Paul Bernard', 'GEN00000001'),
('Isabelle Durand', 'GEN00000002'),
('Bernard Lefevre', 'GEN00000001'),
('Catherine Perrin', 'GEN00000002'),
('Olivier Pires', 'GEN00000001'),
('Chantal Hebert', 'GEN00000002'),
('Michel Bernard', 'GEN00000001'),
('Nathalie Caron', 'GEN00000002'),
('Jean-Pierre Charpentier', 'GEN00000001'),
('Julie Lefevre', 'GEN00000002'),
('Bernard Dupuis', 'GEN00000001'),
('Monique Besson', 'GEN00000002'),
('Éric Durand', 'GEN00000001'),
('Catherine Lefevre', 'GEN00000002'),
('Yves Caron', 'GEN00000001'),
('Sophie Perrin', 'GEN00000002');

-- Insertion dans la table conseil_du_mois
INSERT INTO conseil_du_mois (date_debut, date_fin, id_produit) VALUES 
('2025-02-01', '2025-02-28', 'PRO00000001'),
('2025-02-01', '2025-02-28', 'PRO00000002'),
('2025-02-01', '2025-02-28', 'PRO00000003'),
('2025-02-01', '2025-02-28', 'PRO00000004'),
('2025-02-01', '2025-02-28', 'PRO00000005'),
('2025-02-01', '2025-02-28', 'PRO00000006'),
('2025-02-01', '2025-02-28', 'PRO00000007'),
('2025-02-01', '2025-02-28', 'PRO00000008'),
('2025-02-01', '2025-02-28', 'PRO00000009'),
('2025-02-01', '2025-02-28', 'PRO00000010'),
('2025-02-01', '2025-02-28', 'PRO00000011'),
('2025-02-01', '2025-02-28', 'PRO00000012'),
('2025-02-01', '2025-02-28', 'PRO00000013'),
('2025-02-01', '2025-02-28', 'PRO00000014'),
('2025-02-01', '2025-02-28', 'PRO00000015'),
('2025-02-01', '2025-02-28', 'PRO00000016'),
('2025-02-01', '2025-02-28', 'PRO00000017'),
('2025-02-01', '2025-02-28', 'PRO00000018'),
('2025-02-01', '2025-02-28', 'PRO00000019'),
('2025-02-01', '2025-02-28', 'PRO00000020');

-- Insertion dans la table vente
INSERT INTO vente (prix_total, date_vente, id_commission, id_vendeur, id_client, id_produit) VALUES 
(100.00, '2025-02-01', 'COM00000001', 'VEN00000001', 'CLI00000001', 'PRO00000001'),
(120.50, '2025-02-02', 'COM00000002', 'VEN00000002', 'CLI00000002', 'PRO00000002'),
(150.75, '2025-02-03', 'COM00000003', 'VEN00000003', 'CLI00000003', 'PRO00000003'),
(180.00, '2025-02-04', 'COM00000004', 'VEN00000004', 'CLI00000004', 'PRO00000004'),
(90.50, '2025-02-05', 'COM00000005', 'VEN00000005', 'CLI00000005', 'PRO00000005'),
(110.00, '2025-02-06', 'COM00000006', 'VEN00000006', 'CLI00000006', 'PRO00000006'),
(125.25, '2025-02-07', 'COM00000007', 'VEN00000007', 'CLI00000007', 'PRO00000007'),
(160.75, '2025-02-08', 'COM00000008', 'VEN00000008', 'CLI00000008', 'PRO00000008'),
(135.50, '2025-02-09', 'COM00000009', 'VEN00000009', 'CLI00000009', 'PRO00000009'),
(200.00, '2025-02-10', 'COM00000010', 'VEN00000010', 'CLI00000010', 'PRO00000010'),
(175.00, '2025-02-11', 'COM00000011', 'VEN00000011', 'CLI00000011', 'PRO00000011'),
(145.75, '2025-02-12', 'COM00000012', 'VEN00000012', 'CLI00000012', 'PRO00000012'),
(155.25, '2025-02-13', 'COM00000013', 'VEN00000013', 'CLI00000013', 'PRO00000013'),
(180.50, '2025-02-14', 'COM00000014', 'VEN00000014', 'CLI00000014', 'PRO00000014'),
(220.00, '2025-02-15', 'COM00000015', 'VEN00000015', 'CLI00000015', 'PRO00000015'),
(250.00, '2025-02-16', 'COM00000016', 'VEN00000016', 'CLI00000016', 'PRO00000016'),
(210.00, '2025-02-17', 'COM00000017', 'VEN00000017', 'CLI00000017', 'PRO00000017'),
(230.00, '2025-02-18', 'COM00000018', 'VEN00000018', 'CLI00000018', 'PRO00000018'),
(240.50, '2025-02-19', 'COM00000019', 'VEN00000019', 'CLI00000019', 'PRO00000019'),
(260.00, '2025-02-20', 'COM00000020', 'VEN00000020', 'CLI00000020', 'PRO00000020');

-- Insertion dans la table vente_detail
INSERT INTO vente_detail (quantite, prix_unitaire, id_vente) VALUES 
(2, 5.99, 'VNT00000001'),
(3, 8.49, 'VNT00000002'),
(1, 4.99, 'VNT00000003'),
(4, 15.00, 'VNT00000004'),
(5, 3.00, 'VNT00000005'),
(2, 6.99, 'VNT00000006'),
(3, 18.00, 'VNT00000007'),
(1, 1.50, 'VNT00000008'),
(4, 9.99, 'VNT00000009'),
(5, 12.99, 'VNT00000010'),
(3, 10.00, 'VNT00000011'),
(2, 7.50, 'VNT00000012'),
(1, 8.00, 'VNT00000013'),
(4, 14.99, 'VNT00000014'),
(5, 20.00, 'VNT00000015'),
(2, 5.00, 'VNT00000016'),
(3, 11.00, 'VNT00000017'),
(1, 4.00, 'VNT00000018'),
(4, 7.20, 'VNT00000019'),
(5, 9.80, 'VNT00000020');

-- Insertion dans la table produit_maladie
INSERT INTO produit_maladie (id_produit, id_maladie) VALUES 
('PRO00000001', 'MLD00000001'),
('PRO00000002', 'MLD00000002'),
('PRO00000003', 'MLD00000003'),
('PRO00000004', 'MLD00000004'),
('PRO00000005', 'MLD00000005'),
('PRO00000006', 'MLD00000006'),
('PRO00000007', 'MLD00000007'),
('PRO00000008', 'MLD00000008'),
('PRO00000009', 'MLD00000009'),
('PRO00000010', 'MLD00000010'),
('PRO00000011', 'MLD00000011'),
('PRO00000012', 'MLD00000012'),
('PRO00000013', 'MLD00000013'),
('PRO00000014', 'MLD00000014'),
('PRO00000015', 'MLD00000015'),
('PRO00000016', 'MLD00000016'),
('PRO00000017', 'MLD00000017'),
('PRO00000018', 'MLD00000018'),
('PRO00000019', 'MLD00000019'),
('PRO00000020', 'MLD00000020');

INSERT INTO categorie_personne (categorie_personne) VALUES 
('Enfants'),
('Adolescents'),
('Adultes'),
('Personnes âgées'),
('Femmes enceintes'),
('Sportifs'),
('Personnes diabétiques'),
('Personnes hypertendues'),
('Personnes allergiques'),
('Végétariens'),
('Végans'),
('Personnes en surpoids'),
('Personnes souffrant d''asthme'),
('Travailleurs de nuit'),
('Personnes immunodéprimées'),
('Personnes avec des maladies chroniques'),
('Étudiants'),
('Personnes ayant des troubles digestifs'),
('Personnes ayant des problèmes cardiaques'),
('Personnes en convalescence');


-- Insertion dans la table produit_categorie_personne
INSERT INTO produit_categorie_personne (id_produit, id_categorie_personne) VALUES 
('PRO00000001', 'CATP00000001'),
('PRO00000002', 'CATP00000002'),
('PRO00000003', 'CATP00000003'),
('PRO00000004', 'CATP00000004'),
('PRO00000005', 'CATP00000005'),
('PRO00000006', 'CATP00000006'),
('PRO00000007', 'CATP00000007'),
('PRO00000008', 'CATP00000008'),
('PRO00000009', 'CATP00000009'),
('PRO00000010', 'CATP00000010'),
('PRO00000011', 'CATP00000011'),
('PRO00000012', 'CATP00000012'),
('PRO00000013', 'CATP00000013'),
('PRO00000014', 'CATP00000014'),
('PRO00000015', 'CATP00000015'),
('PRO00000016', 'CATP00000016'),
('PRO00000017', 'CATP00000017'),
('PRO00000018', 'CATP00000018'),
('PRO00000019', 'CATP00000019'),
('PRO00000020', 'CATP00000020');