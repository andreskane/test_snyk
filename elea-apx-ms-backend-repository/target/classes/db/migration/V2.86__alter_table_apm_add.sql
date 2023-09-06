ALTER TABLE apm ADD deleted bit NULL;
ALTER TABLE apm ADD deletedBy varchar(100) NULL;
ALTER TABLE apm ADD deletedDate datetime NULL;