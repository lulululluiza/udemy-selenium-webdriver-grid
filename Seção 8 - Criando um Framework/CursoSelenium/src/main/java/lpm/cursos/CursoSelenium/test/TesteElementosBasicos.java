package lpm.cursos.CursoSelenium.test;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;


public class TesteElementosBasicos {
	
	DSL dsl;
	
	//Antes de cada @Test, roda esta função
	@Before
	public void inicializaDriver() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		
		dsl = new DSL();
	}
	
	//Após a execução de cada @Test, executa esta função
	@After
	public void fecharNavegador() {
		//Caso algum teste dê erro, a execução seguirá por causa do After, que sempre rodará
		DriverFactory.killDriver();
	}	

	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Texto aleatório");
		Assert.assertEquals("Texto aleatório", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste aleatório no text area");
		Assert.assertEquals("Teste aleatório no text area", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void testeRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void testeCheckboxSelecionado() {
		dsl.clicarCheckbox("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.isCheckboxMarcado("elementosForm:comidaFavorita:1"));
	}
	
	@Test
	public void testeComboboxSelecionado() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");		
		Assert.assertEquals("Mestrado", dsl.obterPrimeiroValorSelecionadoCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void testeComboboxTodosValores() {				
		Assert.assertEquals(8, dsl.validaTamanhoCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.validaOpcaoEstaNoCombo("elementosForm:escolaridade", "2o grau incompleto"));
	}
	
	@Test
	public void testeComboboxMultiplosValores () {
		
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		Assert.assertEquals(3, dsl.retornaQuantiaOpcoesSelecionadosCombo("elementosForm:esportes"));		
		
		List<WebElement> selecionados = dsl.retornaSelecionadosCombo("elementosForm:esportes");
		//ou, validar se todas as opções certas estão lá		
		int contador = 0;
		String valor = "";
		for(WebElement item: selecionados) {
			valor = item.getText();
			if(valor.equals("Futebol") || valor.equals("Corrida") || valor.equals("O que eh esporte?")) {
				contador += 1;
			}
		}				
		Assert.assertEquals(3, contador);		
		//ou 
		Assert.assertTrue(dsl.retornaValoresSelecionadosCombo("elementosForm:esportes").containsAll(Arrays.asList("Futebol", "Corrida", "O que eh esporte?")));
		
		//deselecionar uma e revalidar
		dsl.deselecionarCombo("elementosForm:esportes", "O que eh esporte?");		
		Assert.assertEquals(2, dsl.retornaQuantiaOpcoesSelecionadosCombo("elementosForm:esportes"));
	}
	
	@Test
	public void testeBotao() {
		dsl.clicarBotao("buttonSimple");
		
		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
	}
	
	@Test
	public void testeLink() {
		//texto do link, não a url em si
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void testeBuscarTextosPagina() {
		//geral, por toda tag body
		Assert.assertTrue(dsl.obterSeTextoContem(By.tagName("body"), "Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		//span retorma mais de um, porém um deles possui uma classe unica
		//Assert.assertEquals(" Cuidado onde clica, muitas armadilhas... ", driver.findElement(By.tagName("span")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void escreverDuasVezesNoMesmoTextField() {
		dsl.escreve("elementosForm:nome", "Valor 1");
		Assert.assertEquals("Valor 1", dsl.obterValorCampo("elementosForm:nome"));

		dsl.escreve("elementosForm:nome", "Valor 2");
		Assert.assertEquals("Valor 2", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void rodandoJavascript() {
		//JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		//js.executeScript("alert('Testando Javascript via Selenium :)')");
		//js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		//js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		
		dsl.executarJS("arguments[0].style.border = arguments[1]", element, "solid 4px red");
		
		//arguments[0] = element ---- arguments[1] = "solid 4px red"
		//js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		//dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Checkbox", "elementosForm:tableUsuarios");
		//dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
		//dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
		//dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Input", "elementosForm:tableUsuarios");
	}
}
