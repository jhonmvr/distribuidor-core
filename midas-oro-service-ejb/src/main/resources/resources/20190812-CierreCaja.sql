--12/8/2019
--CAMBIO DE LONGITUD TIPO DETALLE CAJA
--AUTOR: KEVIN CHAMORRO
ALTER TABLE remidasoro.tb_mi_detalle_caja ALTER COLUMN tipo TYPE varchar(64) USING tipo::varchar;