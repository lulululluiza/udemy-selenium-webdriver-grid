import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class DesafioCadastroCompleto {

	@Test
	public void desafioCadastroCompleto() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		/*Validar:
		 *Preenchimento do Formulário;
		 *Clique no Cadastrar;
		 *Valores que aparecerem;
		 */
		
		//Nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Fulano");
		Assert.assertEquals("Fulano", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		//Sobrenome
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("da Silva");
		Assert.assertEquals("da Silva", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		//Sexo
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
		
		//Qual sua comida favorita? 	
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());

		//Escolaridade
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByIndex(3);		
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		//Pratica esportes?
		element = driver.findElement(By.id("elementosForm:esportes"));
		combo = new Select(element);
		combo.selectByIndex(1);
		combo.selectByIndex(2);
		combo.selectByIndex(3);
		List<WebElement> itensCombo = combo.getAllSelectedOptions();
		int countSelecionadosCertos = 0;
		
		for(WebElement item: itensCombo) {
			String valor = item.getText();
			if(valor.equals("Futebol") || valor.equals("Corrida") || valor.equals("Karate")) {
				countSelecionadosCertos += 1;
			}
		}
		Assert.assertEquals(3, countSelecionadosCertos);
		
		//Sugestoes
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Nenhuma");
		Assert.assertEquals("Nenhuma", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		//Cadastrar
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		//Assert.assertTrue("Cadastrado!", driver.findElement(By.id("resultado")).getText().contains("Cadastrado!"));
		//ou
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		//Assert.assertEquals("Nome: Fulano", driver.findElement(By.id("descNome")).getText());
		//ou
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Fulano"));		
		Assert.assertEquals("Sobrenome: da Silva", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Frango", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: 2graucomp", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol Corrida Karate", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Nenhuma", driver.findElement(By.id("descSugestoes")).getText());
				
	}
}
