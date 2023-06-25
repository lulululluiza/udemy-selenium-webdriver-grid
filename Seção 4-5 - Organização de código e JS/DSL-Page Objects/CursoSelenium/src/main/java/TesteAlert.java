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

public class TesteAlert {

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
