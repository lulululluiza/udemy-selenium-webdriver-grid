package lpm.cursos.CursoSelenium.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;


public class TesteGoogle {
	
	DSL dsl;
	
	@Before
	public void inicializaDriver() {
		DriverFactory.getDriver().get("https://google.com.br");		
		

		dsl = new DSL();
	}
	
	@After
	public void fecharNavegador (){
		DriverFactory.killDriver();
	}
	
	@Test
	public void teste() {		
		Assert.assertEquals("Google", dsl.obterTituloPagina());
	}
}
