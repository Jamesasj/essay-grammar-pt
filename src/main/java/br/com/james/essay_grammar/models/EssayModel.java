package br.com.james.essay_grammar.models;

import java.util.HashMap;

import org.cogroo.analyzer.ComponentFactory;

import br.com.james.essay_grammar.Analisador;
import grammarpt.tools;

/* Lista de Features
 * 1 - Numero Erros
 * 2 - Numero Erros/Numero de tokens
 * 3 - Numero de Tokens
 * 4 - FleschScore ???
 * 5 - Media do tamanho palavras = soma tamanho palavras/Numero de tokens
 * 6 - Numero de correcores
 * 7 - Numero de correcores/Numero de tokens
 * 8 - Numero de sentencas > 70 caracteres
 * */
public abstract class EssayModel {

	private String identificador;
	private String texto;
	private String titulo;
	private String arquivo;
	private float nota_final;
	private HashMap notas = new HashMap();
	private HashMap features = new HashMap();

	public EssayModel(String texto, String titulo, String arquivo, HashMap notas, float nota_final,
			String identificador) {
		super();
		this.texto = texto;
		this.titulo = titulo;
		this.arquivo = arquivo;
		this.notas = notas;
		this.nota_final = nota_final;
		this.identificador = identificador;
	}

	public EssayModel(String texto, String titulo, String arquivo, float nota_final, String identificador) {
		super();
		this.texto = texto;
		this.titulo = titulo;
		this.arquivo = arquivo;
		this.nota_final = nota_final;
		this.identificador = identificador;
	}

	public EssayModel(String texto, String titulo, float nota_final, String identificador) {
		super();
		this.texto = texto;
		this.titulo = titulo;
		this.nota_final = nota_final;
		this.identificador = identificador;
	}

	public EssayModel(String texto, String titulo, float nota_final) {
		super();
		this.texto = texto;
		this.titulo = titulo;
		this.nota_final = nota_final;
	}

	public EssayModel(String texto, float nota_final) {
		super();
		this.texto = texto;
		this.nota_final = nota_final;
	}

	public EssayModel(String texto) {
		super();
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public HashMap getNotas() {
		return notas;
	}

	public void setNotas(HashMap notas) {
		this.notas = notas;
	}

	public float getNota_final() {
		return nota_final;
	}

	public void setNota_final(float nota_final) {
		this.nota_final = nota_final;
	}

	public String getIdentificador() {
		return identificador;
	}

	public HashMap getFeatures() {
		return features;
	}

	public String getFeaturesToString() {
		return "";
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void addNotas(String descricao, float nota) {
		this.notas.put(descricao, notas);
	}

	public EssayModel addFeatures(String chave, String valor) {
		this.features.put(chave, valor);
		return this;
	}

	public String getFeature() {
		return "";
	}

	public abstract void extrairFeatures();

	@Override
	public String toString() {
		return "EssayModel [identificador=" + identificador + ", titulo=" + titulo + ", arquivo=" + arquivo
				+ ", nota_final=" + nota_final + ", notas=" + notas + ", features=" + features + "]";
	}

}
