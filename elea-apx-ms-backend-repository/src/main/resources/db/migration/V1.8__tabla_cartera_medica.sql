create table cartera_medica(
                               id int IDENTITY(1,1) NOT NULL,
                               apm_id int NOT NULL,
                               doctor_id int NOT NULL,
                               CONSTRAINT cartera_medica_PK PRIMARY KEY (id),
                               CONSTRAINT cartera_apm_FK FOREIGN KEY (apm_id) REFERENCES apm(id),
                               CONSTRAINT cartera_doctor_FK FOREIGN KEY (doctor_id) REFERENCES doctor(id)
)
GO