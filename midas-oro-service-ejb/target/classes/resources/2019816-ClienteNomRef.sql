--16/18/2019
--Cambio longitu de cliente
--Autor Kevin Chamorro
ALTER TABLE tb_mi_cliente ALTER COLUMN nombre_referencia TYPE varchar(32) USING nombre_referencia::varchar;