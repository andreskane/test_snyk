ALTER TABLE recordatorio ADD inactivo bit NULL DEFAULT 0 GO
UPDATE recordatorio SET inactivo = 0 WHERE inactivo IS NULL GO

ALTER TABLE recordatorio ADD created_date datetime NULL GO
ALTER TABLE recordatorio ADD modified_by varchar(100) NULL GO
ALTER TABLE recordatorio ADD modified_date datetime NULL GO