/* Centralizar métodos genéricos utilizados com frequência 
 * para aumentar o reuso e deixar as chamadas dos métodos mais explicativas,
 * ao invés de usar os métodos puros do Selenium
 * */
package lpm.cursos.CursoSelenium.core;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	/********************* Text Field e Text Area **********************/
	
	public void escreve(By by, String texto) {
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);		
	}
	
	public void escreve(String id_campo, String texto) {
		escreve(By.id(id_campo), texto);
	}
		
	public String obterValorCampo(By by) {
		return DriverFactory.getDriver().findElement(by).getAttribute("value");
	}
	
	public String obterValorCampo(String id_campo) {
		return obterValorCampo(By.id(id_campo));
	}
	
	
	/****************** Radio e Checkbox *********************/
	
	public void clicarRadio(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
	}
	
	public Boolean isRadioMarcado(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).isSelected();
	}
	
	public void clicarCheckbox(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
	}	
	
	public Boolean isCheckboxMarcado(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).isSelected();
	}
	
	
	/************************ Button *************************/
	
	public void clicarBotao(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
	}
	
	
	/************************ Link ***************************/
	
	public void clicarLink(String texto_link) {
		DriverFactory.getDriver().findElement(By.linkText(texto_link)).click();
	}
	
	
	/*********************** Combobox ************************/
	
	public void selecionarCombo(String id_campo, String selecionar) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(selecionar);
	}
	
	public void deselecionarCombo(String id_campo, String deselecionar) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.deselectByVisibleText(deselecionar);
	}
	
	public String obterPrimeiroValorSelecionadoCombo(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getFirstSelectedOption().getText();		
	}
	
	public List<WebElement> retornaSelecionadosCombo(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getAllSelectedOptions();
	}
	
	public List<WebElement> retornaOpcoesCombo(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		
		return combo.getOptions();
	}
	
	public List<String> retornaValoresSelecionadosCombo(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
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
		return DriverFactory.getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id_campo) {
		return obterTexto(By.id(id_campo));
	}
	
	public Boolean obterSeTextoContem(By by, String contem) {
		return DriverFactory.getDriver().findElement(by).getText().contains(contem);
	}	
	
	/************************ Alertas ************************/

	public String obterTextoAlerta () {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String textoAlerta = alert.getText();		
		return textoAlerta;
	}
	
	public String obterTextoAlertaeAceitar () {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	public String confirmarEObterTextoDoAlerta () {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.accept();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	public String negarEObterTextoDoAlerta () {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.dismiss();
		String textoAlerta = alert.getText();
		alert.accept();
		return textoAlerta;
	}
	
	
	/************************ Prompt ************************/

	public void escrevePrompt(String valor) {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	
	/******************* Frames e Janelas *******************/

	public void trocarParaOFrame(String id_frame) {
		DriverFactory.getDriver().switchTo().frame(id_frame);
	}

	public void retornarConteudoDefault() {
		DriverFactory.getDriver().switchTo().defaultContent();		
	}

	public void trocarParaOutraJanela(String id_janela) {
		DriverFactory.getDriver().switchTo().window(id_janela);		
	}
	
	public String obterHandleJanela(int janela) {
		return DriverFactory.getDriver().getWindowHandles().toArray()[janela].toString();
	}
	
	public void fecharJanela() {
		DriverFactory.getDriver().close();
	}

	public String obterTituloPagina() {
		return DriverFactory.getDriver().getTitle();
	}
	
	/***************** Javascript ********************/
	
	public Object executarJS(String comando, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();				
		
		//arguments[0] = element ---- arguments[1] = "solid 4px red"
		return js.executeScript(comando, params);	
	}
	
	
	/******************* Tabela **********************/
	
	public void clicarBotaoTabela(String colunaBusca, String valorBuscado, String colunaBotao, String idTabela) {
		//procurar coluna do registro
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[@id='"+idTabela+"']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valorBuscado, tabela, idColuna);

		//descobrir coluna do botão
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar botão na célula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}
	
	private int obterIndiceLinha(String valorBuscado, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));

		int idLinha = -1;
		for(int x=0; x<linhas.size(); x++) {
			if(linhas.get(x).getText().equals(valorBuscado)) {
				//linha = possui id iniciando em 0
				//elementos do xpath começam em 1
				idLinha = x+1;
				break;
			}
			/*
			 *Caso a tabela tivesse paginação, toda vez q o laço seria finalizado com 
			 *-1, seria clicado na próxima página para percorrer as linhas novamente 
			 * */
		}
				
		return idLinha;
	}

	public int obterIndiceColuna(String coluna, WebElement tabela) {
		//procurar coluna do registro
		int idColuna = -1;				
				
		/*no xpath 
		*- "//th" retorna a pesquisa a partir do começo do doc
		*- ".//th" faz a pesquisa a partir do contexto do elemento, no caso tabela
		*/
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
				
		for(int x=0; x<colunas.size(); x++) {
			if(colunas.get(x).getText().equals(coluna)) {
				//colunas = possui id iniciando em 0
				//elementos do xpath começam em 1
				idColuna = x+1;
				break;
			}
		}
		
		return idColuna;
	}
}
