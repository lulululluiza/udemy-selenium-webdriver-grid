package lpm.cursos.CursoSelenium.test;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lpm.cursos.CursoSelenium.core.DriverFactory;
import lpm.cursos.CursoSelenium.core.DSL;

public class TesteSincronismo {

	DSL dsl;
	
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
				
		dsl = new DSL();
	}
	
	@After
	public void fecharNavegador() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirRespostaDemoradaSolucaoFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		
		//espera fixa
		Thread.sleep(5000);
		
		dsl.escreve("novoCampo", "Teste Solucao Fixa");		
	}
	
	/*
	 * implicitlyWait = declara o tempo máximo de espera, se o elemento
	 * já estiver disponível, ele avança o fluxo antes
	 * */
	@Test
	public void deveInteragirRespostaDemoradaSolucaoImplicita() {
		dsl.clicarBotao("buttonDelay");
		
		/*poderia-se declarar ele no Before para que todas as interações 
		*do driver esperassem esse tempo		*
		**/
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		dsl.escreve("novoCampo", "Teste Solucao Implicita 1");
		
		/*
		 *como o implicitlyWait está aqui, todas as interações abaixo dele no método seguirão
		 *com o comportamento de esperar até o tempo máximo definido
		 */
		DriverFactory.getDriver().navigate().refresh();
		dsl.clicarBotao("buttonDelay");
		dsl.escreve("novoCampo", "Teste Solucao Implicita 2");
	}
	
	@Test
	public void deveInteragirRespostaDemoradaSolucaoExplicita() {
		dsl.clicarBotao("buttonDelay");
		
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Teste Solucao Explicita 1");
		
		/*Aqui daria erro
		driver.navigate().refresh();
		dsl.clicarBotao("buttonDelay");
		dsl.escreve("novoCampo", "Teste Solucao Explicita 2");*/
	}
}
