create table TGT_medico
(
    CDGREG	varchar(25),
    CDGREG_PMIX	varchar(25),
    CRM	varchar(25),
    NOME	varchar(100),
    CDGMED	varchar(25),
    CDGMED_REG	varchar(25),
    LOCAL	varchar(255),
    BAIRRO	varchar(100),
    ZONA	varchar(25),
    CEP	varchar(25),
    CDGESP1	varchar(25),
    CDGESP2	varchar(25),
    TIPO_DOM	varchar(25)
)
go


create table TGT_medico_representante
(
    CDGMED	varchar(25),
    CDGMED_REG	varchar(25),
    CDGR	varchar(25),
    CDGD	varchar(25),
    CDGREP	varchar(25),
    CDGREP_NOM	varchar(25),
    LOCAL	varchar(25),
    BAIRRO	varchar(25),
    CEP	varchar(25),
    CDGESP1	varchar(25),
    CDGESP2	varchar(25)
)
go


create table TGT_medico_visitado
(
    CDGMED	varchar(25),
    CDGMED_REG	varchar(25),
    CDGMED_VIS	varchar(25)
)
go


create table TGT_mercados
(
    CDG_USUARIO	varchar(25),
    CDG_PAIS	varchar(25),
    CDG_MERCADO	varchar(25),
    DESCRIPCION	varchar(100),
    MCA_Closeup	varchar(25),
    MCA_audit	varchar(25),
    MCA_feedack	varchar(25),
    MCA_recetario	varchar(25),
    MCA_generado	varchar(25),
    MCA_controlado	varchar(25),
    Filler1	varchar(25),
    Filler2	varchar(25),
    Filler3	varchar(25),
    CDG_LABORA	varchar(25),
    EDICION	varchar(25),
    FECHA_HORA_PROC	varchar(25),
    ABREVIATURA	varchar(50)
)
go


create table TGT_mercados_productos
(
    CDG_PAIS	varchar(25),
    CDG_USUARIO	varchar(25),
    CDG_MERCADO	varchar(25),
    CDG_PROD	varchar(25),
    CDG_LABORA	varchar(25),
    EDICION	varchar(25),
    FECHA_HORA_PROC	varchar(25)
)
go


create table TGT_mercados_modulos_lineas
(
    CDG_USUARIO	varchar(25),
    CDG_PAIS	varchar(25),
    CDG_MERCADO	varchar(25),
    DESCRIPCION	varchar(100),
    ABREVIATURA	varchar(50),
    CDG_LABORA	varchar(25),
    EDICION	varchar(25),
    FECHA_HORA_PROC	varchar(25),
    MARKET	varchar(25),
    TARGET	varchar(25),
    SFA	varchar(25),
    LINEA	varchar(25)
)
go


create table TGT_prescricao
(
    DGMAR	varchar(25),
    DGPRO	varchar(25),
    DGMED	varchar(25),
    DGMED_REG	varchar(25),
    DGESP1	varchar(25),
    DGREG	varchar(25),
    DGLAB	varchar(25),
    DGCLA4	varchar(25),
    X1	varchar(25),
    X2	varchar(25),
    ATA	varchar(25),
    IPO_DOM	varchar(25),
    xPPA	varchar(25)
)
go


create table TGT_processo
(
    TIPO	varchar(25),
    VALOR	varchar(25)
)
go