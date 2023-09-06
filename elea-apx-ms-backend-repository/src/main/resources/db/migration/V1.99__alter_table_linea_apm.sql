CREATE TABLE linea_apm (
      id int IDENTITY(1,1) NOT NULL,
      id_apm int NOT NULL,
      id_linea int NOT NULL,
      CONSTRAINT apm_linea_PK PRIMARY KEY (id),
      CONSTRAINT apm_FK FOREIGN KEY(id_apm) REFERENCES apm(id),
      CONSTRAINT linea_FK FOREIGN KEY(id_linea) REFERENCES linea(id)
      )
GO