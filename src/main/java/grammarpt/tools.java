package grammarpt;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class tools {

	private LinkedHashSet<Character> vogal_set;

	private LinkedHashSet<String> nasal_set;
	private LinkedHashSet<String> liquida_set;
	private LinkedHashSet<String> fricativa_set;
	private LinkedHashSet<String> oclusiva_set;
	private LinkedHashSet<String> digrafo_set;
	private LinkedHashSet<String> semivogal_set;

	public tools() {
		super();
		this.vogal_set = createVogalSet();
		this.nasal_set = createNasalSet();
		this.liquida_set = createLiquidaSet();
		this.fricativa_set = createFricativasSet();
		this.oclusiva_set = createOclusivasSet();
		this.digrafo_set = createDigrafoSet();
		this.semivogal_set = createSemiVogalSet();
	}

	public LinkedHashSet<Character> createVogalAgudoCircSet() {
		LinkedHashSet<Character> vogal_set = new LinkedHashSet<Character>();
		vogal_set.add('á');
		vogal_set.add('é');
		vogal_set.add('ó');
		vogal_set.add('í');
		vogal_set.add('ú');
		vogal_set.add('â');
		vogal_set.add('ê');
		vogal_set.add('î');
		vogal_set.add('ô');
		vogal_set.add('û');
		vogal_set.add('à');
		return vogal_set;
	}

	public LinkedHashSet<Character> createVogalTilSet() {
		LinkedHashSet<Character> vogal_set = new LinkedHashSet<Character>();
		vogal_set.add('ã');
		vogal_set.add('õ');
		return vogal_set;
	}

	public LinkedHashSet<Character> createVogalSet() {
		LinkedHashSet<Character> vogal_set = new LinkedHashSet<Character>();
		vogal_set.add('a');
		vogal_set.add('e');
		vogal_set.add('o');
		vogal_set.add('á');
		vogal_set.add('é');
		vogal_set.add('ó');
		vogal_set.add('í');
		vogal_set.add('ú');
		vogal_set.add('ã');
		vogal_set.add('õ');
		vogal_set.add('â');
		vogal_set.add('ê');
		vogal_set.add('ô');
		vogal_set.add('à');
		vogal_set.add('ü');
		return vogal_set;
	}

	public boolean isVogal(String w, int i) {
		int wlen = w.length();
		if (this.vogal_set.contains(w.charAt(i))) {
			return true;
		} else {
			// verificando encontros vocalicos: ditongos com i e u
			// colocam i e u como semivogais e nao como vogais
			if (w.charAt(i) == 'i' || w.charAt(i) == 'u') {
				if (i + 1 < wlen) {
					if (w.charAt(i) == 'i' && w.charAt(i + 1) == 'a' && i + 2 >= wlen) {
						return true;
					} else if (this.vogal_set.contains(w.charAt(i + 1))) {
						return false;
					} else {
						if (i - 1 >= 0) {
							if (this.vogal_set.contains(w.charAt(i - 1))) {
								return false;
							} else
								return true;
						} else {
							return true;
						}
					}
				} else {
					if (i - 1 >= 0) {
						if (this.vogal_set.contains(w.charAt(i - 1))) {
							return false;
						} else
							return true;
					} else {
						return true;
					}

				}
			} else
				return false;
		}
	}

	public boolean isVogalSemi(Character c) {
		// is vogal or semivogal
		return this.vogal_set.contains(c) || isSemiVogal(Character.toString(c), 0);
	}

	public LinkedHashSet<String> createSemiVogalSet() {
		LinkedHashSet<String> semivogal_set = new LinkedHashSet<String>();
		semivogal_set.add("i");
		semivogal_set.add("u");
		return semivogal_set;
	}

	public boolean isSemiVogal(String w, int i) {
		if (w.charAt(i) == 'i') {
			return true;
		} else {
			if (w.charAt(i) == 'u') {
				return true;
			} else {
				return false;
			}
		}
	}

	public LinkedHashSet<String> createDigrafoSet() {
		LinkedHashSet<String> digrafo_set = new LinkedHashSet<String>();
		digrafo_set.add("lh");
		digrafo_set.add("nh");
		return digrafo_set;
	}

	public boolean isDigrafo(String w, int i) {
		int wlen = w.length();
		if (i + 1 >= wlen)
			return false;
		int wlensub = w.substring(i).length();
		for (String s : this.digrafo_set) {
			if (wlensub >= 2 && w.substring(i, i + 2).equals(s)) {
				return true;
			}
		}
		return false;
	}

	public LinkedHashSet<String> createOclusivasSet() {
		LinkedHashSet<String> oclusivas_set = new LinkedHashSet<String>();
		oclusivas_set.add("p");
		oclusivas_set.add("t");
		oclusivas_set.add("b");
		oclusivas_set.add("d");
		oclusivas_set.add("ca");
		oclusivas_set.add("co");
		oclusivas_set.add("cu");
		oclusivas_set.add("ga");
		oclusivas_set.add("go");
		oclusivas_set.add("gu");
		oclusivas_set.add("gú");
		oclusivas_set.add("que");
		oclusivas_set.add("qui");
		oclusivas_set.add("gue");
		oclusivas_set.add("gui");
		return oclusivas_set;
	}

	public boolean isOclusiva(String w, int i) {
		int wlensub = w.substring(i).length();
		for (String s : this.oclusiva_set) {
			int slen = s.length();
			if (slen == 1) {
				if (w.substring(i, i + 1).equals(s)) {
					return true;
				}
			} else {
				if (wlensub >= 2 && slen == 2) {
					if (w.substring(i, i + 2).equals(s)) {
						return true;
					}
				} else {
					if (wlensub >= 3 && slen == 3) {
						if (w.substring(i, i + 3).equals(s)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public LinkedHashSet<String> createFricativasSet() {
		LinkedHashSet<String> fricativas_set = new LinkedHashSet<String>();
		fricativas_set.add("f");
		fricativas_set.add("v");
		fricativas_set.add("s");
		fricativas_set.add("ç");
		fricativas_set.add("z");
		fricativas_set.add("j");
		fricativas_set.add("x");
		fricativas_set.add("ce");
		fricativas_set.add("ci");
		fricativas_set.add("ss");
		fricativas_set.add("ce");
		fricativas_set.add("ch");
		fricativas_set.add("ge");
		fricativas_set.add("gi");
		return fricativas_set;
	}

	public boolean isFricativas(String w, int i) {
		int wlen = w.length();
		for (String s : this.fricativa_set) {
			if (s.length() == 1) {
				if (w.substring(i, i + 1).equals(s)) {
					return true;
				}
			} else {
				if (i + 1 < wlen) {
					if (w.substring(i, i + 2).equals(s)) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public LinkedHashSet<String> createLiquidaSet() {
		LinkedHashSet<String> liquida_set = new LinkedHashSet<String>();
		// except lh
		liquida_set.add("l");
		liquida_set.add("r");
		liquida_set.add("rr");
		return liquida_set;
	}

	public boolean isLiquida(String w, int i) {
		int wlen = w.length();
		if (w.charAt(i) == 'l') {
			if (i < wlen - 1 && w.charAt(i + 1) == 'h')
				return false;
			else
				return true;
		} else {
			if (w.charAt(i) == 'r') {
				return true;
			} else
				return false;
		}
	}

	public LinkedHashSet<String> createNasalSet() {
		LinkedHashSet<String> nasal_set = new LinkedHashSet<String>();
		nasal_set.add("m");
		nasal_set.add("n");
		return nasal_set;
	}

	public boolean isNasal(String w, int i) {
		return this.nasal_set.contains(w.substring(i, i + 1));
	}

	public boolean isConsoante(String w, int i) {
		if (isDigrafo(w, i)) {
			return true;
		}
		if (isOclusiva(w, i)) {
			return true;
		}
		if (isFricativas(w, i)) {
			return true;
		}
		if (isLiquida(w, i)) {
			return true;
		}
		if (isNasal(w, i)) {
			return true;
		}
		if (w.charAt(i) == 'g')
			return true;
		if (w.charAt(i) == 'c')
			return true;
		return false;
	}

	public boolean regra12Parte1(String w, int nucleo) {
		int wlen = w.length();
		// testar a primeira condicao da regra 12
		// i eh o inicio da silaba
		if (isConsoante(w, nucleo - 1)) {
			if (nucleo + 1 < wlen
					&& (isLiquida(w, nucleo + 1) || isNasal(w, nucleo + 1) || w.charAt(nucleo + 1) == 'c'
							|| w.charAt(nucleo + 1) == 'x')
					&& nucleo + 3 < wlen && (isVogal(w, nucleo + 3) || w.charAt(nucleo + 3) == 'h'
							|| w.charAt(nucleo + 3) == 'l' || w.charAt(nucleo + 3) == 'r')) {
				return true;

			}
		} else {
			if (nucleo - 2 >= 0 && (w.charAt(nucleo - 1) == 'u' || w.charAt(nucleo - 1) == 'ü')
					&& (w.charAt(nucleo - 2) == 'q' || w.charAt(nucleo - 2) == 'g')) {
				if (nucleo + 1 < wlen
						&& (isLiquida(w, nucleo + 1) || isNasal(w, nucleo + 1) || w.charAt(nucleo + 1) == 'c'
								|| w.charAt(nucleo + 1) == 'x')
						&& nucleo + 3 < wlen && (isVogal(w, nucleo + 3) || w.charAt(nucleo + 3) == 'h'
								|| w.charAt(nucleo + 3) == 'l' || w.charAt(nucleo + 3) == 'r')) {
					return true;

				}
			}
		}
		return false;

	}

	public boolean qAntesu(String w, int i) {
		// testar se existe qu em uma palavra na posicao i
		if (w.charAt(i) == 'u') {
			if (i - 1 >= 0) {
				if (w.charAt(i - 1) == 'q')
					return true;
				else
					return false;
			} else
				return false;
		} else
			return false;
	}

	public int findVogal(String w, int i, int stressed_graph) {
		int k;
		int wlen = w.length();
		for (k = i; k < wlen; k++) {
			if (isVogal(w, k)) {
				if (stressed_graph < k && stressed_graph >= i)
					return stressed_graph;
				else
					return k;
			}
		}
		return -1;
	}

	public ArrayList<String> word2syllables(String w) {
		ArrayList<String> s = new ArrayList<String>();
		int wlen = w.length();

		String syll = "";
		int stressed_graph = stressed_syll(w); // stressed grapheme in word w

		for (int i = 0; i < wlen;) {
			// a vogal eh o nucleo da silaba
			int nucleo = findVogal(w, i, stressed_graph);
			if (this.vogal_set.contains(w.charAt(i)) || w.charAt(i) == 'i' || w.charAt(i) == 'u') {

				// regra para os ditongos decrescentes
				if (i + 1 < wlen && (w.charAt(i) == 'a' || w.charAt(i) == 'e' || w.charAt(i) == 'o')
						&& (w.charAt(i + 1) == 'i' || w.charAt(i + 1) == 'u')) {
					if (stressed_graph == (i + 1)) {
						syll = syll + w.charAt(i);
						s.add(syll);
						syll = "";
					} else {
						syll = syll + w.charAt(i) + w.charAt(i + 1);
						s.add(syll);
						syll = "";
						i = i + 1;
					}

				} else if (w.charAt(i) != 'ã' && w.charAt(i) != 'õ' && i + 1 < wlen && isVogal(w, i + 1)
						&& !isSemiVogal(w, i + 1)) {
					// regra 1
					syll = syll + w.charAt(i);
					s.add(syll);
					syll = "";
				} else if (i + 3 < wlen && isConsoante(w, i + 1) && isConsoante(w, i + 2) && isOclusiva(w, i + 3)) {
					// regra 2
					syll = syll + w.charAt(i) + w.charAt(i + 1) + w.charAt(i + 2);
					s.add(syll);
					syll = "";
					i = i + 2;
				} else if (i + 2 < wlen
						&& (isSemiVogal(w, i + 1) || isNasal(w, i + 1) || w.charAt(i + 1) == 's'
								|| w.charAt(i + 1) == 'r' || w.charAt(i + 1) == 'l' || w.charAt(i + 1) == 'x')
						&& isConsoante(w, i + 2) && w.charAt(i + 2) != 's' && w.charAt(i + 2) != 'h'
						&& w.charAt(i + 2) != 'r') {
					// regra 3

					syll = syll + w.charAt(i) + w.charAt(i + 1);
					s.add(syll);
					syll = "";
					i = i + 1;
				} else if (i + 3 < wlen && isConsoante(w, i + 1) && isConsoante(w, i + 2) && isVogal(w, i + 3)) {
					// regra 4
					syll = syll + w.charAt(i);
					s.add(syll);
					syll = "";
				} else if (i + 2 < wlen && isDigrafo(w, i + 1)) {
					// regra 5
					if (i + 3 < wlen && (isVogal(w, i + 3) || isLiquida(w, i + 3))) {
						syll = syll + w.charAt(i);
						s.add(syll);
						syll = "";
					}
				} else if (i + 2 < wlen && isConsoante(w, i + 1) && (isVogal(w, i + 2) || isLiquida(w, i + 2))) {
					// regra 5
					syll = syll + w.charAt(i);
					s.add(syll);
					syll = "";
				} else {
					int k = i; // comeco da silaba
					for (; k < wlen; k++) {
						syll = syll + w.charAt(k);
						i++;
					}
					s.add(syll);
					syll = "";
				}

			} else {

				if (nucleo - 1 >= 0) {

					if (nucleo + 3 < wlen && isConsoante(w, nucleo - 1) && (isDigrafo(w, nucleo + 1)
							// regra 6
							|| (w.charAt(nucleo + 1) == 'c' && w.charAt(nucleo + 2) == 'h')) && nucleo + 3 < wlen
							&& isVogal(w, nucleo + 3)) {

						int k = i;
						for (; k < nucleo + 1; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";
					} else if (nucleo + 1 < wlen && isConsoante(w, nucleo - 1) && nucleo + 2 < wlen
							&& isConsoante(w, nucleo + 1) && isVogal(w, nucleo + 2)) {
						// regra 6

						int k = i;
						for (; k < nucleo + 1; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";

					} else if ((isConsoante(w, nucleo - 1) || isSemiVogal(w, nucleo - 1)) && nucleo + 2 < wlen
							&& isSemiVogal(w, nucleo + 1) && isConsoante(w, nucleo + 2)
							&& ((nucleo + 2 == wlen - 1 && w.charAt(nucleo + 2) != 'm' && w.charAt(nucleo + 2) != 'n'
									&& w.charAt(nucleo + 2) != 'r' && w.charAt(nucleo + 2) != 's')
									|| (nucleo + 2 != wlen - 1))) {
						// regra 7

						int k = i;
						for (; k < nucleo + 2; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";

					} else if (nucleo + 3 < wlen && isConsoante(w, nucleo - 1) && isSemiVogal(w, nucleo + 1)
							&& w.charAt(nucleo + 2) == 's' && isOclusiva(w, nucleo + 3)) {
						// regra 8
						int k = i;
						for (; k < nucleo + 3; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						s.add(syll);
						syll = "";

					} else if (nucleo + 1 < wlen && isConsoante(w, nucleo - 1) && isSemiVogal(w, nucleo + 1)
							&& (nucleo + 2 >= wlen || (nucleo + 2 < wlen && isVogal(w, nucleo + 2)))) {
						// regra 9

						int k = i;
						for (; k < nucleo + 2; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";

					} else if (nucleo + 2 < wlen && isSemiVogal(w, nucleo - 1) && isConsoante(w, nucleo + 1)
							&& isVogal(w, nucleo + 2)) {
						// regra 10
						int k = i;
						for (; k < nucleo; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";
					} else if (nucleo + 3 < wlen && isConsoante(w, nucleo - 1) && isSemiVogal(w, nucleo + 1)
							&& w.charAt(nucleo + 2) == 'r' && isConsoante(w, nucleo + 3)) {
						// regra 11
						int k = i;
						for (; k < nucleo + 3; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						s.add(syll);
						syll = "";
					} else if (regra12Parte1(w, nucleo)) {
						if (w.charAt(nucleo + 2) == 'h' || w.charAt(nucleo + 2) == 'l' || w.charAt(nucleo + 2) == 'r') {
							// regra 12
							int k = i;
							for (; k < nucleo + 1; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							i--;
							s.add(syll);
							syll = "";
						} else {
							int k = i;
							for (; k < nucleo + 2; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							i--;
							s.add(syll);
							syll = "";

						}
					} else if (nucleo + 1 < wlen && isConsoante(w, nucleo - 1)
							&& (isLiquida(w, nucleo + 1) || isNasal(w, nucleo + 1) || w.charAt(nucleo + 1) == 'i')) {
						// regra 13
						if (nucleo + 2 >= wlen) {
							// caso 6
							int k = i;
							for (; k < wlen; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							s.add(syll);
							syll = "";

						} else {
							if (w.charAt(nucleo + 2) == 's') {
								int k = i;
								for (; k < nucleo + 3; k++) {
									syll = syll + w.charAt(k);
									i++;
								}
								s.add(syll);
								syll = "";
							}
						}

					} else if ((nucleo + 1 < wlen && w.charAt(nucleo) == w.charAt(nucleo + 1))
							|| (nucleo + 2 < wlen && isVogal(w, nucleo + 1) && isVogal(w, nucleo + 2))) {
						// TODO: regra 14 (?) duvidas nessa regra (bueiro???)
						int k = i;
						for (; k < nucleo + 1; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";
					} else if (nucleo + 1 < wlen
							&& (isOclusiva(w, nucleo + 1) || w.charAt(nucleo + 1) == 'f' || w.charAt(nucleo + 1) == 'v'
									|| w.charAt(nucleo + 1) == 'g')
							&& (isOclusiva(w, nucleo + 1) || isLiquida(w, nucleo + 1)) && nucleo + 3 < wlen
							&& isVogal(w, nucleo + 3)) {
						// regra 15
						int k = i;
						for (; k < nucleo + 1; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";
					} else if (nucleo - 1 >= 0 && w.charAt(nucleo) == 'i' && isConsoante(w, nucleo - 1)
							&& nucleo + 1 < wlen && (w.charAt(nucleo + 1) == 'a' || w.charAt(nucleo + 1) == 'o')
							&& nucleo + 2 >= wlen) {
						// TODO: rever essa regra (a ação dela) // regra 16
						int k = i;
						for (; k < nucleo + 1; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						i--;
						s.add(syll);
						syll = "";

					} else if ((w.charAt(nucleo) == 'ã' || w.charAt(nucleo) == 'õ') && nucleo - 1 >= 0
							&& isConsoante(w, nucleo - 1) && nucleo + 1 < wlen
							&& (w.charAt(nucleo + 1) == 'o' || w.charAt(nucleo + 1) == 'e')) {
						// regra 17
						if (nucleo + 2 >= wlen) {
							int k = i; // comeco da silaba
							for (; k < wlen; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							s.add(syll);
							syll = "";
						} else {
							if (w.charAt(nucleo + 2) == 's') {
								int k = i; // comeco da silaba
								for (; k < wlen; k++) {
									syll = syll + w.charAt(k);
									i++;
								}
								s.add(syll);
								syll = "";
							}
						}

					} else if (nucleo - 1 >= 0 && (isConsoante(w, nucleo - 1) || qAntesu(w, nucleo - 1))
							&& nucleo + 2 < wlen && isConsoante(w, nucleo + 1) && isConsoante(w, nucleo + 2)) {
						if (w.charAt(nucleo + 1) == w.charAt(nucleo + 2)) {
							// regra 18
							// caso 1: V eh separada do grafema seguinte
							int k = i; // comeco da silaba
							for (; k < nucleo + 1; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							s.add(syll);
							syll = "";
						} else {
							if (w.charAt(nucleo + 1) == 's' && w.charAt(nucleo + 2) != 's') {
								// caso 2
								int k = i; // comeco da silaba
								for (; k < nucleo + 2; k++) {
									syll = syll + w.charAt(k);
									i++;
								}
								i--;
								s.add(syll);
								syll = "";
							} else {
								if (nucleo + 3 < wlen && w.charAt(nucleo + 2) == 's' && isOclusiva(w, nucleo + 3)) {
									// caso 5
									int k = i; // comeco da silaba
									for (; k < nucleo + 3; k++) {
										syll = syll + w.charAt(k);
										i++;
									}
									i--;
									s.add(syll);
									syll = "";
								}
							}
						}
					} else if (nucleo + 2 < wlen && isVogal(w, nucleo + 1) && isConsoante(w, nucleo + 2)) {
						// regra 19
						if (nucleo + 3 < wlen && isVogal(w, nucleo + 3)) {
							// caso 2
							int k = i; // comeco da silaba
							for (; k < nucleo + 2; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							i--;
							s.add(syll);
							syll = "";
						} else {
							// caso 1: V eh separada do grafema seguinte
							int k = i; // comeco da silaba
							for (; k < nucleo + 1; k++) {
								syll = syll + w.charAt(k);
								i++;
							}
							i--;
							s.add(syll);
							syll = "";
						}
					} else {
						int k = i; // comeco da silaba
						for (; k < wlen; k++) {
							syll = syll + w.charAt(k);
							i++;
						}
						s.add(syll);
						syll = "";
					}

				}
			}

			i = i + 1;
			// break;
		}

		return s;
	}

	public int findPenultimaVogal(String w) {
		int wlen = w.length();
		int countVogal = 0;
		boolean achou = false;
		int k;

		for (k = wlen - 1; k >= 0; k--) {
			if (isVogalSemi(w.charAt(k))) {
				countVogal++;
			}
			if (countVogal == 2) {
				achou = true;
				break;
			}
		}

		if (achou)
			return k;
		else
			return -1;
	}

	public int stressed_syll(String w) {
		int stressedlevel = 100;
		int stressedpos = 0;
		int lenw = w.length();
		LinkedHashSet<Character> agudoCirc = createVogalAgudoCircSet();
		LinkedHashSet<Character> tilSet = createVogalTilSet();

		int posPenultVogal = findPenultimaVogal(w);

		for (int i = lenw - 1; i >= 0; i--) {

			if (i == lenw - 1) {

				// regra 19
				if (posPenultVogal != -1 && stressedlevel > 19) {
					stressedlevel = 19;
					stressedpos = posPenultVogal;
				}

				// regra 18
				if (lenw >= 4 && w.charAt(i) == 'm' && w.charAt(i - 1) == 'e' && w.charAt(i - 2) == 'u'
						&& w.charAt(i - 3) == 'q' && stressedlevel > 18) {
					stressedlevel = 18;
					stressedpos = i - 1;
				}

				// regra 17
				if (posPenultVogal != -1 && stressedlevel > 17) {
					if (w.charAt(posPenultVogal) == 'i' || w.charAt(posPenultVogal) == 'u') {
						if (posPenultVogal - 1 >= 0 && isVogalSemi(w.charAt(posPenultVogal - 1))
								&& posPenultVogal + 1 < lenw && !isVogalSemi(w.charAt(posPenultVogal + 1))) {

							if ((posPenultVogal - 2) >= 0) {
								if (w.charAt(posPenultVogal - 2) != 'q' && w.charAt(posPenultVogal - 2) != 'g') {
									stressedlevel = 17;
									stressedpos = posPenultVogal - 1;
								}
							} else {
								stressedlevel = 17;
								stressedpos = posPenultVogal - 1;
							}
						}
					}
				}

				// regra 16
				if (lenw > 4 && (w.charAt(i) == 'a' || w.charAt(i) == 'e' || w.charAt(i) == 'o')
						&& isConsoante(w, i - 1) && w.charAt(i - 2) == 'n'
						&& (w.charAt(i - 3) == 'i' || w.charAt(i - 3) == 'u') && isVogalSemi(w.charAt(i - 4))
						&& stressedlevel > 16) {
					stressedpos = i - 3;
					stressedlevel = 16;
				}

				// regra 15
				if (lenw >= 6 && w.charAt(i) == 's' && isVogal(w, i - 1) && isVogal(w, i - 4)
						&& (w.charAt(i - 3) == 'i' || w.charAt(i - 3) == 'u') && !isVogal(w, i - 2)
						&& w.charAt(i - 5) != 'q' && w.charAt(i - 5) != 'g' && stressedlevel > 15) {
					stressedpos = i - 4;
					stressedlevel = 15;
				}

				// regra 14

				if (lenw >= 5 && isVogal(w, i) && isVogal(w, i - 3)
						&& (w.charAt(i - 2) == 'i' || w.charAt(i - 2) == 'u') && !isVogal(w, i - 1)
						&& w.charAt(i - 4) != 'q' && w.charAt(i - 4) != 'g' && stressedlevel > 14) {

					stressedpos = i - 3;
					stressedlevel = 14;
				}

				// regra 13
				if (lenw >= 3 && isVogal(w, i) && (w.charAt(i - 1) == 'i' || w.charAt(i - 1) == 'u')
						&& isVogal(w, i - 2) && stressedlevel > 13) {

					stressedpos = i - 2;
					stressedlevel = 13;
				}

				// regra 12
				if (lenw >= 5 && w.charAt(i) == 's' && w.charAt(i - 1) == 'e' && w.charAt(i - 2) == 'u'
						&& (w.charAt(i - 3) == 'q' || w.charAt(i - 3) == 'g') && stressedlevel > 12) {

					if (isVogalSemi(w.charAt(i - 4))) {
						stressedpos = i - 4;
					} else {
						stressedpos = i - 5;
					}
					stressedlevel = 12;
				}

				// regra 11
				if (lenw >= 4 && w.charAt(i) == 'e' && w.charAt(i - 1) == 'u'
						&& (w.charAt(i - 2) == 'q' || w.charAt(i - 2) == 'g') && stressedlevel > 11) {

					if (isVogalSemi(w.charAt(i - 3))) {
						stressedpos = i - 3;
					} else {
						stressedpos = i - 4;
					}
					stressedlevel = 11;
				}

				// regra 10
				if (lenw >= 5 && w.charAt(i) == 'e' && w.charAt(i - 1) == 'u' && w.charAt(i - 2) == 'q'
						&& w.charAt(i - 3) == 'r' && w.charAt(i - 4) == 'o' && w.charAt(i - 5) == 'p'
						&& stressedlevel > 10) {
					stressedlevel = 10;
					stressedpos = i;
				}

				// regra 9
				if (i - 2 >= 0 && w.charAt(i) == 's' && (w.charAt(i - 1) == 'i' || w.charAt(i - 1) == 'u')
						&& !isVogal(w, i - 2) && stressedlevel > 9) {
					stressedlevel = 9;
					stressedpos = i - 1;
				}

				// regra 8
				if (i - 1 >= 0 && w.charAt(i) == 's' && (w.charAt(i - 1) == 'i' || w.charAt(i - 1) == 'u')
						&& isVogalSemi(w.charAt(i - 2)) && stressedlevel > 8) {
					stressedlevel = 8;
					stressedpos = i - 2;
				}

				// regra 7
				if ((w.charAt(i) == 'i' || w.charAt(i) == 'u') && stressedlevel > 7) {
					if (i - 1 >= 0 && isVogalSemi(w.charAt(i - 1)) && w.charAt(i - 1) != 'u') {
						stressedpos = i - 1;
					} else {
						stressedpos = i;
					}

					stressedlevel = 7;

				}

				// regra 6
				if (i - 3 >= 0 && w.charAt(i) == 's' && w.charAt(i - 1) == 'i'
						&& (w.charAt(i - 2) == 'u' || w.charAt(i - 2) == 'ü')
						&& (w.charAt(i - 3) == 'q' || w.charAt(i - 3) == 'g') && stressedlevel > 6) {
					stressedlevel = 6;
					stressedpos = i - 1;
				}

				// regra 5
				if (i - 2 >= 0 && w.charAt(i) == 'i' && (w.charAt(i - 1) == 'u' || w.charAt(i - 1) == 'ü')
						&& (w.charAt(i - 2) == 'q' || w.charAt(i - 2) == 'g') && stressedlevel > 5) {
					stressedlevel = 5;
					stressedpos = i;

				}

				// regra 4
				if (i - 1 >= 0 && w.charAt(i) == 's' && w.charAt(i - 1) == 'n'
						&& (w.charAt(i - 2) == 'i' || w.charAt(i - 2) == 'o' || w.charAt(i - 2) == 'u')
						&& stressedlevel > 4) {
					stressedlevel = 4;
					stressedpos = i - 2;

				}

				// regra 3
				if (i - 1 >= 0 && w.charAt(i) == 'm'
						&& (w.charAt(i - 1) == 'i' || w.charAt(i - 1) == 'o' || w.charAt(i - 1) == 'u')
						&& stressedlevel > 3) {
					stressedlevel = 3;
					stressedpos = i - 1;
				}

				// regra 2
				if ((w.charAt(i) == 'r' || w.charAt(i) == 'l' || w.charAt(i) == 'z' || w.charAt(i) == 'x'
						|| w.charAt(i) == 'n') && stressedlevel > 2) {
					stressedlevel = 2;
					stressedpos = i - 1;

				}
			}

			// regra 1
			if (agudoCirc.contains(w.charAt(i))) {
				stressedlevel = 0;
				stressedpos = i;
			} else {
				// regra 1 ainda: mas com o til
				if (tilSet.contains(w.charAt(i)) && stressedlevel > 1) {
					stressedlevel = 1;
					stressedpos = i;
				}
			}
			// break;
		}

		return stressedpos;
	}
}
