package grammarpt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

public class ReadData {
	// assuming a xml dataset
	public List<Essay> read(String dataDir) throws SAXException, IOException {
		File[] files = new File(dataDir).listFiles();
		List<Essay> essayList = new ArrayList<Essay>();

		for (File f : files) {
			if (f.isDirectory()) {
				// each directory contains essays from one topic
				List<Essay> topicEssays = processdir(f);
				essayList.addAll(topicEssays);
				// break;
			}
		}
		return essayList;
	}

	public List<Essay> processdir(File dirTopic) throws SAXException, IOException {
		File[] xmlFiles = new File(dirTopic.getAbsolutePath() + "/xml/").listFiles();
		List<Essay> essayList = new ArrayList<Essay>();
		
		// assuming that in this directory there are xml files to read
		for (File f : xmlFiles) {
			String ext = FilenameUtils.getExtension(f.getAbsolutePath());
			if (ext.equals("xml")) {
				File xmlfile = new File(f.getAbsolutePath());
				Essay e = new Essay(xmlfile);
				essayList.add(e);
			}
		}
		return essayList;
	}

}
