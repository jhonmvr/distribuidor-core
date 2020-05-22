package ec.com.def.pa.model;

import java.io.Serializable;
import javax.persistence.*;

import ec.com.def.pa.enums.EstadoSiniestroAgricolaEnum;


/**
 * The persistent class for the TB_SA_PARAMETRO database table.
 * 
 */
@Entity
@Table(name="TB_SA_PARAMETRO")
@NamedQuery(name="TbSaParametro.findAll", query="SELECT t FROM TbSaParametro t")
public class TbSaParametro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	
	@SequenceGenerator(name="TB_SA_PARAMETRO_ID_GENERATOR", sequenceName="SEQ_PARAMETRO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SA_PARAMETRO_ID_GENERATOR")

	private Long id;

	@Column(name="CARACTERISTICA_DOS")
	private String caracteristicaDos;

	@Column(name="CARACTERITICA_UNO")
	private String caracteriticaUno;

	@Enumerated(EnumType.STRING)
	private EstadoSiniestroAgricolaEnum estado;


	private String nombre;

	private String tipo;

	private String valor;

	public TbSaParametro() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaracteristicaDos() {
		return this.caracteristicaDos;
	}

	public void setCaracteristicaDos(String caracteristicaDos) {
		this.caracteristicaDos = caracteristicaDos;
	}

	public String getCaracteriticaUno() {
		return this.caracteriticaUno;
	}

	public void setCaracteriticaUno(String caracteriticaUno) {
		this.caracteriticaUno = caracteriticaUno;
	}

	public EstadoSiniestroAgricolaEnum getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoSiniestroAgricolaEnum estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}