package lpm.cursos.CursoSelenium.test;
import java.time.Duration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;


public class TesteSincronismoAjax {
	DSL dsl;
	
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");	
		
		dsl = new DSL();
	}
	
	@After
	public void fecharNavegador() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void sincronismoAjax() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));

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
