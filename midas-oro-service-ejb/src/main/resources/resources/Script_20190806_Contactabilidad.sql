--2/8/2019
--TABLA DE DATOS DE CONTACTABILIDAD DEL CLIENTE
--AUTOR: KEVIN CHAMORRO
CREATE TABLE tb_mi_contactabilidad (
   id numeric(10) PRIMARY KEY,
   id_cliente numeric(10) NOT NULL,
   id_agente numeric(10) NOT NULL,
   id_agencia numeric(10) NOT NULL,
   id_contrato numeric(10) NOT NULL,
   comentario varchar(256) NOT NULL,
   estado varchar(32) NOT NULL,
   fecha_creacion date NOT NULL,
   fecha_actualizacion date,
   usuario_creacion varchar(32) NOT NULL,
   usuario_actualizacion varchar(32)
);

ALTER TABLE tb_mi_contactabilidad
    ADD CONSTRAINT fk_con_cliente FOREIGN KEY (id_cliente) REFERENCES tb_mi_cliente (id);
ALTER TABLE tb_mi_contactabilidad
    ADD CONSTRAINT fk_con_agente FOREIGN KEY (id_agente) REFERENCES tb_mi_agente (id);
ALTER TABLE tb_mi_contactabilidad
    ADD CONSTRAINT fk_con_agencia FOREIGN KEY (id_agencia) REFERENCES tb_mi_agencia (id);
ALTER TABLE tb_mi_contactabilidad
    ADD CONSTRAINT fk_con_contrato FOREIGN KEY (id_contrato) REFERENCES tb_mi_contrato (id);
   
COMMENT ON COLUMN tb_mi_contactabilidad.id IS 'PK';
COMMENT ON COLUMN tb_mi_contactabilidad.id_cliente IS 'FK con tb_mi_cliente';
COMMENT ON COLUMN tb_mi_contactabilidad.id_agente IS 'FK con tb_mi_agente';
COMMENT ON COLUMN tb_mi_contactabilidad.id_agencia IS 'FK con tb_mi_agencia';
COMMENT ON COLUMN tb_mi_contactabilidad.id_contrato IS 'FK con tb_mi_contrato';
COMMENT ON COLUMN tb_mi_contactabilidad.comentario IS 'Comentario ingresado por el agente, indicando el estado del contacto con el cliente';
COMMENT ON COLUMN tb_mi_contactabilidad.estado IS 'Estado del registro';
COMMENT ON COLUMN tb_mi_contactabilidad.fecha_creacion IS 'Fecha de creacion del registro';
COMMENT ON COLUMN tb_mi_contactabilidad.fecha_actualizacion IS 'Fecha de actualizacion del registro';
COMMENT ON COLUMN tb_mi_contactabilidad.usuario_creacion IS 'Usario que creo el registro';
COMMENT ON COLUMN tb_mi_contactabilidad.usuario_actualizacion IS 'Usario que actualizo el registro';

CREATE SEQUENCE seq_contactabilidad INCREMENT BY 1 START 1;