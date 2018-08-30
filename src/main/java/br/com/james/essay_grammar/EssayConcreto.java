package br.com.james.essay_grammar;

import br.com.james.essay_grammar.models.EssayModel;

public class EssayConcreto extends EssayModel {

	public EssayConcreto(String texto) {
		super(texto);
	}

	@Override
	public void extrairFeatures() {
		System.out.println("foi se ");
	}

}
