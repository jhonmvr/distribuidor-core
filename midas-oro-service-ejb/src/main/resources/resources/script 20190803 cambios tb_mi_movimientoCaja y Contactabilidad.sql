alter table tb_mi_movimiento_caja
add column tipo_tarjeta varchar(50);

alter table tb_mi_movimiento_caja
add column numero_tarjeta varchar(50);

alter table tb_mi_movimiento_caja
add column habiente_tarjeta varchar(50);

alter table tb_mi_movimiento_caja
add column ced_habiente_tarjeta varchar(50);


alter table tb_mi_movimiento_caja
ALTER COLUMN tipo_tarjeta DROP NOT NULL;

alter table tb_mi_movimiento_caja
ALTER COLUMN numero_tarjeta DROP NOT NULL;

alter table tb_mi_movimiento_caja
ALTER COLUMN habiente_tarjeta DROP NOT NULL;

alter table tb_mi_movimiento_caja
ALTER COLUMN ced_habiente_tarjeta DROP NOT NULL;


ALTER TABLE tb_mi_contactabilidad
ADD COLUMN estado_Gestion varchar(50);

