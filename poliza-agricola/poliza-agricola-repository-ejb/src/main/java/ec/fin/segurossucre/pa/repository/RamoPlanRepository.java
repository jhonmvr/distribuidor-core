package ec.fin.segurossucre.pa.repository;

import javax.ejb.Local;

import ec.fin.segurossucre.core.exception.SegSucreException;
import ec.fin.segurossucre.core.persistence.CrudRepository;
import ec.fin.segurossucre.pa.model.Ramoplan;
import ec.fin.segurossucre.pa.model.RamoplanPK;

@Local
public interface RamoPlanRepository  extends CrudRepository<RamoplanPK, Ramoplan>{
	
	public Ramoplan findByPKFixed( String ramoid, String ramoplanid)  throws SegSucreException;

	public Ramoplan findByCodigo(String cultivo)throws SegSucreException;

}
