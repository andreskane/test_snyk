ALTER TABLE apm ADD id_linea int NULL,
CONSTRAINT linea_apm_FK FOREIGN KEY (id_linea) REFERENCES linea (id)
GO