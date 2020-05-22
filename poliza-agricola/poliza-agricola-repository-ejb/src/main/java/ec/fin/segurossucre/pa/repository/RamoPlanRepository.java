package ec.com.def.pa.repository;

import javax.ejb.Local;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.CrudRepository;
import ec.com.def.pa.model.Ramoplan;
import ec.com.def.pa.model.RamoplanPK;

@Local
public interface RamoPlanRepository  extends CrudRepository<RamoplanPK, Ramoplan>{
	
	public Ramoplan findByPKFixed( String ramoid, String ramoplanid)  throws DefException;

	public Ramoplan findByCodigo(String cultivo)throws DefException;

}
