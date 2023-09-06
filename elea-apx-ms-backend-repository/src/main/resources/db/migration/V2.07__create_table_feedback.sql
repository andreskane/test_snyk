CREATE TABLE feedback (
    id INT IDENTITY (1,1) NOT NULL,
	idApm INT,
	idPuntaje INT,
	idPantalla INT,
	comentario VARCHAR(200) NULL,
	CONSTRAINT id_feedback_PK PRIMARY KEY (id)
);