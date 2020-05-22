package ec.fin.segurossucre.pa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the TB_SA_USUARIO_CANAL database table.
 * 
 */
@Entity
@Table(name="TB_SA_USUARIO_CANAL")
//@NamedQuery(name="TbSaUsuarioCanal.findAll", query="SELECT t FROM TbSaUsuarioCanal t")
public class TbSaUsuarioCanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SA_USUARIO_CANAL_ID_GENERATOR", sequenceName="SEQ_USUARIO_CANAL",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SA_USUARIO_CANAL_ID_GENERATOR")
	private Long id;

	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_RAMO", referencedColumnName="RAMOID"),
		@JoinColumn(name="ID_CANAL", referencedColumnName="CANALID")
		})
	private Ramocanal ramocanal;

	public TbSaUsuarioCanal() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Ramocanal getRamocanal() {
		return ramocanal;
	}

	public void setRamocanal(Ramocanal ramocanal) {
		this.ramocanal = ramocanal;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}