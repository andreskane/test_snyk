ALTER TABLE cartera_medica ADD datos_visita_id int NULL GO
ALTER TABLE cartera_medica ADD approved bit NULL GO
ALTER TABLE cartera_medica ADD created_date datetime NOT NULL GO
ALTER TABLE cartera_medica ADD modified_by varchar(100) NOT NULL GO
ALTER TABLE cartera_medica ADD modified_date datetime NOT NULL GO
