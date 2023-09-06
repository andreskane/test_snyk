ALTER TABLE region_provincia drop CONSTRAINT region_provincia_provincia_FK;
ALTER TABLE localidad drop CONSTRAINT localidad_provincia_id_fk;
ALTER TABLE codigo_postal drop CONSTRAINT codigo_postal_provincia_id_fk;

truncate table provincia; -- eliminar provincias cargadas por script ya que van a ser traidas de la base de ELEA

truncate table region_provincia;
-- insertar mapeo de regiones de las tablas de prescripciones con las provincias
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'01', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'04', 527);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'05', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'06', 543);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'07', 545);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'08', 534);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'09', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'10', 528);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'11', 525);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'12', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'13', 543);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'14', 529);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'15', 536);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'16', 535);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'17', 540);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'18', 531);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'19', 539);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'20', 527);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'21', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'22', 529);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'23', 527);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'24', 537);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'25', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'26', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'27', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'28', 530);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'29', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'30', 524);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'31', 533);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'32', 538);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'33', 541);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'36', 522);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'37', 542);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'38', 543);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'39', 527);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'40', 114193);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'45', 114193);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'46', 534);
INSERT INTO region_provincia (sfn_region, id_provincia) VALUES (N'48', 544);