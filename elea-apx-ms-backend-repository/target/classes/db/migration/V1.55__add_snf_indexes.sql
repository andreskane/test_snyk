create index SFN_audit_class_cdg_mercado_index
    on SFN_audit_class (cdg_mercado)
go

create index SFN_audit_product_cdg_raiz_index
    on SFN_audit_product (cdg_raiz)
go

create index SFN_audit_laboratory_cdg_laboratorio_index
    on SFN_audit_laboratory (cdg_laboratorio)
go

create index SFN_audit_customer_prescription_cdg_periodo_index
    on SFN_audit_customer_prescription (cdg_periodo)
go
