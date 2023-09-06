create table evento (
    id           int  NOT NULL,
    titulo varchar(255) NULL,
    descripcion varchar(255) NULL,
    lugar varchar(255) NULL,
    imagen varchar(255) NULL,
    fecha datetime NULL,
    CONSTRAINT evento_PK PRIMARY KEY (id)
)
GO
