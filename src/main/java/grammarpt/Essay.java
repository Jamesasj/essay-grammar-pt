package grammarpt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.cogroo.analyzer.Analyzer;
import org.cogroo.analyzer.ComponentFactory;
import org.cogroo.checker.CheckDocument;
import org.cogroo.checker.GrammarChecker;
import org.cogroo.entities.Mistake;
import org.cogroo.text.Sentence;
import org.cogroo.text.Token;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.giullianomorroni.jcorretor.CorretorOrtografico;
import com.giullianomorroni.jcorretor.SugestaoOrtografia;

public class Essay {

	private String bodyText;
	private float finalGrade;
	private String generalComment;
	private String specificComments;
	private List<String> ftrsNames;
	private String fileName;
	private tools toolObj = new tools();
	private HashMap skills = new HashMap();
	private ComponentFactory factory;

	public enum SkillTypes {
		UNDERSTANDING, SELECTINGINFO, SHOWKNOW, SOLUTION, FORMAL
	}

	Essay(File f) throws SAXException, IOException {
		DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
		factory = ComponentFactory.create(new Locale("pt", "BR"));

		try {
			this.fileName = f.getAbsolutePath();
			ftrsNames = new ArrayList<String>();
			DocumentBuilder docBuilder = docFac.newDocumentBuilder();
			Document document = docBuilder.parse(f);
			NodeList body = document.getElementsByTagName("body").item(0).getChildNodes();
			String bodyText = "";

			for (int k = 0; k < body.getLength(); k++) {
				String tagName = body.item(k).getNodeName();
				String textContent = body.item(k).getTextContent().trim();
				if (tagName == "wrong" || tagName == "#text") {
					bodyText = bodyText.concat(" ");
					bodyText = bodyText.concat(textContent);
				}
			}

			this.bodyText = bodyText;
			String finalgrade = document.getElementsByTagName("finalgrade").item(0).getTextContent();
			this.finalGrade = Float.parseFloat(finalgrade.replace(',', '.'));

			NodeList skillList = document.getElementsByTagName("skills").item(0).getChildNodes();
			for (int k = 0; k < skillList.getLength(); k++) {
				Node nameFirst = skillList.item(k).getFirstChild();
				Node nameLast = skillList.item(k).getLastChild();
				float gradeSkill = 0;
				if (nameLast != null) {
					String gradeSkillStr = nameLast.getTextContent().trim();
					if (!gradeSkillStr.isEmpty()) {
						gradeSkill = Float.parseFloat(nameLast.getTextContent().replace(',', '.'));
					} else
						gradeSkill = 0;
				}
				if (nameFirst != null) {
					String desc = nameFirst.getTextContent();
					if (desc.contains("Selecionar")) {
						skills.put(SkillTypes.SELECTINGINFO, new Double(gradeSkill));
					} else if (desc.contains("Demonstrar c")) {
						skills.put(SkillTypes.SHOWKNOW, new Double(gradeSkill));
					} else if (desc.contains("Compreender")) {
						skills.put(SkillTypes.UNDERSTANDING, new Double(gradeSkill));
					} else if (desc.contains("Elaborar")) {
						skills.put(SkillTypes.SOLUTION, new Double(gradeSkill));
					} else if (desc.contains("Demonstrar d")) {
						skills.put(SkillTypes.FORMAL, new Double(gradeSkill));
					}
				}

			}

			this.generalComment = document.getElementsByTagName("generalcomment").item(0).getTextContent();
			this.specificComments = document.getElementsByTagName("specificaspects").item(0).getTextContent();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public int numSyllable(List<Token> lstTokens) {
		int nSyllable = 0;

		for (Token t : lstTokens) {
			String[] lsttokens = t.getLexeme().split("_");
			for (String w : lsttokens) {

				ArrayList<String> s = toolObj.word2syllables(w);
				nSyllable = nSyllable + s.size();
			}

		}
		return nSyllable;
	}

	public int fleschScore(float syllPerWord, float wordsPerSent) {
		int fs = 0;
		double score = 206.835 - (84.6 * syllPerWord) - (1.015 * wordsPerSent) + 42;

		return fs;
	}

	public String extractFeatures() throws IllegalArgumentException, IOException {
		/*
		 * O excesso das chamadas dessas classes é estranho Tentar reescrever este
		 * método.
		 */

		Analyzer c = factory.createPipe();
		String ftrs = "";
		GrammarChecker gc = new GrammarChecker(c);
		CheckDocument doc = new CheckDocument(this.bodyText);

		gc.analyze(doc);

		// as features of mistakes (remove space errors): number of grammar mistakes,
		// number of mistakes per number of words
		// number of syllabes per word
		// number of words per sentence

		List<Mistake> mlist = doc.getMistakes();

		// System.out.println(fileName);
		int longSentence = 0;
		int ntokens = 0;
		int wordlenghtSum = 0;
		float avgsyll = 0;

		List<Sentence> lstSentence = doc.getSentences();
		for (Sentence s : lstSentence) {
			List<Token> lstTokens = s.getTokens();
			avgsyll = avgsyll + numSyllable(lstTokens);
			ntokens = ntokens + lstTokens.size();
			for (Token t : lstTokens) {
				wordlenghtSum = wordlenghtSum + t.toString().length();
			}
			int sentenceSize = s.getText().length();
			if (sentenceSize > 70) {
				longSentence = longSentence + 1;
			}
		}
		System.out.println("-->" + ntokens + " " + lstSentence.size());

		int nErrors = mlist.size();
		float nErrorsTokens = 0;
		float tokensperSentence = 0;
		if (ntokens != 0) {
			nErrorsTokens = nErrors / ntokens;
			avgsyll = avgsyll / ntokens;
			tokensperSentence = lstSentence.size() / ntokens;
		}

		// System.out.println("--> " + avgsyll);
		ftrs = ftrs + Integer.toString(nErrors) + ","; // 1
		ftrsNames.add("nErrors");
		ftrs = ftrs + Float.toString(nErrorsTokens) + ","; // 2
		ftrsNames.add("nErrorsTokens");
		ftrs = ftrs + Float.toString(ntokens) + ","; // 3
		ftrsNames.add("ntokens");

		// Flesh score
		double fs = fleschScore(avgsyll, tokensperSentence);
		ftrs = ftrs + Double.toString(fs) + ","; // 4
		ftrsNames.add("fleshscore");

		// average word length
		float avgWordLength = 0;
		if (ntokens != 0) {
			avgWordLength = wordlenghtSum / ntokens;
		}
		ftrs = ftrs + Float.toString(avgWordLength); // 5
		ftrsNames.add("avgWordLength");

		long startTime = System.currentTimeMillis();
		// number of spelling mistakes
		// number of spelling mistakes per number of words
		CorretorOrtografico corretor = new CorretorOrtografico();
		SugestaoOrtografia correcao = corretor.corrigir(this.bodyText);
		List<String> lstCorrecao = correcao.getTextoSugerido();

		long endTime = System.currentTimeMillis();
		long milliseconds = (endTime - startTime);
		int seconds = (int) (milliseconds / 1000) % 60;
		// System.out.println(this.fileName);
		// System.out.println("Spelling correction sentences " + lstSentence.size() + "
		// data took " + seconds + " seconds");

		int nSpelling = lstCorrecao.size();

		int nSpellingTokens = nSpelling / ntokens;

		ftrs = ftrs + Float.toString(nSpelling) + ","; // 6
		ftrsNames.add("nSpelling");
		ftrs = ftrs + Float.toString(nSpellingTokens) + ","; // 7
		ftrsNames.add("nSpellingTokens");

		// numero de sentencas com mais de 70 caracteres (sentencas longas)
		ftrs = ftrs + Float.toString(longSentence); // 8
		ftrsNames.add("nLongSentence");

		return ftrs;
	}

	public void writeHeader(PrintWriter out) {
		String header = "";
		int i;
		for (i = 0; i < ftrsNames.size() - 1; i++) {
			header = header + ftrsNames.get(i) + ",";
		}
		header = header + ftrsNames.get(i);

		out.println(header);
	}

	public void writeFeatures(PrintWriter out, Boolean h) throws IllegalArgumentException, IOException {
		String ftrs = this.extractFeatures();

		if (h) {
			writeHeader(out);
		}

		out.println(ftrs);
	}

	String getfileName() {
		return fileName;
	}

	List<String> get_ftrsNames() {
		return ftrsNames;
	}

}
