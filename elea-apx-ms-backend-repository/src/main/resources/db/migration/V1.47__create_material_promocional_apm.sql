CREATE TABLE material_promocional_apm (
      id int IDENTITY(1,1) NOT NULL,
      apm_id int NOT NULL,
      material_promocional_id int NOT NULL,
      tiene_material bit NULL,
      CONSTRAINT material_promocional_apm_PK PRIMARY KEY (id),
      CONSTRAINT mpa_apm_FK FOREIGN KEY(apm_id) REFERENCES apm(id),
      CONSTRAINT mpa_material_promocional_FK FOREIGN KEY(material_promocional_id) REFERENCES material_promocional(id)
      )
GO

update material_promocional_tipo set nombre = 'Gimmick' where nombre = 'Gimmix'
GO