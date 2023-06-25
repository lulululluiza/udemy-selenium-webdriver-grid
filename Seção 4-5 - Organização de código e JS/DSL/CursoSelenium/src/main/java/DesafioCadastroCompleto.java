import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DesafioCadastroCompleto {
	
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
	public void desafioCadastroCompleto() {

		/*Validar:
		 *Preenchimento do Formulário;
		 *Clique no Cadastrar;
		 *Valores que aparecerem;
		 */
		
		//Nome
		dsl.escreve("elementosForm:nome", "Fulano");
		Assert.assertEquals("Fulano", dsl.obterValorCampo("elementosForm:nome"));
		
		//Sobrenome
		dsl.escreve("elementosForm:sobrenome", "da Silva");
		Assert.assertEquals("da Silva", dsl.obterValorCampo("elementosForm:sobrenome"));
		
		//Sexo
		dsl.clicarRadio("elementosForm:sexo:1");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:1"));
		
		//Qual sua comida favorita? 
		dsl.clicarCheckbox("elementosForm:comidaFavorita:1");	
		Assert.assertTrue(dsl.isCheckboxMarcado("elementosForm:comidaFavorita:1"));

		//Escolaridade
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterPrimeiroValorSelecionadoCombo("elementosForm:escolaridade"));
		
		//Pratica esportes?
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");

		
		Assert.assertEquals(3, dsl.retornaQuantiaOpcoesSelecionadosCombo("elementosForm:esportes"));
		Assert.assertTrue(dsl.retornaValoresSelecionadosCombo("elementosForm:esportes").containsAll(Arrays.asList("Futebol", "Corrida", "Karate")));
	
		//Sugestoes
		dsl.escreve("elementosForm:sugestoes", "Nenhuma");
		Assert.assertEquals("Nenhuma", dsl.obterValorCampo("elementosForm:sugestoes"));
		
		//Cadastrar
		dsl.clicarBotao("elementosForm:cadastrar");
		//Assert.assertTrue("Cadastrado!", driver.findElement(By.id("resultado")).getText().contains("Cadastrado!"));
		//ou
		
		Assert.assertTrue(dsl.obterSeTextoIniciaCom(By.id("resultado"), "Cadastrado!"));
		//Assert.assertEquals("Nome: Fulano", driver.findElement(By.id("descNome")).getText());
		//ou
		Assert.assertTrue(dsl.obterSeTextoTerminaCom(By.id("descNome"), "Fulano"));	
		Assert.assertEquals("Sobrenome: da Silva", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Frango", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: 2graucomp", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol Corrida Karate", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Nenhuma", dsl.obterTexto("descSugestoes"));
				
	}
}
