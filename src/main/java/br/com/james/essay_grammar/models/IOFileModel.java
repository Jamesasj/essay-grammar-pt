package br.com.james.essay_grammar.models;

import java.util.List;

public abstract class IOFileModel {
	public abstract List<EssayModel> readListEssay(String fileName);
	
	public abstract EssayModel readEssay(String filename);
}
