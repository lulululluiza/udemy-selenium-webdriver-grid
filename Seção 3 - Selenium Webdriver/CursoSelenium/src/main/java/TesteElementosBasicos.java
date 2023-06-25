import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TesteElementosBasicos {

	@Test
	public void testeTextField() {
		
		//Caso o sistema não localize o drive
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();		
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		//System.getProperty("user.dir") = retorna o diretorio raiz do projeto (CursoSelenium)
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//campo Nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Texto aleatório");
		Assert.assertEquals("Texto aleatório", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	
		driver.quit();
	}
	
	@Test
	public void testeTextArea() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste aleatório no text area");
		Assert.assertEquals("Teste aleatório no text area", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	
		driver.quit();
	}
	
	@Test
	public void testeRadioButton() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void testeCheckboxSelecionado() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void testeComboboxSelecionado() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByIndex(3);
		combo.selectByValue("especializacao");
		combo.selectByVisibleText("Mestrado");
		
		Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
		
		driver.quit();
	}
	
	@Test
	public void testeComboboxTodosValores() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);		
		List<WebElement> opcoesCombo = combo.getOptions();
		
		Assert.assertEquals(8, opcoesCombo.size());
		
		Boolean encontrouOpcao = false;
		
		for(WebElement option: opcoesCombo) {
			if(option.getText().equals("2o grau incompleto")) {
				encontrouOpcao = true;
				break;
			}
		}
		
		Assert.assertTrue(encontrouOpcao);
		
		driver.quit();
	}
	
	@Test
	public void testeComboboxMultiplosValores () {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		List<WebElement> selecionados = combo.getAllSelectedOptions();
		
		Assert.assertEquals(3, selecionados.size());
		
		
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

		//deselecionar uma e revalidar
		combo.deselectByVisibleText("O que eh esporte?");
		selecionados = combo.getAllSelectedOptions();
		Assert.assertEquals(2, selecionados.size());
		
		driver.quit();
	}
	
	@Test
	public void testeBotao() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.findElement(By.id("buttonSimple")).click();
		
		Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
	}
	
	@Test
	public void testeLink() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		//texto do link, não a url em si
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void testeBuscarTextosPagina() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		
		//geral, por toda tag body
		Assert.assertTrue(driver.findElement(By.tagName("body"))
				.getText().contains("Campo de Treinamento"));
		
		//especifico
		Assert.assertTrue(driver.findElement(By.tagName("h3")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());

		//span retorma mais de um, porém um deles possui uma classe unica
		//Assert.assertEquals(" Cuidado onde clica, muitas armadilhas... ", driver.findElement(By.tagName("span")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
	}
}
