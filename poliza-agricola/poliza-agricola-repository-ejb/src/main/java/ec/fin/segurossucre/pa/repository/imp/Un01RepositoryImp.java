package ec.com.def.pa.repository.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.def.core.exception.DefException;
import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.core.util.main.Constantes;
import ec.com.def.pa.model.Un01;
import ec.com.def.pa.repository.Un01Repository;
import ec.com.def.pa.repository.spec.Un01ByIdentificacionSpec;
import ec.com.def.pa.repository.spec.Un01ByNumeroTramiteSpec;
import ec.com.def.pa.util.SiniestroAgricolaUtils;

/**
 * Session Bean implementation class Un01RepositoryImp
 */
@Stateless(mappedName = "un01Repository")
public class Un01RepositoryImp extends GeneralRepositoryImp<Long, Un01>  implements Un01Repository {
	@Inject
	Logger log;

    /**
     * Default constructor. 
     */
    public Un01RepositoryImp() {
        // sin implementar
    }
    
    /**
	 * Metodo que busca  por numero de tramite.
	 * @param numeroTramite Numero de tramite enviado
	 * @return Listado de entidades de tipo un01 encontradas
	 */
    public List<Un01> findByNumeroTramite( String numeroTramite, Date fechaEmision ) throws DefException{
    	try {
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(fechaEmision);
    		String str = String.valueOf(cal.get(Calendar.YEAR)).concat("-").concat("01").concat("-").concat("01");
    		Date fecha =  SiniestroAgricolaUtils.formatSringToDate(str);
			return this.findAllBySpecification(new Un01ByNumeroTramiteSpec(numeroTramite,fecha));
		} catch (Exception e) {
			throw new DefException( Constantes.ERROR_CODE_READ,
					"ERROR EN LA BUSQUEDA DE TAMITE EN Un01 CON NUMERO TRAMITE " + numeroTramite + "  " + e );
		}
    }

	@Override
	public Un01 finByIdentificacion(String identificacion) throws DefException {
		try {
			List<Un01> tmp = this.findAllBySpecification(new Un01ByIdentificacionSpec(identificacion));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new DefException( Constantes.ERROR_CODE_READ,
					"EN LA BUSQUEDA DE ASEGURADO EN Un01 CON " + identificacion + "  " + e );
		}
		return null;
	}

}
