package lpm.cursos.CursoSelenium.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*Driver Centralizado*/
public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {}
	
	/*
	 * Caso não exista um driver ainda, ou seja, ninguém o chamou ainda, um novo é criado
	 * do contrário, o já instanciado por alguém é retornado
	 * */
	
	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Propriedades.browser) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: driver = new ChromeDriver(); break;
			}
			
			driver.manage().window().setPosition(new Point(250,20));
			driver.manage().window().setSize(new Dimension(1015,695));
		}
		
		return driver;
	}
	
	/*
	 * Se há um driver, ele o encerra e anula, caso outra instância precisa ser chamada
	 * senão.. o método não faz nada, para evitar gerar NPE
	 * */
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
