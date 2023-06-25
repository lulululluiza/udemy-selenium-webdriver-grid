import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TesteSincronismoAjax {
	WebDriver driver;
	DSL dsl;
	
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
				
		//System.getProperty("user.dir") = retorna o diretorio raiz do projeto (CursoSelenium)
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");	
		
		dsl = new DSL(driver);
	}
	
	@After
	public void fecharNavegador() {
		//driver.quit();
	}
	
	@Test
	public void sincronismoAjax() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		dsl.escreve("j_idt343:name", "Teeeeeeeeeeste");
		dsl.clicarBotao("j_idt343:j_idt347");
		
		//não é uma boa ideia pq ele está lá, só sem texto 
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("j_idt343:display")));
		
		//pelo texto ser igual a
		//wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Teeeeeeeeeeste"));
		
		//ate o elemento desaparecer
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt395_start")));
		
		Assert.assertEquals("Teeeeeeeeeeste", dsl.obterTexto("j_idt343:display"));
	}

}
