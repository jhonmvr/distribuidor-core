UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='ContratoCompraPlazo.jasper'
WHERE id=1;


UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='ContratoCD.jasper'
WHERE id=4;


DELETE FROM remidasoro.tb_mi_tipo_documento
WHERE id=5;


DELETE FROM remidasoro.tb_mi_tipo_documento
WHERE id=3;


UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='devolucionCP.jasper'
WHERE id=14;
UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='AdendaCP.jasper'
WHERE id=17;

UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='LiquidacionCompraBienesUsados.jasper'
WHERE id=11;
UPDATE remidasoro.tb_mi_tipo_documento
SET plantilla='Perfeccionamiento.jasper'
WHERE id=13;
INSERT INTO remidasoro.tb_mi_tipo_documento (id,tipo_documento,descripcion,estado,plantilla,tipo_plantilla)
VALUES (18,'CONTRATO_CANCELAR','Liquidacion Compra Directa','ACT','LiquidacionCD.jasper','LiqCD');
UPDATE remidasoro.tb_mi_tipo_documento
SET tipo_plantilla='CP'
WHERE id=1;
UPDATE remidasoro.tb_mi_tipo_documento
SET tipo_plantilla='CD'
WHERE id=4;
UPDATE remidasoro.tb_mi_tipo_documento
SET tipo_plantilla='LiqCB'
WHERE id=11;
UPDATE remidasoro.tb_mi_tipo_documento
SET tipo_plantilla='PerCP'