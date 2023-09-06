INSERT INTO material_promocional_tipo (nombre)
VALUES ('Muestra médica'),
       ('Gimmix'),
       ('Digital');
GO

ALTER TABLE especialidad ADD
    CONSTRAINT especialidad_PK PRIMARY KEY (id)
GO

ALTER TABLE material_promocional ADD
    especialidad_id int NULL,
    producto_id int NULL,
    archivo varbinary(max) NULL,
    archivo_extension varchar(8) NULL,
    created_date datetime NOT NULL,
    modified_by varchar(100) NOT NULL,
    modified_date datetime NOT NULL,
    CONSTRAINT material_promocional_especialidad_FK FOREIGN KEY (especialidad_id) REFERENCES especialidad(id),
    CONSTRAINT material_promocional_producto_FK FOREIGN KEY (producto_id) REFERENCES producto(id)
GO
