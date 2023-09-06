CREATE TABLE tag
(
    id int IDENTITY (1,1) NOT NULL,
    nombre varchar(100)       NULL,
    tipo varchar(100)       NULL
)GO

CREATE TABLE tag_doctor
(
    tag_id int NOT NULL,
    doctor_id int NOT NULL
)GO