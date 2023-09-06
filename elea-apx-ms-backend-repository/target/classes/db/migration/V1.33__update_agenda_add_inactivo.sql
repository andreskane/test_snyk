ALTER TABLE agenda ADD inactivo bit NULL DEFAULT 0 GO
UPDATE agenda SET inactivo = 0 WHERE inactivo IS NULL GO