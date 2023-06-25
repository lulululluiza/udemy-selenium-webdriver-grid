package lpm.cursos.CursoSelenium.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;

public class TesteAlert {

	DSL dsl;
	
	//Antes de cada @Test, roda esta função
	@Before
	public void inicializaDriver() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		dsl = new DSL();
	}
	
	//Após a execução de cada @Test, executa esta função
	@After
	public void fecharNavegador() {
		//Caso algum teste dê erro, a execução seguirá por causa do After, que sempre rodará
		DriverFactory.killDriver();
	}

	@Test
	public void testeAlertaSimples() {
		dsl.clicarBotao("alert");
				
		//Alertas ficam em outro foco
		String textoAlerta = dsl.obterTextoAlertaeAceitar();
		Assert.assertEquals("Alert Simples", textoAlerta);		
		
		dsl.escreve("elementosForm:nome", textoAlerta);
		Assert.assertEquals("Alert Simples", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeAlertaConfirmar() {		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.obterTextoAlerta());
		
		Assert.assertEquals("Confirmado", dsl.confirmarEObterTextoDoAlerta());
		
		dsl.clicarBotao("confirm");		
		Assert.assertEquals("Negado", dsl.negarEObterTextoDoAlerta());
	}
	
	@Test
	public void testeAlertaPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.obterTextoAlerta());
		dsl.escrevePrompt("12");
		Assert.assertEquals("Era 12?", dsl.obterTextoAlertaeAceitar());
		Assert.assertEquals(":D", dsl.obterTextoAlertaeAceitar());
	}
}
