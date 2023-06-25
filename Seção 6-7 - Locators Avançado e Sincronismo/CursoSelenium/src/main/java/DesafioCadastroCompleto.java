import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DesafioCadastroCompleto {
	
	WebDriver driver;
	DSL dsl;
	DesafioCadastroCompletoPage page;
	
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
		page = new DesafioCadastroCompletoPage(driver);
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
		page.setNome("Fulano");
		Assert.assertEquals("Fulano", dsl.obterValorCampo("elementosForm:nome"));
		
		//Sobrenome
		page.setSobrenome("da Silva");
		Assert.assertEquals("da Silva", dsl.obterValorCampo("elementosForm:sobrenome"));
		
		//Sexo
		page.setSexoFeminino();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:1"));
		
		//Qual sua comida favorita? 
		page.setComidaFrango();	
		Assert.assertTrue(dsl.isCheckboxMarcado("elementosForm:comidaFavorita:1"));

		//Escolaridade
		page.setEscolaridade("2o grau completo");		
		Assert.assertEquals("2o grau completo", dsl.obterPrimeiroValorSelecionadoCombo("elementosForm:escolaridade"));
		
		//Pratica esportes?
		page.setEsportes("Futebol");
		page.setEsportes("Corrida");
		page.setEsportes("Karate");
		
		Assert.assertEquals(3, dsl.retornaQuantiaOpcoesSelecionadosCombo("elementosForm:esportes"));
		Assert.assertTrue(dsl.retornaValoresSelecionadosCombo("elementosForm:esportes").containsAll(Arrays.asList("Futebol", "Corrida", "Karate")));
	
		//Sugestoes
		page.setSugestoes("Nenhuma");
		Assert.assertEquals("Nenhuma", dsl.obterValorCampo("elementosForm:sugestoes"));
		
		//Cadastrar
		page.cadastrar();
		//Assert.assertTrue("Cadastrado!", driver.findElement(By.id("resultado")).getText().contains("Cadastrado!"));
		//ou
		
		Assert.assertEquals("Cadastrado!", page.getResultadoCadastro());
		//Assert.assertEquals("Nome: Fulano", driver.findElement(By.id("descNome")).getText());
		//ou
		Assert.assertEquals("Fulano", page.getNomeCadastro());	
		Assert.assertEquals("da Silva", page.getSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.getSexoCadastro());
		Assert.assertEquals("Frango", page.getComidaCadastro());
		Assert.assertEquals("2graucomp", page.getEscolaridadeCadastro());
		Assert.assertEquals("Futebol Corrida Karate", page.getEsporteCadastro());
		Assert.assertEquals("Nenhuma", page.getSugestoesCadastro());	
	}
}
