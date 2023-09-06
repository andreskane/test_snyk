ALTER TABLE recordatorio_item DROP CONSTRAINT
recordatorio_item_FK;
GO

ALTER TABLE recordatorio_item ADD
    CONSTRAINT recordatorio_item_FK FOREIGN KEY (id_tipo) REFERENCES recordatorio_item_tipo(id);
GO

ALTER TABLE recordatorio ADD
    id_apm INT NULL ,
    CONSTRAINT recordatorio_apm_FK FOREIGN KEY (id_apm) REFERENCES apm(id);
GO