package lpm.cursos.CursoSelenium.test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import lpm.cursos.CursoSelenium.core.BaseTest;
import lpm.cursos.CursoSelenium.core.DSL;
import lpm.cursos.CursoSelenium.core.DriverFactory;
import lpm.cursos.CursoSelenium.page.DesafioCadastroCompletoPage;

@RunWith(Parameterized.class)
public class TesteDeveValidarRegras extends BaseTest{
	private DSL dsl;
	private DesafioCadastroCompletoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msgAlerta;
	
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		dsl = new DSL();
		page = new DesafioCadastroCompletoPage();
	}
		
	@Parameters
	public static Collection<Object[]> getColecao() {
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Nome", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Nome", "Sobrenome", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Nome", "Sobrenome", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Nome", "Sobrenome", "Feminino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"},
		});
	}

	@Test
	public void deveValidarRegrasCadastro() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Feminino"))
			page.setSexoFeminino();
		if(sexo.equals("Masculino"))
			page.setSexoMasculino();
		if(comidas.contains("Carne"))
			page.setComidaCarne();
		if(comidas.contains("Frango"))
			page.setComidaFrango();
		if(comidas.contains("Pizza"))
			page.setComidaPizza();
		if(comidas.contains("Vegetariano"))
			page.setComidaVegetariano();		
		page.setEsportes(esportes);

		page.cadastrar();
		System.out.println(msgAlerta);

		Assert.assertEquals(msgAlerta, dsl.obterTextoAlertaeAceitar());
	}
}
