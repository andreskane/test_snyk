DROP TABLE feedback;

CREATE TABLE feedback (
    id INT IDENTITY (1,1) NOT NULL,
	idApm INT,
	puntaje VARCHAR(300) NULL,
	pantalla VARCHAR(300) NULL,
	comentario VARCHAR(2000) NULL,
	fecha datetime NULL,
	CONSTRAINT id_feedback_PK PRIMARY KEY (id)
);
go
