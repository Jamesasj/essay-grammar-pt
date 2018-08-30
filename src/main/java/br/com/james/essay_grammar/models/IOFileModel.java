package br.com.james.essay_grammar.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class IOFileModel {
	private PrintWriter out;

	public abstract List<EssayModel> readListEssay(String fileName);

	public abstract EssayModel readEssay(String filename);

	public void abrirArquivo(String filename) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			this.out = new PrintWriter(bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void adicionarLinha(String texto) {
		this.out.println(texto);
	};

	public void fecharArquivo() {
		this.out.close();
	}
}
