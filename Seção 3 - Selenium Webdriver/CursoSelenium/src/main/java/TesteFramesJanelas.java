import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesJanelas {

	@Test
	public void TesteFrames() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertaValor = alert.getText();
		Assert.assertEquals("Frame OK!", alertaValor);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alertaValor);
		Assert.assertEquals(alertaValor, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	
		driver.quit();
	}
	

	@Test
	public void TesteJanelas() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("buttonPopUpEasy")).click();
		/*
		 * Nesse caso, o título da janela é conhecido: é o valor passado para a função
		 * */
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("aushasuhasuhas");
		String valorPopup = driver.findElement(By.tagName("textarea")).getAttribute("value");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.id("elementosForm:nome")).sendKeys(valorPopup);
		Assert.assertEquals(valorPopup, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		driver.quit();		
	}
	
	@Test
	public void TesteJanelasSemTitulo() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setPosition(new Point(250,20));
		driver.manage().window().setSize(new Dimension(1015,695));		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		//janela corrente
		System.out.println(driver.getWindowHandle());
		
		//todas elas
		System.out.println(driver.getWindowHandles());
		
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.findElement(By.tagName("textarea")).sendKeys("Janela secreto O.O");
		String valorJanela = driver.findElement(By.tagName("textarea")).getAttribute("value");
		
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		driver.findElement(By.id("elementosForm:nome")).sendKeys(valorJanela);
		Assert.assertEquals(valorJanela, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		driver.quit();
	}
}
