package br.com.james.essay_grammar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.cogroo.analyzer.Analyzer;
import org.cogroo.analyzer.ComponentFactory;
import org.cogroo.checker.CheckDocument;
import org.cogroo.checker.GrammarChecker;
import org.cogroo.text.Sentence;
import org.cogroo.text.Token;

import com.giullianomorroni.jcorretor.CorretorOrtografico;
import com.giullianomorroni.jcorretor.SugestaoOrtografia;

import br.com.james.essay_grammar.models.EssayModel;

public class Analisador {
	private ComponentFactory factory;
	private Analyzer cogroo;
	private GrammarChecker corretorGramatical;
	private CheckDocument documento;
	private int nTokens;
	private int totalLetras;
	private int sentencaGrande;
	private CorretorOrtografico corretorOrtografico;
	private List<String> lCorrecao;
	private String[] lCabecalho = { "TotalErros", "M.Erro", "T.Tokens", "FlashScore", "M.Palavras", "T.Correcoes",
			"M.Correcoes", "SentencaGrande" };

	public Analisador() {
		this.factory = ComponentFactory.create(new Locale("pt", "BR"));
		this.cogroo = factory.createPipe();
		try {
			this.corretorGramatical = new GrammarChecker(cogroo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		corretorOrtografico = new CorretorOrtografico();
	}

	public void analisarRedacao(EssayModel redacao) {
		this.nTokens = 0;
		this.totalLetras = 0;
		this.sentencaGrande = 0;
		this.documento = new CheckDocument(redacao.getTexto());
		this.corretorGramatical.analyze(documento); // ponto crítico
		lCorrecao = this.corretorOrtografico.corrigir(redacao.getTexto()).getTextoSugerido();
		analisar();
		redacao.addFeatures(this.lCabecalho[0], Integer.toString(this.getTotalErros()))
				.addFeatures(this.lCabecalho[1], Float.toString(this.getErroToken()))
				.addFeatures(this.lCabecalho[2], Integer.toString(this.getTotalTokens()))
				.addFeatures(this.lCabecalho[3], "NA")
				.addFeatures(this.lCabecalho[4], Float.toString(this.getLetrasToken()))
				.addFeatures(this.lCabecalho[5], Integer.toString(this.getTotalCorrecao()))
				.addFeatures(this.lCabecalho[6], Float.toString(this.getCorrecaoToken()))
				.addFeatures(this.lCabecalho[7], Integer.toString(this.getSentencasGrandes()));
	}

	private void analisar() {
		List<Sentence> lSentencas = this.documento.getSentences();
		for (Sentence sentenca : lSentencas) {
			List<Token> lTokens = sentenca.getTokens();
			this.nTokens = this.nTokens + lTokens.size();
			for (Token token : lTokens) {
				totalLetras = totalLetras + token.toString().length();
			}
			int tamanhoSentenca = sentenca.getText().length();
			if (tamanhoSentenca > 70) {
				sentencaGrande++;
			}
		}
	}

	public int getTotalErros() {
		return this.documento.getMistakes().size();
	}

	public float getErroToken() {
		return this.getTotalErros() / this.nTokens;
	}

	public int getTotalTokens() {
		return this.nTokens;
	}

	public int getSentencasGrandes() {
		return this.sentencaGrande;
	}

	public int getTotalCorrecao() {
		return this.lCorrecao.size();
	}

	public float getCorrecaoToken() {
		return this.getTotalCorrecao() / this.getTotalTokens();
	}

	public float getLetrasToken() {
		return this.totalLetras / this.getTotalTokens();
	}

	public String getCabecalho() {
		StringBuilder retorno = new StringBuilder();
		for (int i = 0; i < lCabecalho.length; i++) {
			retorno.append(this.lCabecalho[i]).append(", ");
		}
		return retorno.toString();

	}

}
