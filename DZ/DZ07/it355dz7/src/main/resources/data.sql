DROP table IF EXISTS jelo;
DROP table IF EXISTS restoran;

CREATE TABLE restoran
(
    id      INT          NOT NULL AUTO_INCREMENT,
    naziv   VARCHAR(255) NOT NULL,
    adresa  VARCHAR(255) NOT NULL,
    telefon VARCHAR(20)  NOT NULL,
    ocena   double DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE jelo
(
    id          INT          NOT NULL AUTO_INCREMENT,
    naziv       VARCHAR(255) NOT NULL,
    opis        VARCHAR(255),
    cena        double       NOT NULL,
    restoran_id INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (restoran_id) REFERENCES restoran (id)
);

INSERT INTO restoran (naziv, adresa, telefon, ocena)
VALUES ('Restoran1', 'Adresa1', '111-111', 4.5);

INSERT INTO restoran (naziv, adresa, telefon, ocena)
VALUES ('Restoran2', 'Adresa2', '222-222', 3.2);

INSERT INTO jelo (naziv, opis, cena, restoran_id)
VALUES ('Jelo1', 'Opis jela 1', 10.5, 1);

INSERT INTO jelo (naziv, opis, cena, restoran_id)
VALUES ('Jelo2', 'Opis jela 2', 15.0, 1);

INSERT INTO jelo (naziv, opis, cena, restoran_id)
VALUES ('Jelo3', 'Opis jela 3', 8.75, 2);

INSERT INTO jelo (naziv, opis, cena, restoran_id)
VALUES ('Jelo4', 'Opis jela 4', 12.5, 2);