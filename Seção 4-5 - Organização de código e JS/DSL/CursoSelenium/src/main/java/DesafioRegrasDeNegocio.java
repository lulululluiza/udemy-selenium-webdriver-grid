import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioRegrasDeNegocio {

	WebDriver driver;
	DSL dsl;
	
	//Antes de cada @Test, roda esta função
	@Before
	public void inicializaDriver() {
		//Caso o sistema não localize o drive
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
				
		driver = new ChromeDriver();		
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
				
		//System.getProperty("user.dir") = retorna o diretorio raiz do projeto (CursoSelenium)
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
	}
	
	//Após a execução de cada @Test, executa esta função
	@After
	public void fecharNavegador() {
		//Caso algum teste dê erro, a execução seguirá por causa do After, que sempre rodará
		driver.quit();
	}	

	@Test
	public void testeNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Nome eh obrigatorio", dsl.obterTextoAlertaeAceitar());
	}
	
	@Test
	public void testeSobrenomeObrigatorio() {
		dsl.escreve("elementosForm:nome", "Nome");
		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.obterTextoAlertaeAceitar());
	}
	
	@Test
	public void testeSexoObrigatorio() {
		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Sexo eh obrigatorio", dsl.obterTextoAlertaeAceitar());
	}
	
	@Test
	public void testeVegetarianoQueComeCarne() {
		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarRadio("elementosForm:sexo:1");
		
		dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
		dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
		
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterTextoAlertaeAceitar());;
	}
	
	@Test
	public void testeEsportistaQueNaoSabeOQueEEsporte() {
		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarRadio("elementosForm:sexo:1");
		dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
		
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterTextoAlertaeAceitar());
	}
}
