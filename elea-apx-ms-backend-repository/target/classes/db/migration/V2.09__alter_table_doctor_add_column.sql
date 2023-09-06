ALTER TABLE doctor ADD sociedad_cientifica_id int NULL;
ALTER TABLE doctor ADD CONSTRAINT doctor_sociedad_FK FOREIGN KEY (sociedad_cientifica_id) REFERENCES sociedad_cientifica(id);