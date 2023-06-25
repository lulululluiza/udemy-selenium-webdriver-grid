import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
	
	WebDriver driver;
	DSL dsl;
	
	@Before
	public void inicializaDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\luiza\\Downloads\\Cursos\\Testes funcionais com Selenium WebDriver - Do básico ao GRID\\drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		//ponto (x,y) na tela que o browser vai abrir, a partir do canto superior esquerdo
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("https://www.google.com.br");		
		//System.out.println(driver.getTitle());
		dsl = new DSL(driver);
	}
	
	@After
	public void fecharNavegador (){
		driver.quit();
	}
	
	@Test
	public void teste() {		
		Assert.assertEquals("Google", dsl.obterTituloPagina());
	}
}
