--6/8/2019
--CAMBIO DE LONGITUD ESTADO PARA JOYA E HISTORICO JOYA
--AUTOR: KEVIN CHAMORRO

ALTER TABLE tb_mi_joya ALTER COLUMN estado TYPE varchar(32) USING estado::varchar;
ALTER TABLE tb_mi_historico_joya ALTER COLUMN estado TYPE varchar(32) USING estado::varchar;