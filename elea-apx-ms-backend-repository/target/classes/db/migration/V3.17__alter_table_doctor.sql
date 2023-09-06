ALTER TABLE doctor ADD deleted bit NULL;
ALTER TABLE doctor ADD deletedBy varchar(100) NULL;
ALTER TABLE doctor ADD deletedDate datetime NULL;