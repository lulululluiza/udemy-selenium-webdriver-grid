
/*
 * Centralizar métodos genéricos utilizados com frequência para aumentar o reuso e deixar as chamadas dos métodos mais explicativas,
 * ao invés de usar os métodos puros do Selenium
 * */

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	/********************* Text Field e Text Area **********************/
	
	public void escreve(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);		
	}
	
	public void escreve(String id_campo, String texto) {
		escreve(By.id(id_campo), texto);
	}
		
	public String obterValorCampo(By by) {
		return driver.findElement(by).getAttribute("value");
	}
	
	public String obterValorCampo(String id_campo) {
		return obterValorCampo(By.id(id_campo));
	}
	
	
	/****************** Radio e Checkbox *********************/
	
	public void clicarRadio(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	public Boolean isRadioMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	public void clicarCheckbox(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}	
	
	public Boolean isCheckboxMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	
	/************************ Button *************************/
	
	public void clicarBotao(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	
	/************************ Link ***************************/
	
	public void clicarLink(String texto_link) {
		driver.findElement(By.linkText(texto_link)).click();
	}
	
	
	/*********************** Combobox ************************/
	
	public void selecionarCombo(String id_campo, String selecionar) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(selecionar);
	}
	
	public void deselecionarCombo(String id_campo, String deselecionar) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.deselectByVisibleText(deselecionar);
	}
	
	public String obterPrimeiroValorSelecionadoCombo(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getFirstSelectedOption().getText();		
	}
	
	public List<WebElement> retornaSelecionadosCombo(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getAllSelectedOptions();
	}
	
	public List<WebElement> retornaOpcoesCombo(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getOptions();
	}
	
	public List<String> retornaValoresSelecionadosCombo(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		List<WebElement> elementosCombo = combo.getAllSelectedOptions();
		List<String> valoresSelecionados = new ArrayList<String>();
		
		for(WebElement item: elementosCombo) {
			valoresSelecionados.add(item.getText());
		}
		
		return valoresSelecionados;
	}
	
	public int validaTamanhoCombo(String id_campo) {
		List<WebElement> opcoesCombo = retornaOpcoesCombo(id_campo);		
		
		return opcoesCombo.size();
	}
	
	public int retornaQuantiaOpcoesSelecionadosCombo(String id_campo) {
		List<WebElement> opcoesSelecionadasCombo = retornaSelecionadosCombo(id_campo);
		
		return opcoesSelecionadasCombo.size();
	}
	
	public Boolean validaOpcaoEstaNoCombo(String id_campo, String opcaoCombo) {
		Boolean encontrouOpcao = false;
		
		List<WebElement> opcoesCombo = retornaOpcoesCombo(id_campo);
		
		for(WebElement option: opcoesCombo) {
			if(option.getText().equals(opcaoCombo)) {
				encontrouOpcao = true;
				break;
			}
		}
		
		return encontrouOpcao;
	}
	
	
	/************************ Textos ************************/
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id_campo) {
		return obterTexto(By.id(id_campo));
	}
	
	public Boolean obterSeTextoContem(By by, String contem) {
		return driver.findElement(by).getText().contains(contem);
	}
	
	public Boolean obterSeTextoIniciaCom(By by, String iniciaCom) {
		return driver.findElement(by).getText().startsWith(iniciaCom);
	}
	
	public Boolean obterSeTextoTerminaCom(By by, String terminaCom) {
		return driver.findElement(by).getText().endsWith(terminaCom);
	}
	
	
	/************************ Alertas ************************/

	public String obterTextoAlerta () {
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();		
		return textoAlerta;
	}
	
	public String obterTextoAlertaeAceitar () {
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	public String confirmarEObterTextoDoAlerta () {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	public String negarEObterTextoDoAlerta () {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	
	/************************ Prompt ************************/

	public void escrevePrompt(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	
	/******************* Frames e Janelas *******************/

	public void trocarParaOFrame(String id_frame) {
		driver.switchTo().frame(id_frame);
	}

	public void retornarConteudoDefault() {
		driver.switchTo().defaultContent();		
	}

	public void trocarParaOutraJanela(String id_janela) {
		driver.switchTo().window(id_janela);		
	}
	
	public String obterHandleJanela(int janela) {
		return driver.getWindowHandles().toArray()[janela].toString();
	}
	
	public void fecharJanela() {
		driver.close();
	}

	public String obterTituloPagina() {
		return driver.getTitle();
	}
	
	/***************** Javascript ********************/
	
	public Object executarJS(String comando, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) driver;				
		
		//arguments[0] = element ---- arguments[1] = "solid 4px red"
		return js.executeScript(comando, params);	
	}
	
}
