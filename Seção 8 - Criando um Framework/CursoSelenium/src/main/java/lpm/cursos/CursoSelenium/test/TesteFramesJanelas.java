package lpm.cursos.CursoSelenium.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;

public class TesteFramesJanelas {

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
		DriverFactory.killDriver();
		
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
		System.out.println(DriverFactory.getDriver().getWindowHandle());
		
		//todas elas
		System.out.println(DriverFactory.getDriver().getWindowHandles());
				
		dsl.trocarParaOutraJanela(dsl.obterHandleJanela(1));
		dsl.escreve(By.tagName("textarea"), "Janela secreto O.O");
		String valorJanela = dsl.obterValorCampo(By.tagName("textarea"));
		
		dsl.trocarParaOutraJanela(dsl.obterHandleJanela(0));
		dsl.escreve("elementosForm:nome", valorJanela);
		Assert.assertEquals(valorJanela, dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveIteragirComFrameEscondido() {
		
		WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));		
		//scrollby = rolar a tela para(x, y) --- getLocation = retorna x,y
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.trocarParaOFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.obterTextoAlertaeAceitar();
		Assert.assertEquals("Frame OK!", msg);
		
	}
}
