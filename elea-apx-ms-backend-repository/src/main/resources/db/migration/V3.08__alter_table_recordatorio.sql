ALTER TABLE recordatorio DROP COLUMN fecha_completado GO
ALTER TABLE recordatorio ADD frecuencia varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL GO
ALTER TABLE recordatorio ADD fecha_recordatorio date NULL  GO
ALTER TABLE recordatorio ADD hora_recordatorio varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL GO