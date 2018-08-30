package br.com.james.essay_grammar;

import br.com.james.essay_grammar.models.EssayModel;
import br.com.james.essay_grammar.models.IOFileModel;

public class App {

	static Analisador ANALISADOR = new Analisador();

	public static void main(String[] args) {

		String arquivo = "/home/james/Documents/texto2.txt";

		IOFileModel leitor = new LeitorConcreto();

		EssayModel redacao = leitor.readEssay(arquivo);

		ANALISADOR.analisarRedacao(redacao);

		System.out.println(ANALISADOR.getTotalTokens());
		System.out.println(redacao);
		arquivo = "/home/james/Documents/texto2.txt";

		EssayModel redacao2 = leitor.readEssay(arquivo);

		ANALISADOR.analisarRedacao(redacao2);

		System.out.println(ANALISADOR.getTotalTokens());

	}
}
