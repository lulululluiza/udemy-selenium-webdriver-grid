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
	
	public String getResultadoCadastro() {
		return dsl.obterTexto(By.xpath("//div[@id='resultado']/span"));
	}
	
	public String getNomeCadastro() {
		return dsl.obterTexto(By.xpath("//div[@id='descNome']/span"));
	}
	
	public String getSobrenomeCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descSobrenome']/span"));
	}
	
	public String getSexoCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descSexo']/span"));
	}

	public String getComidaCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descComida']/span"));

	}
	
	public String getEscolaridadeCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descEscolaridade']/span"));

	}
	
	public String getEsporteCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descEsportes']/span"));
	}
	
	public String getSugestoesCadastro( ) {
		return dsl.obterTexto(By.xpath("//div[@id='descSugestoes']/span"));
	}
}
