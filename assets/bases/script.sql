-- Active: 1735375537127@@127.0.0.1@5432@pharmacie@public
-- fonctionnalite 1

SELECT *
from
    produit p
    JOIN produit_categorie_personne pcp on pcp.id_produit = p.id_produit
    JOIN produit_maladie pm on pm.id_produit = p.id_produit
WHERE
    pm.id_maladie = ?
    AND pcp.id_categorie_personne = ?;

-- fonctionnalite 2

SELECT (
        m.id_mouvement, m.quantite, m.prix_achat_unitaire, m.prix_vente_unitaire, m.date_mouvement, p.nom_produit
    )
from
    produit p
    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit
    JOIN mouvement m ON m.id_produit = p.id_produit
WHERE
    pcp.id_categorie_personne = ?
    and id_mode_administration = ?;

insert into
    produit (
        id_produit,
        nom_produit,
        prix_vente_unitaire,
        nombre,
        prix_achat_unitaire,
        quantite,
        description,
        id_forme,
        id_mode_administration,
        id_laboratoire
    )
values (
        'PRD000000011',
        'rr',
        3,
        5,
        6,
        67,
        'ffff',
        'FRM00000004',
        'MOD00000003',
        'LAB00000005'
    );

SELECT * FROM mode_administration;

SELECT m.id_mouvement, m.quantite, m.prix_achat_unitaire, m.prix_vente_unitaire, m.date_mouvement, p.nom_produit
from
    produit p
    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit
    JOIN mouvement m ON m.id_produit = p.id_produit;

SELECT * FROM produit_categorie_personne;

SELECT * from mouvement;

SELECT * FROM produit_categorie_personne;

-- fn 3

SELECT *
FROM
    conseil_du_mois cdm
    JOIN produit p ON p.id_produit = cdm.id_produit
WHERE (
        EXTRACT(
            MONTH
            FROM cdm.date_debut
        ) >= 8
        and EXTRACT(
            MONTH
            FROM cdm.date_fin
        ) <= 8
    )
    and (
        EXTRACT(
            YEAR
            FROM cdm.date_debut
        ) >= 2025
        and EXTRACT(
            YEAR
            FROM cdm.date_fin
        )
        <= 2025
    );