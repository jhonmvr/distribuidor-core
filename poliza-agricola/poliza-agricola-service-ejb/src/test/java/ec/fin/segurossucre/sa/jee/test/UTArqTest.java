package ec.fin.segurossucre.sa.jee.test;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.InvalidConfigurationFileException;
import org.jboss.shrinkwrap.resolver.api.ResolutionException;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import ec.fin.segurossucre.core.exception.SegSucreException;



@RunWith(Arquillian.class)
public class UTArqTest {

	
	
	/*@Inject
    private SiniestroAgricolaService siniestroAgricolaService;*/
	
	
	
	/**
	 * Metodo que genera el archivo jar de datamodel
	 * @return Jar generado
	 */
	private static JavaArchive generateDataModel() {
		File[] ejbDependenciesDm = null;
		try {
			ejbDependenciesDm = Maven.resolver()
					.loadPomFromFile("../siniestro-agricola-datamodel-ejb/pom.xml")
					.importDependencies(ScopeType.COMPILE, ScopeType.TEST)
					.resolve()
					.withTransitivity()
					.asFile();
		} catch (IllegalStateException e) {
			System.out.println("!!!!!!! IllegalStateException " + e.getMessage());
		} catch (ResolutionException e) {
			System.out.println("!!!!!!! ResolutionException " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("!!!!!!! IllegalArgumentException " + e.getMessage());
		} catch (InvalidConfigurationFileException e) {
			System.out.println("!!!!!!! InvalidConfigurationFileException " + e.getMessage());
		}
	    JavaArchive ejbDataModel = ShrinkWrap.create(JavaArchive.class, "siniestro-agricola-datamodel-ejb.jar")	    		
	    		.addPackage("ec.fin.segurossucre.sa.model")
	    		.addPackage("ec.fin.segurossucre.sa.enums")
	    		.addPackage("ec.fin.segurossucre.sa.util")
	    		.addPackage("ec.fin.segurossucre.sa.wrapper")
	    		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    if( ejbDependenciesDm != null ) {
		    for (File file : ejbDependenciesDm) {
		    	ejbDataModel.addAsResource(file);
		    }
	    }
	    return ejbDataModel;
	}
	
	/**
	 * Metodo que genera el archivo jar de repository
	 * @return Jar generado
	 */
	private static JavaArchive generateRepository() {
		File[] ejbDependenciesRepo=null;
		try {
			ejbDependenciesRepo= Maven.resolver()
					.loadPomFromFile("../siniestro-agricola-repository-ejb/pom.xml")
					.importDependencies(ScopeType.COMPILE, ScopeType.TEST)
					.resolve()
					.withTransitivity()
					.asFile();
		} catch (IllegalStateException e) {
			System.out.println("!!!!!!! IllegalStateException " + e.getMessage());
		} catch (ResolutionException e) {
			System.out.println("!!!!!!! ResolutionException " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("!!!!!!! IllegalArgumentException " + e.getMessage());
		} catch (InvalidConfigurationFileException e) {
			System.out.println("!!!!!!! InvalidConfigurationFileException " + e.getMessage());
		}
	    JavaArchive ejbRepository = ShrinkWrap.create(JavaArchive.class, "siniestro-agricola-repository-ejb.jar")	    		
	    		.addPackage("ec.fin.segurossucre.sa.repository")
	    		.addPackage("ec.fin.segurossucre.sa.repository.imp")
	    		.addPackage("ec.fin.segurossucre.sa.repository.spec")
	    		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    if(ejbDependenciesRepo!= null) {
		    for (File file : ejbDependenciesRepo) {
		    	ejbRepository.addAsResource(file);
		    }
	    }
	    return ejbRepository;
	}

	/**
	 * Metodo que genera el archivo jar de service
	 * @return Jar generado
	 */
	private static JavaArchive generateService() {
		File[] ejbDependenciesServ = Maven.resolver()
	    		.loadPomFromFile("pom.xml")
	    		.importDependencies(ScopeType.COMPILE, ScopeType.TEST)
	    		.resolve()
	    		.withTransitivity()
	    		.asFile();
	    JavaArchive ejbServ = ShrinkWrap.create(JavaArchive.class, "siniestro-agricola-service-ejb.jar")	  
	    		.addPackage("ec.fin.segurossucre.sa.cdi")
	    		.addPackage("ec.fin.segurossucre.sa.service")
	    		//.addPackage("ec.fin.segurossucre.sa.jee.test")
	    		.addClass(ec.fin.segurossucre.sa.jee.test.UTArqTest.class)
	    		.addAsManifestResource("META-INF/scripts/create.sql", "scripts/create.sql")
	    		.addAsManifestResource("META-INF/scripts/drop.sql", "scripts/drop.sql")
	    		.addAsManifestResource("META-INF/scripts/data.sql", "scripts/data.sql")
	    		/*.addAsManifestResource(EmptyAsset.INSTANCE, "scripts/create.sql")
	    		.addAsManifestResource(EmptyAsset.INSTANCE, "scripts/drop.sql")
	    		.addAsManifestResource( EmptyAsset.INSTANCE,"scripts/data.sql")*/
	    		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    
	    for (File file : ejbDependenciesServ) {
	    	ejbServ.addAsResource(file);
	    }
	    return ejbServ;
	}
	
	@Deployment
    public static Archive<?> createTestArchive() {
		System.out.println("===============>START CREATE EAR ARCHIVE");
		EnterpriseArchive ear = ShrinkWrap
	    		.create(EnterpriseArchive.class, "siniestro-agricola-ear.ear"); 
	    
		System.out.println("====>>START CREATE EJB DATAMODEL");	    
	    JavaArchive ejbDataModel = generateDataModel();
	    System.out.println("====>>END CREATE EJB DATAMODEL");	  
	    
	    System.out.println("====>>START CREATE EJB REPOSITORY");	  
	    JavaArchive ejbRepository=generateRepository();
	    System.out.println("====>>END CREATE EJB DATAMODEL");
	    
	    System.out.println("====>>START CREATE EJB SERVICE");
	    JavaArchive ejbServ=generateService();
	    System.out.println("====>>END CREATE EJB SERVICE");
	    
	    ear.addAsModule(ejbDataModel);
        ear.addAsModule(ejbRepository); 
        ear.addAsModule(ejbServ); 
	    
        ear.addAsLibrary("core-common-jee-1.0.0.jar");
	    ear.addAsLibrary("core-security-datamodel.jar");
	    ear.addAsLibrary("shrinkwrap-resolver-api-3.1.3.jar");
	    ear.setApplicationXML("test-application.xml");
	    ear.addAsManifestResource( "META-INF/test-persistence.xml","persistence.xml");
	    
	    System.out.println("================>END CREATE EAR ARCHIVE");
        return ear;
    }

	
	
	@BeforeClass 
	//@RunAsClient // runs as client
	public static void shouldBeAbleToRunOnClientSideBeforeRemoteTest() throws Exception {
		System.out.println("===>STARTING CLIENT");
	}
	
	
	
	/*@Test
	@InSequence(1)
	public void testManageAsegurado() {
		
		
			try {
				System.out.println("====>testAddAsegurado: " );
				TbSaAsegurado s=new TbSaAsegurado();
				s.setApellido("TAMAYO");
				s.setNombres( "luis alberto" );
				s=this.siniestroAgricolaService.manageAsegurado( s );
				Assert.assertNotNull(s);
				System.out.println("Id sasegurado " + s.getId() );
				s.setIdentificacion( "1711186575" );
				s=this.siniestroAgricolaService.manageAsegurado( s );
				Assert.assertNotNull(s);
			} catch (SegSucreException e) {
				//Assert.assertThat( e.getMessage(), );
				System.out.println("===>error encontrado " + e.getMensaje());
			}
	}*/
	
	/*@Test
	@InSequence(2)
	public void testFindAseguradoById() {
		System.out.println("===>testFindAseguradoById: ");
		
		try {
			
			TbSaAsegurado entity=this.siniestroAgricolaService.findAseguradoById( 
					Long.valueOf(2) );
			Assert.assertNotNull(entity);
			System.out.println("===>apol encontrado " + entity.getId());
		} catch (SegSucreException e) {
			//Assert.assertThat( e.getMessage(), is("001"));
			System.out.println("===>error encontrado " + e.getMensaje());
		}
	}*/
	
	/*@Test
	@InSequence(3)
	public void testListAsegurado() {
		System.out.println("===>testListAsegurado: ");
		
		try {
			PaginatedWrapper pw=new PaginatedWrapper();
			pw.setIsPaginated( PaginatedWrapper.YES );
			pw.setSortDirections("asc");
			pw.setSortFields("id");
			pw.setCurrentPage(0);
			pw.setPageSize(100);
			List<TbSaAsegurado> entities=this.siniestroAgricolaService.findAllAsegurado( pw );
			//System.out.println("===>retorna apols " + apos);
			Assert.assertNotNull(entities);
			for( TbSaAsegurado entity : entities ) {
				System.out.println("===>apol encontrado " + entity.getId());
			}
			
		} catch (SegSucreException e) {
			//Assert.assertThat( e.getMessage(), is("001"));
			System.out.println("===>error encontrado " + e.getMensaje());
		}
	}*/
	
	@Test
	@InSequence(1)
	public void testListConsultaGeneral() throws SegSucreException {
		System.out.println("===>testListConsultaGeneral: ");
		
		String s = "1";
		Assert.assertNull(s);
	}
	
	/*public void testListApol() {
		System.out.println("===>testListApol: ");
		
		try {
			PaginatedWrapper pw=new PaginatedWrapper();
			pw.setIsPaginated( PaginatedWrapper.YES );
			pw.setSortDirections("asc");
			pw.setSortFields("awsecuen");
			pw.setCurrentPage(1);
			pw.setPageSize(100);
			List<Apol> apos=this.siniestroAgricolaService.findAllApol( pw );
			//System.out.println("===>retorna apols " + apos);
			Assert.assertNotNull(apos);
			for( Apol a : apos ) {
				System.out.println("===>apol encontrado " + a.getAwagtcod());
			}
			
		} catch (SegSucreException e) {
			//Assert.assertThat( e.getMessage(), is("001"));
			System.out.println("===>error encontrado " + e.getMensaje());
		}
	}
	*/
}
