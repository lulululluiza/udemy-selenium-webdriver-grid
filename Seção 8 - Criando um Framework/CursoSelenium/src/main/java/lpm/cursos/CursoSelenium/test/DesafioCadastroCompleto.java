package lpm.cursos.CursoSelenium.test;
import lpm.cursos.CursoSelenium.core.DriverFactory;
import lpm.cursos.CursoSelenium.page.DesafioCadastroCompletoPage;
import lpm.cursos.CursoSelenium.core.BaseTest;
import lpm.cursos.CursoSelenium.core.DSL;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DesafioCadastroCompleto extends BaseTest{
	
	DSL dsl;
	DesafioCadastroCompletoPage page;
	
	@Before
	public void inicializaDriver() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");;
		dsl = new DSL();	
		page = new DesafioCadastroCompletoPage();
	}
		
	//Após a execução de cada @Test, executa esta função
	@After
	public void fecharNavegador() {
		//Caso algum teste dê erro, a execução seguirá por causa do After, que sempre rodará
		DriverFactory.killDriver();
	}		

	@Test
	public void desafioCadastroCompleto() {
		
		page.setNome("Fulano");
		page.setSobrenome("da Silva");
		page.setSexoFeminino();
		page.setComidaFrango();	
		page.setEscolaridade("2o grau completo");		
		page.setEsportes("Futebol");
		page.setEsportes("Corrida");
		page.setEsportes("Karate");
		page.setSugestoes("Nenhuma");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.getResultadoCadastro());
		Assert.assertEquals("Fulano", page.getNomeCadastro());	
		Assert.assertEquals("da Silva", page.getSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.getSexoCadastro());
		Assert.assertEquals("Frango", page.getComidaCadastro());
		Assert.assertEquals("2graucomp", page.getEscolaridadeCadastro());
		Assert.assertEquals("Futebol Corrida Karate", page.getEsporteCadastro());
		Assert.assertEquals("Nenhuma", page.getSugestoesCadastro());	
	}
}
