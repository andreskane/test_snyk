exec sp_rename 'TGT_prescricao.DGMAR', CDGMAR, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGPRO', CDGPRO, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGMED', CDGMED, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGMED_REG', CDGMED_REG, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGESP1', CDGESP1, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGREG', CDGREG, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGLAB', CDGLAB, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.DGCLA4', CDGCLA4, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.X1', PX1, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.X2', PX2, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.ATA', DATA, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.IPO_DOM', TIPO_DOM, 'COLUMN'
go
exec sp_rename 'TGT_prescricao.xPPA', PxPPA, 'COLUMN'
go


create index TGT_prescricao_DATA_index
    on TGT_prescricao (DATA)
go

create index TGT_prescricao_CDGMED_REG_index
    on TGT_prescricao (CDGMED_REG)
go