package lpm.cursos.CursoSelenium.core;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*Herança de Comportamento*/
public class BaseTest{	
	
	@Rule
	public TestName testName = new TestName();
	
	@After
	public void fecharNavegador() throws IOException {
		TakesScreenshot print = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = print.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File(
				"target"+File.separator+"screenshots"+File.separator+testName.getMethodName()+".jpg"));
		
		/*Reúso do browser*/
		if(Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}	
}
