ALTER TABLE tb_mi_joya
ADD COLUMN iva numeric;

alter table tb_mi_joya
add column comprador numeric;

ALTER TABLE tb_mi_joya
ADD FOREIGN KEY (comprador) 
REFERENCES tb_mi_cliente(id)


ALTER TABLE tb_mi_agencia
ADD COLUMN seq_ie varchar(10);


ALTER TABLE tb_mi_venta_lote
ADD COLUMN valor numeric;