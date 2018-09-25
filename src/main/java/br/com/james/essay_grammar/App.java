package br.com.james.essay_grammar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.james.essay_grammar.models.EssayModel;
import br.com.james.essay_grammar.models.IOFileModel;

public class App {

	static Analisador ANALISADOR = new Analisador();

	public static void main(String[] args) {

		//String[] arquivo = { "dataset/texto0.txt", "dataset/texto1.txt", "dataset/texto2.txt","dataset/texto3.txt" };
		
		List<String> lsArquivos = lerArquivo("list_dataset.txt");
		
		IOFileModel fileIO = new LeitorConcreto();

		fileIO.abrirArquivo("saida.csv");

		fileIO.adicionarLinha(ANALISADOR.getCabecalho());
		
		for (String arquivo : lsArquivos) {
			EssayModel redacao = fileIO.readEssay(arquivo);
			ANALISADOR.analisarRedacao(redacao);
			System.out.println(redacao.featuresCSV());
			fileIO.adicionarLinha(redacao.featuresCSV());
		}
		
		fileIO.fecharArquivo();

		System.out.println("concluido");
	}

	private static List<String> lerArquivo(String caminho) {
		BufferedReader br = null;
		List<String> stList = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(caminho));
			String linha;
			while((linha = br.readLine())!= null) {
				stList.add(linha);
			}
			br.close();
			return stList;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
