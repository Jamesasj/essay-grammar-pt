package grammarpt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.xml.sax.SAXException;

public class extract {
	public static void main(String[] args) throws SAXException, IOException {

		String dir_data = "/Users/evelin.amorim/Documents/UFMG/aes/data/"; // args[0];
		String fts_file = "/Users/evelin.amorim/Documents/UFMG/aes/baseftrs.csv"; // args[1];
		ReadData rd = new ReadData();

		long startTime = System.currentTimeMillis();

		
		List<Essay> essayList = rd.read(dir_data); // Para cada objeto essay é instanciado uma classe cogroo

		long endTime = System.currentTimeMillis();
		long milliseconds = (endTime - startTime);
		int seconds = (int) (milliseconds / 1000) % 60;
		System.out.println("Reading data took " + seconds + " seconds");

		// ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));
		// //não precisa
		// Analyzer cogroo = factory.createPipe(); // não precisa

		FileWriter fw = new FileWriter(fts_file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);

		int i = 0;

		// pq o tempo de extracao de features vai aumentando até voltar a diminuir?
		// o que esta levando tanto tempo?
		/*
		 * James : a instancia execiva das classes no cogroo na classe Essay pode estar
		 * ocupando a memória execiva em uma lista. Talvez uma instancia estatica reduza
		 * o tempo de chamada das funcionalidades do cogroo.
		 */
		for (Essay e : essayList) {
			startTime = System.currentTimeMillis();
			if (i == 0) {
				e.writeFeatures(out, true);
			} else {
				e.writeFeatures(out, false);
			}
			endTime = System.currentTimeMillis();
			milliseconds = (endTime - startTime);
			seconds = (int) (milliseconds / 1000) % 60;
			System.out.println(i + " Extracting/writing data took " + seconds + " seconds");
			System.out.println(e.getfileName());
			i++;
		}

	}
}
