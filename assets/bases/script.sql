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

SELECT DISTINCT
    p.id_produit,
    p.nom_produit,
    p.description,
    mda.mode_administration,
    p.id_produit
from
    produit p
    JOIN produit_categorie_personne pcp on pcp.id_produit = p.id_produit
    JOIN produit_maladie pm on pm.id_produit = p.id_produit
    JOIN mode_administration mda ON mda.id_mode_administration = p.id_mode_administration
WHERE
    pm.id_maladie = ?
    AND pcp.id_categorie_personne = ?;

-- fonctionnalite 2

SELECT v.id_vente, v.prix_total, v.date_vente, c.commission, p.nom_produit, cli.nom_client, vnd.nom_vendeur, v.id_vendeur
FROM
    vente v
    JOIN produit p ON p.id_produit = v.id_produit
    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit
    JOIN commission c ON c.id_commission = v.id_commission
    JOIN client cli ON cli.id_client = v.id_client
    JOIN vendeur vnd ON vnd.id_vendeur = v.id_vendeur
WHERE 1 = 1
    and pcp.id_categorie_personne = ?
    and p.id_mode_administration = ?

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
        ) <= 2025
    );

SELECT * FROM produit_categorie_personne;

SELECT * FROM produit_maladie;

-----------------------

SELECT distinct
    m.id_mouvement,
    m.quantite,
    m.prix_achat_unitaire,
    m.prix_vente_unitaire,
    m.date_mouvement,
    p.id_produit
from
    produit p
    JOIN produit_categorie_personne pcp ON pcp.id_produit = p.id_produit
    JOIN mouvement m ON m.id_produit = p.id_produit
where
    pcp.id_categorie_personne = 'CATP00000001';

SELECT * FROM produit_categorie_personne;

-------------------

-- fn 4

SELECT DISTINCT
    c.*
from mouvement m
    JOIN client c ON c.id_client = m.id_client
WHERE
    m.date_mouvement = ?;

SELECT * from mouvement;

select mouvement.id_mouvement, mouvement.quantite, mouvement.date_mouvement, produit.prix_vente_unitaire, produit.nom_produit, client.nom_client
from
    mouvement
    join produit on mouvement.prix_vente_unitaire = produit.prix_vente_unitaire
    join client on mouvement.id_client = client.id_client;

--- fn 5

SELECT v.nom_vendeur, sum(
        (
            5 * m.prix_vente_unitaire * m.quantite
        ) / 100
    ) commission
FROM mouvement m
    JOIN vendeur v on v.id_vendeur = m.id_vendeur
WHERE
    date_mouvement BETWEEN '2025-01-21' and '2025-01-21'
GROUP BY
    v.id_vendeur;

SELECT *
FROM mouvement m
    JOIN vendeur v on v.id_vendeur = m.id_vendeur;

SELECT cv.nom_vendeur, sum(
        (
            5 * cv.prix_vente_unitaire * cv.quantite
        ) / 100
    )
FROM (
        SELECT *
        FROM mouvement m
            JOIN vendeur v on v.id_vendeur = m.id_vendeur
    ) cv
GROUP BY
    cv.id_vendeur;

-- insertion mouvement vente

INSERT INTO
    mouvement (
        quantite,
        date_mouvement,
        id_produit,
        id_client,
        id_vendeur,
        prix_vente_unitaire
    )
VALUES (?, ?, ?, ?, ?, ?);

SELECT * FROM vendeur;

---------------

SELECT *
FROM historique_prix_produit hpp
WHERE
    1 = 1
    AND id_produit = ?
    AND date_update > ?
    AND date_update < ?;

SELECT * FROM maladie;

SELECT * FROM produit_categorie_personne;