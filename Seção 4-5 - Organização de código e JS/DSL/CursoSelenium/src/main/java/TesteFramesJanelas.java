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

public class TesteFramesJanelas {

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
	public void TesteFrames() {
		dsl.trocarParaOFrame("frame1");
		dsl.clicarBotao("frameButton");
		
		String alertaValor = dsl.obterTextoAlertaeAceitar();
		Assert.assertEquals("Frame OK!", alertaValor);
		
		dsl.retornarConteudoDefault();
		dsl.escreve("elementosForm:nome", alertaValor);
		Assert.assertEquals(alertaValor, dsl.obterValorCampo("elementosForm:nome"));
	}
	

	@Test
	public void TesteJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		/*
		 * Nesse caso, o título da janela é conhecido: é o valor passado para a função
		 * */
		dsl.trocarParaOutraJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "aushasuhasuhas");
		String valorPopup = dsl.obterValorCampo(By.tagName("textarea"));
		dsl.fecharJanela();
		
		dsl.trocarParaOutraJanela("");
		dsl.escreve("elementosForm:nome", valorPopup);
		Assert.assertEquals(valorPopup, dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void TesteJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		
		//janela corrente
		System.out.println(driver.getWindowHandle());
		
		//todas elas
		System.out.println(driver.getWindowHandles());
				
		dsl.trocarParaOutraJanela(dsl.obterHandleJanela(1));
		dsl.escreve(By.tagName("textarea"), "Janela secreto O.O");
		String valorJanela = dsl.obterValorCampo(By.tagName("textarea"));
		
		dsl.trocarParaOutraJanela(dsl.obterHandleJanela(0));
		dsl.escreve("elementosForm:nome", valorJanela);
		Assert.assertEquals(valorJanela, dsl.obterValorCampo("elementosForm:nome"));
	}
}
