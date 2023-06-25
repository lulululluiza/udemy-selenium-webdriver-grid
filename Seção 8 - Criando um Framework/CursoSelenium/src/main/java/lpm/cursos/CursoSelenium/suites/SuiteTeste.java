package lpm.cursos.CursoSelenium.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import lpm.cursos.CursoSelenium.core.DriverFactory;
import lpm.cursos.CursoSelenium.test.DesafioCadastroCompleto;
import lpm.cursos.CursoSelenium.test.TesteDeveValidarRegras;
import lpm.cursos.CursoSelenium.test.TesteElementosBasicos;

@RunWith(Suite.class)
@SuiteClasses({
	DesafioCadastroCompleto.class,
	TesteDeveValidarRegras.class,
})
public class SuiteTeste {
	
	/*Após todos os testes da classe serem rodados, dai sim roda este método*/
	@AfterClass
	/*Reúso do browser*/
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}	
}
