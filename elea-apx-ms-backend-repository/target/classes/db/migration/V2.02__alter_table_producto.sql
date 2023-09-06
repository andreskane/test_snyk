ALTER TABLE producto ADD id_categoria int NULL,
    CONSTRAINT categoria_producto_FK FOREIGN KEY (id_categoria) REFERENCES categoria_producto (id)
GO