import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DesafioCadastroCompletoPage {
	
	private DSL dsl;
	
	
	public DesafioCadastroCompletoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicarCheckbox("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String escolaridade) {
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);		
	}
	
	public void setEsportes(String... esportes) {
		for(String item: esportes) {
			dsl.selecionarCombo("elementosForm:esportes", item);
		}
	}
	
	public void setSugestoes(String sugestoes) {
		dsl.escreve("elementosForm:sugestoes", sugestoes);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public Boolean getResultadoCadastro(String resultado) {
		return dsl.obterSeTextoIniciaCom(By.id("resultado"), resultado);
	}
	
	public Boolean getNomeCadastro(String nome) {
		return dsl.obterSeTextoTerminaCom(By.id("descNome"), nome);
	}
	
	public String getSobrenomeCadastro( ) {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String getSexoCadastro( ) {
		return dsl.obterTexto("descSexo");
	}

	public String getComidaCadastro( ) {
		return dsl.obterTexto("descComida");
	}
	
	public String getEscolaridadeCadastro( ) {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String getEsporteCadastro( ) {
		return dsl.obterTexto("descEsportes");
	}
	
	public String getSugestoesCadastro( ) {
		return dsl.obterTexto("descSugestoes");
	}
}
