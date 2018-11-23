package facebook_api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;

public class printWriter {
	
	private PrintWriter print;


	public void printOnFile(DefaultListModel<String> dlm,File file) throws FileNotFoundException {
		
		try {
			print = new PrintWriter(file);
			for (int i = 0; i < dlm.size(); i++) {
				print.write(dlm.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
