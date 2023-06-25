package lpm.cursos.CursoSelenium.core;

public class Propriedades {
	
	/*define se ao executar os testes sá reutilizado o mesmo browser*/
	public static boolean FECHAR_BROWSER = false;
	public static Browsers browser = Browsers.CHROME; 
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
}
