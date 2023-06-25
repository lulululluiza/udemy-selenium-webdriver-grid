import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\luiza\\Downloads\\Cursos\\Testes funcionais com Selenium WebDriver - Do básico ao GRID\\drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		//ponto (x,y) na tela que o browser vai abrir, a partir do canto superior esquerdo
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("https://www.google.com.br");		
		//System.out.println(driver.getTitle());
		
		Assert.assertEquals("Google", driver.getTitle());
		
		//fecha todas as abas e as instâncias do drive
		driver.quit();
	}
}
