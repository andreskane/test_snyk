create table grilla_promocional
(
    id                int identity (0, 1) constraint grilla_promocional_PK primary key,
    archivo           varbinary(max),
    archivo_nombre    varchar(100) not null,
    content_type      varchar(100) not null,
    created_date      datetime     not null,
    modified_by       varchar(100) not null,
    modified_date     datetime     not null
)
go


