CREATE TABLE ciclo
(
    id     int IDENTITY(1,1) NOT NULL,
    inicio datetime     NOT NULL,
    fin    datetime     NOT NULL,
    nombre varchar(100) NOT NULL,
    CONSTRAINT ciclo_PK PRIMARY KEY (id)
) GO