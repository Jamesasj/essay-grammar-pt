package br.com.james.essay_grammar;

import br.com.james.essay_grammar.models.EssayModel;
import br.com.james.essay_grammar.models.IOFileModel;

public class App {

	static Analisador ANALISADOR = new Analisador();

	public static void main(String[] args) {

		String[] arquivo = { "texto.txt" };
		IOFileModel fileIO = new LeitorConcreto();

		fileIO.abrirArquivo("saida.csv");

		fileIO.adicionarLinha(ANALISADOR.getCabecalho());
		for (int i = 0; i < arquivo.length; i++) {
			EssayModel redacao = fileIO.readEssay(arquivo[i]);
			ANALISADOR.analisarRedacao(redacao);
			fileIO.adicionarLinha(redacao.featuresCSV());
		}
		fileIO.fecharArquivo();

		System.out.println("concluido");
	}
}
