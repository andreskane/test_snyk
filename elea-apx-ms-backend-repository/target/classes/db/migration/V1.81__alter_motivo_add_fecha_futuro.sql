ALTER TABLE motivo ADD fecha_futuro bit default 0
GO

UPDATE motivo SET fecha_futuro = 0 WHERE fecha_futuro IS NULL
GO