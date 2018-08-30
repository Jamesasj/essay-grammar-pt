package br.com.james.essay_grammar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import br.com.james.essay_grammar.models.EssayModel;
import br.com.james.essay_grammar.models.IOFileModel;

public class LeitorConcreto extends IOFileModel {

	@Override
	public List<EssayModel> readListEssay(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EssayModel readEssay(String filename) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));

			String st, texto = "";
			while ((st = br.readLine()) != null) {
				texto += st;
			}
			br.close();
			return new EssayConcreto(texto);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

}
