import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class TesteAlert {

	@Test
	public void testeAlertaSimples() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.findElement(By.id("alert")).click();
				
		//Alertas ficam em outro foco
		Alert alert = driver.switchTo().alert();		
		Assert.assertEquals("Alert Simples", alert.getText());
		String textoAlerta = alert.getText();
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);
		Assert.assertEquals("Alert Simples", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void testeAlertaConfirmar() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		
		alert.accept();				
		assertEquals("Confirmado", alert.getText());
		alert.accept();	
		
		driver.findElement(By.id("confirm")).click();
		alert = driver.switchTo().alert();
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void testeAlertaPrompt() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
		driver.quit();
	}
}
