package ec.fin.segurossucre.pa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the APOL database table.
 * 
 */
@Entity
//@NamedQuery(name="Apol.findAll", query="SELECT a FROM Apol a")
public class Apol implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long awsecuen;

	@Temporal(TemporalType.DATE)
	private Date afchproc;

	private String awagtcod;

	private BigDecimal awbasimp;

	private String awbnfaec;

	private String awbnfape;

	private String awbnfcan;

	private String awbnfdir;

	private String awbnfind;

	private String awbnfnac;

	private String awbnfnom;

	private String awbnfprv;

	private String awbnfruc;

	private BigDecimal awbnfsue;

	private String awbnftlf;

	private String awcanal;

	private String awcliactdescod;

	private String awcliaec;

	private String awcliape;

	private String awclican;

	private String awclidir;

	private String awclilugnac;

	private String awclimail;

	private String awclinac;

	@Temporal(TemporalType.DATE)
	private Date awclinacfch;

	private BigDecimal awclinatjur;

	private String awclinom;

	private String awclipep;

	private String awcliprv;

	private String awcliruc;

	private String awclisex;

	private BigDecimal awclisue;

	private String awclitel1;

	private String awclitel2;

	private BigDecimal awclitipid;

	@Temporal(TemporalType.DATE)
	private Date awcnffch;

	private String awcnfusr;

	@Temporal(TemporalType.DATE)
	private Date awctzfch;

	private String awctzusr;

	private BigDecimal awcuoini;

	private BigDecimal awderemi;

	private BigDecimal awdescuento;

	@Temporal(TemporalType.DATE)
	private Date awdltfch;

	private String awdltusr;

	private String awejecom;

	private BigDecimal awenviadoicore;

	private String awestciv;

	private String awestmig;

	private String awfactore;

	private String awfactura;

	@Temporal(TemporalType.DATE)
	private Date awfchmigra;

	private BigDecimal awfinan;

	private String awfpgind;

	private BigDecimal awguiaicorefactore;

	private BigDecimal awhayagropago;

	private BigDecimal awhayanexo;

	private BigDecimal awindmigra;

	private BigDecimal awindmigraorcl;

	private BigDecimal awitemto;

	private BigDecimal awitetas;

	private BigDecimal awiva;

	private String awobsmigra;

	@Temporal(TemporalType.DATE)
	private Date awordfch;

	private String awordusr;

	private String awprdcod;

	private BigDecimal awprimasubsidio;

	private BigDecimal awprineta;

	private BigDecimal awpritotal;

	private BigDecimal awpspvco;

	private BigDecimal awpzganx;

	private BigDecimal awpzgciu;

	private BigDecimal awpzgcmo;

	@Temporal(TemporalType.DATE)
	private Date awpzgfem;

	@Temporal(TemporalType.DATE)
	private Date awpzgfex;

	@Temporal(TemporalType.DATE)
	private Date awpzgfin;

	private BigDecimal awpzgpol;

	private String awpzgram;

	private String awreferext;

	private String awreferxml;

	private BigDecimal awsegcam;

	private BigDecimal awsegcamnr;

	private String awstatus;

	private BigDecimal awsupbco;

	private String awtextopoliza;

	private String awtplan;

	private BigDecimal awvallet;

	@Lob
	private String awveriobs;

	@Column(name="WIT_MAR")
	private BigDecimal witMar;

	private BigDecimal wivhmar;

	private String wtipoend;
	
	private BigDecimal awhectareas;
	
	private String awparroquia;

	public Apol() {
		// Do nothing because constructor.
	}

	public Long getAwsecuen() {
		return this.awsecuen;
	}

	public void setAwsecuen(Long awsecuen) {
		this.awsecuen = awsecuen;
	}

	public Date getAfchproc() {
		return this.afchproc;
	}

	public void setAfchproc(Date afchproc) {
		this.afchproc = afchproc;
	}

	public String getAwagtcod() {
		return this.awagtcod;
	}

	public void setAwagtcod(String awagtcod) {
		this.awagtcod = awagtcod;
	}

	public BigDecimal getAwbasimp() {
		return this.awbasimp;
	}

	public void setAwbasimp(BigDecimal awbasimp) {
		this.awbasimp = awbasimp;
	}

	public String getAwbnfaec() {
		return this.awbnfaec;
	}

	public void setAwbnfaec(String awbnfaec) {
		this.awbnfaec = awbnfaec;
	}

	public String getAwbnfape() {
		return this.awbnfape;
	}

	public void setAwbnfape(String awbnfape) {
		this.awbnfape = awbnfape;
	}

	public String getAwbnfcan() {
		return this.awbnfcan;
	}

	public void setAwbnfcan(String awbnfcan) {
		this.awbnfcan = awbnfcan;
	}

	public String getAwbnfdir() {
		return this.awbnfdir;
	}

	public void setAwbnfdir(String awbnfdir) {
		this.awbnfdir = awbnfdir;
	}

	public String getAwbnfind() {
		return this.awbnfind;
	}

	public void setAwbnfind(String awbnfind) {
		this.awbnfind = awbnfind;
	}

	public String getAwbnfnac() {
		return this.awbnfnac;
	}

	public void setAwbnfnac(String awbnfnac) {
		this.awbnfnac = awbnfnac;
	}

	public String getAwbnfnom() {
		return this.awbnfnom;
	}

	public void setAwbnfnom(String awbnfnom) {
		this.awbnfnom = awbnfnom;
	}

	public String getAwbnfprv() {
		return this.awbnfprv;
	}

	public void setAwbnfprv(String awbnfprv) {
		this.awbnfprv = awbnfprv;
	}

	public String getAwbnfruc() {
		return this.awbnfruc;
	}

	public void setAwbnfruc(String awbnfruc) {
		this.awbnfruc = awbnfruc;
	}

	public BigDecimal getAwbnfsue() {
		return this.awbnfsue;
	}

	public void setAwbnfsue(BigDecimal awbnfsue) {
		this.awbnfsue = awbnfsue;
	}

	public String getAwbnftlf() {
		return this.awbnftlf;
	}

	public void setAwbnftlf(String awbnftlf) {
		this.awbnftlf = awbnftlf;
	}

	public String getAwcanal() {
		return this.awcanal;
	}

	public void setAwcanal(String awcanal) {
		this.awcanal = awcanal;
	}

	public String getAwcliactdescod() {
		return this.awcliactdescod;
	}

	public void setAwcliactdescod(String awcliactdescod) {
		this.awcliactdescod = awcliactdescod;
	}

	public String getAwcliaec() {
		return this.awcliaec;
	}

	public void setAwcliaec(String awcliaec) {
		this.awcliaec = awcliaec;
	}

	public String getAwcliape() {
		return this.awcliape;
	}

	public void setAwcliape(String awcliape) {
		this.awcliape = awcliape;
	}

	public String getAwclican() {
		return this.awclican;
	}

	public void setAwclican(String awclican) {
		this.awclican = awclican;
	}

	public String getAwclidir() {
		return this.awclidir;
	}

	public void setAwclidir(String awclidir) {
		this.awclidir = awclidir;
	}

	public String getAwclilugnac() {
		return this.awclilugnac;
	}

	public void setAwclilugnac(String awclilugnac) {
		this.awclilugnac = awclilugnac;
	}

	public String getAwclimail() {
		return this.awclimail;
	}

	public void setAwclimail(String awclimail) {
		this.awclimail = awclimail;
	}

	public String getAwclinac() {
		return this.awclinac;
	}

	public void setAwclinac(String awclinac) {
		this.awclinac = awclinac;
	}

	public Date getAwclinacfch() {
		return this.awclinacfch;
	}

	public void setAwclinacfch(Date awclinacfch) {
		this.awclinacfch = awclinacfch;
	}

	public BigDecimal getAwclinatjur() {
		return this.awclinatjur;
	}

	public void setAwclinatjur(BigDecimal awclinatjur) {
		this.awclinatjur = awclinatjur;
	}

	public String getAwclinom() {
		return this.awclinom;
	}

	public void setAwclinom(String awclinom) {
		this.awclinom = awclinom;
	}

	public String getAwclipep() {
		return this.awclipep;
	}

	public void setAwclipep(String awclipep) {
		this.awclipep = awclipep;
	}

	public String getAwcliprv() {
		return this.awcliprv;
	}

	public void setAwcliprv(String awcliprv) {
		this.awcliprv = awcliprv;
	}

	public String getAwcliruc() {
		return this.awcliruc;
	}

	public void setAwcliruc(String awcliruc) {
		this.awcliruc = awcliruc;
	}

	public String getAwclisex() {
		return this.awclisex;
	}

	public void setAwclisex(String awclisex) {
		this.awclisex = awclisex;
	}

	public BigDecimal getAwclisue() {
		return this.awclisue;
	}

	public void setAwclisue(BigDecimal awclisue) {
		this.awclisue = awclisue;
	}

	public String getAwclitel1() {
		return this.awclitel1;
	}

	public void setAwclitel1(String awclitel1) {
		this.awclitel1 = awclitel1;
	}

	public String getAwclitel2() {
		return this.awclitel2;
	}

	public void setAwclitel2(String awclitel2) {
		this.awclitel2 = awclitel2;
	}

	public BigDecimal getAwclitipid() {
		return this.awclitipid;
	}

	public void setAwclitipid(BigDecimal awclitipid) {
		this.awclitipid = awclitipid;
	}

	public Date getAwcnffch() {
		return this.awcnffch;
	}

	public void setAwcnffch(Date awcnffch) {
		this.awcnffch = awcnffch;
	}

	public String getAwcnfusr() {
		return this.awcnfusr;
	}

	public void setAwcnfusr(String awcnfusr) {
		this.awcnfusr = awcnfusr;
	}

	public Date getAwctzfch() {
		return this.awctzfch;
	}

	public void setAwctzfch(Date awctzfch) {
		this.awctzfch = awctzfch;
	}

	public String getAwctzusr() {
		return this.awctzusr;
	}

	public void setAwctzusr(String awctzusr) {
		this.awctzusr = awctzusr;
	}

	public BigDecimal getAwcuoini() {
		return this.awcuoini;
	}

	public void setAwcuoini(BigDecimal awcuoini) {
		this.awcuoini = awcuoini;
	}

	public BigDecimal getAwderemi() {
		return this.awderemi;
	}

	public void setAwderemi(BigDecimal awderemi) {
		this.awderemi = awderemi;
	}

	public BigDecimal getAwdescuento() {
		return this.awdescuento;
	}

	public void setAwdescuento(BigDecimal awdescuento) {
		this.awdescuento = awdescuento;
	}

	public Date getAwdltfch() {
		return this.awdltfch;
	}

	public void setAwdltfch(Date awdltfch) {
		this.awdltfch = awdltfch;
	}

	public String getAwdltusr() {
		return this.awdltusr;
	}

	public void setAwdltusr(String awdltusr) {
		this.awdltusr = awdltusr;
	}

	public String getAwejecom() {
		return this.awejecom;
	}

	public void setAwejecom(String awejecom) {
		this.awejecom = awejecom;
	}

	public BigDecimal getAwenviadoicore() {
		return this.awenviadoicore;
	}

	public void setAwenviadoicore(BigDecimal awenviadoicore) {
		this.awenviadoicore = awenviadoicore;
	}

	public String getAwestciv() {
		return this.awestciv;
	}

	public void setAwestciv(String awestciv) {
		this.awestciv = awestciv;
	}

	public String getAwestmig() {
		return this.awestmig;
	}

	public void setAwestmig(String awestmig) {
		this.awestmig = awestmig;
	}

	public String getAwfactore() {
		return this.awfactore;
	}

	public void setAwfactore(String awfactore) {
		this.awfactore = awfactore;
	}

	public String getAwfactura() {
		return this.awfactura;
	}

	public void setAwfactura(String awfactura) {
		this.awfactura = awfactura;
	}

	public Date getAwfchmigra() {
		return this.awfchmigra;
	}

	public void setAwfchmigra(Date awfchmigra) {
		this.awfchmigra = awfchmigra;
	}

	public BigDecimal getAwfinan() {
		return this.awfinan;
	}

	public void setAwfinan(BigDecimal awfinan) {
		this.awfinan = awfinan;
	}

	public String getAwfpgind() {
		return this.awfpgind;
	}

	public void setAwfpgind(String awfpgind) {
		this.awfpgind = awfpgind;
	}

	public BigDecimal getAwguiaicorefactore() {
		return this.awguiaicorefactore;
	}

	public void setAwguiaicorefactore(BigDecimal awguiaicorefactore) {
		this.awguiaicorefactore = awguiaicorefactore;
	}

	public BigDecimal getAwhayagropago() {
		return this.awhayagropago;
	}

	public void setAwhayagropago(BigDecimal awhayagropago) {
		this.awhayagropago = awhayagropago;
	}

	public BigDecimal getAwhayanexo() {
		return this.awhayanexo;
	}

	public void setAwhayanexo(BigDecimal awhayanexo) {
		this.awhayanexo = awhayanexo;
	}

	public BigDecimal getAwindmigra() {
		return this.awindmigra;
	}

	public void setAwindmigra(BigDecimal awindmigra) {
		this.awindmigra = awindmigra;
	}

	public BigDecimal getAwindmigraorcl() {
		return this.awindmigraorcl;
	}

	public void setAwindmigraorcl(BigDecimal awindmigraorcl) {
		this.awindmigraorcl = awindmigraorcl;
	}

	public BigDecimal getAwitemto() {
		return this.awitemto;
	}

	public void setAwitemto(BigDecimal awitemto) {
		this.awitemto = awitemto;
	}

	public BigDecimal getAwitetas() {
		return this.awitetas;
	}

	public void setAwitetas(BigDecimal awitetas) {
		this.awitetas = awitetas;
	}

	public BigDecimal getAwiva() {
		return this.awiva;
	}

	public void setAwiva(BigDecimal awiva) {
		this.awiva = awiva;
	}

	public String getAwobsmigra() {
		return this.awobsmigra;
	}

	public void setAwobsmigra(String awobsmigra) {
		this.awobsmigra = awobsmigra;
	}

	public Date getAwordfch() {
		return this.awordfch;
	}

	public void setAwordfch(Date awordfch) {
		this.awordfch = awordfch;
	}

	public String getAwordusr() {
		return this.awordusr;
	}

	public void setAwordusr(String awordusr) {
		this.awordusr = awordusr;
	}

	public String getAwprdcod() {
		return this.awprdcod;
	}

	public void setAwprdcod(String awprdcod) {
		this.awprdcod = awprdcod;
	}

	public BigDecimal getAwprimasubsidio() {
		return this.awprimasubsidio;
	}

	public void setAwprimasubsidio(BigDecimal awprimasubsidio) {
		this.awprimasubsidio = awprimasubsidio;
	}

	public BigDecimal getAwprineta() {
		return this.awprineta;
	}

	public void setAwprineta(BigDecimal awprineta) {
		this.awprineta = awprineta;
	}

	public BigDecimal getAwpritotal() {
		return this.awpritotal;
	}

	public void setAwpritotal(BigDecimal awpritotal) {
		this.awpritotal = awpritotal;
	}

	public BigDecimal getAwpspvco() {
		return this.awpspvco;
	}

	public void setAwpspvco(BigDecimal awpspvco) {
		this.awpspvco = awpspvco;
	}

	public BigDecimal getAwpzganx() {
		return this.awpzganx;
	}

	public void setAwpzganx(BigDecimal awpzganx) {
		this.awpzganx = awpzganx;
	}

	public BigDecimal getAwpzgciu() {
		return this.awpzgciu;
	}

	public void setAwpzgciu(BigDecimal awpzgciu) {
		this.awpzgciu = awpzgciu;
	}

	public BigDecimal getAwpzgcmo() {
		return this.awpzgcmo;
	}

	public void setAwpzgcmo(BigDecimal awpzgcmo) {
		this.awpzgcmo = awpzgcmo;
	}

	public Date getAwpzgfem() {
		return this.awpzgfem;
	}

	public void setAwpzgfem(Date awpzgfem) {
		this.awpzgfem = awpzgfem;
	}

	public Date getAwpzgfex() {
		return this.awpzgfex;
	}

	public void setAwpzgfex(Date awpzgfex) {
		this.awpzgfex = awpzgfex;
	}

	public Date getAwpzgfin() {
		return this.awpzgfin;
	}

	public void setAwpzgfin(Date awpzgfin) {
		this.awpzgfin = awpzgfin;
	}

	public BigDecimal getAwpzgpol() {
		return this.awpzgpol;
	}

	public void setAwpzgpol(BigDecimal awpzgpol) {
		this.awpzgpol = awpzgpol;
	}

	public String getAwpzgram() {
		return this.awpzgram;
	}

	public void setAwpzgram(String awpzgram) {
		this.awpzgram = awpzgram;
	}

	public String getAwreferext() {
		return this.awreferext;
	}

	public void setAwreferext(String awreferext) {
		this.awreferext = awreferext;
	}

	public String getAwreferxml() {
		return this.awreferxml;
	}

	public void setAwreferxml(String awreferxml) {
		this.awreferxml = awreferxml;
	}

	public BigDecimal getAwsegcam() {
		return this.awsegcam;
	}

	public void setAwsegcam(BigDecimal awsegcam) {
		this.awsegcam = awsegcam;
	}

	public BigDecimal getAwsegcamnr() {
		return this.awsegcamnr;
	}

	public void setAwsegcamnr(BigDecimal awsegcamnr) {
		this.awsegcamnr = awsegcamnr;
	}

	public String getAwstatus() {
		return this.awstatus;
	}

	public void setAwstatus(String awstatus) {
		this.awstatus = awstatus;
	}

	public BigDecimal getAwsupbco() {
		return this.awsupbco;
	}

	public void setAwsupbco(BigDecimal awsupbco) {
		this.awsupbco = awsupbco;
	}

	public String getAwtextopoliza() {
		return this.awtextopoliza;
	}

	public void setAwtextopoliza(String awtextopoliza) {
		this.awtextopoliza = awtextopoliza;
	}

	public String getAwtplan() {
		return this.awtplan;
	}

	public void setAwtplan(String awtplan) {
		this.awtplan = awtplan;
	}

	public BigDecimal getAwvallet() {
		return this.awvallet;
	}

	public void setAwvallet(BigDecimal awvallet) {
		this.awvallet = awvallet;
	}

	public String getAwveriobs() {
		return this.awveriobs;
	}

	public void setAwveriobs(String awveriobs) {
		this.awveriobs = awveriobs;
	}

	public BigDecimal getWitMar() {
		return this.witMar;
	}

	public void setWitMar(BigDecimal witMar) {
		this.witMar = witMar;
	}

	public BigDecimal getWivhmar() {
		return this.wivhmar;
	}

	public void setWivhmar(BigDecimal wivhmar) {
		this.wivhmar = wivhmar;
	}

	public String getWtipoend() {
		return this.wtipoend;
	}

	public void setWtipoend(String wtipoend) {
		this.wtipoend = wtipoend;
	}

	public BigDecimal getAwhectareas() {
		return awhectareas;
	}

	public void setAwhectareas(BigDecimal awhectareas) {
		this.awhectareas = awhectareas;
	}

	public String getAwparroquia() {
		return awparroquia;
	}

	public void setAwparroquia(String awparroquia) {
		this.awparroquia = awparroquia;
	}
	
	

}