ALTER TABLE provincia ADD
    CONSTRAINT provincia_PK PRIMARY KEY (id)
GO


CREATE TABLE region_provincia
(
    id           int IDENTITY (1,1) NOT NULL,
    sfn_region   varchar(50),
    id_provincia int,
    CONSTRAINT region_provincia_PK PRIMARY KEY (id),
    CONSTRAINT region_provincia_provincia_FK FOREIGN KEY (id_provincia) REFERENCES provincia (id)
)
GO

INSERT INTO region_provincia (sfn_region, id_provincia) VALUES
('01',1),
('04',5),
('05',1),
('06',20),
('07',23),
('08',12),
('09',1),
('10',6),
('11',3),
('12',1),
('13',20),
('14',7),
('15',14),
('16',13),
('17',17),
('18',9),
('19',16),
('20',5),
('21',1),
('22',7),
('23',5),
('24',15),
('25',1),
('26',1),
('27',1),
('28',8),
('29',1),
('30',2),
('31',11),
('32',21),
('33',18),
('36',1),
('37',19),
('38',20),
('39',5),
('40',4),
('45',4),
('46',12),
('48',22)

