if not exists (select * from sysobjects where name='stock_productos' and xtype='U')
    CREATE TABLE stock_productos (
	id_producto int NULL,
	fecha date NULL,
	stock_virtual_disponible int NULL,
	stock_total int NULL,
	stock_presupuestado int NULL,
	dias_stock int NULL,
	stock_no_disponible int NULL,
	stock_disponible int NULL,
	otro_stock_disponible int NULL,
	created_at datetime NULL,
	step_data_lake varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	step_data_lake_date datetime NULL,
	elt_user varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
);