--13/8/2019
--SE AGREGA TIPO AGENCIA A TBMIAGENCIA
--AUTOR: KEVIN CHAMORRO
ALTER TABLE tb_mi_agencia ADD COLUMN tipo_agencia VARCHAR(32);
COMMENT ON COLUMN tb_mi_agencia.tipo_agencia IS 'Identifica el tipo de agencia';