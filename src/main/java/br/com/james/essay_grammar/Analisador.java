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

import br.com.james.essay_grammar.models.EssayModel;

public class Analisador {
	private ComponentFactory factory;
	private Analyzer cogroo;
	private GrammarChecker corretorGramatical;
	private CheckDocument documento;
	private int nTokens;
	private int totalLetras;
	private int sentencaGrande;

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
	}

	public void analisarRedacao(EssayModel redacao) {
		this.nTokens = 0;
		this.totalLetras = 0;
		this.sentencaGrande = 0;
		this.documento = new CheckDocument(redacao.getTexto());
		this.corretorGramatical.analyze(documento); // analise gramatical porto crítico
		analisar();
		redacao.addFeatures("TotalErros", Integer.toString(this.getTotalErros()));
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

}
