package data.lists;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListsController {
	//Getters
	public int getNumber(String path) throws IOException {
		File f = new File (path + "aacont.txt");
		FileReader fr = new FileReader (f);
		BufferedReader br = new BufferedReader(fr);
		String num = br.readLine();
		br.close();
		return Integer.parseInt(num);
	}
	
	public String[] getAll(String path) throws IOException {
		FileReader fr = null;
		try {
			int n = getNumber(path);
			if (n == 0) {
				return new String[n];
			} else {
				String[] all = new String[n];
				File f = new File(path + "aaall.txt");
				fr = new FileReader (f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				int i = 0;
				while ((line = br.readLine()) != null) {
					all[i] = line;
					++i;
				}
				return all;
			}
		}
		catch (FileNotFoundException e) {
			return null;
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
	}
	
	public boolean check(String path, String name) throws IOException {
		name = name.trim().replace(" ", "").toLowerCase();
		File f = new File (path + name + ".txt");
		return !(f.exists());
	}
	
	public boolean addNew(String path, String name, String info) throws IOException {
		//Increase number of students
		String newLast = (Integer.toString(getNumber(path) + 1));
		File f = new File (path + "aacont.txt");
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(newLast);
		bw.close();
		//Create student's file
		f = new File (path + name.trim().replace(" ", "").toLowerCase() + ".txt");
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);
		bw.write(info);
		bw.close();
		//Add student to the list
		FileReader fr = null;
		try {
			f = new File(path + "aaall.txt");
			fr = new FileReader (f);
			BufferedReader br = new BufferedReader(fr);
			String line, total = "";
			while ((line = br.readLine()) != null) {
				total += line + "\n";
			}
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			bw.write(total + name);
			br.close();
			return true;
		}
		catch (FileNotFoundException e) {
			return false;
		}
		finally {
			if (fr != null) {
				fr.close();
				bw.close();
			}
		}
	}
	
	public String getInfo(String path, String name) throws IOException {
		File f = new File (path + name.trim().replace(" ", "").toLowerCase() + ".txt");
		FileReader fr = new FileReader (f);
		BufferedReader br = new BufferedReader(fr);
		String info = "", line;
		while ((line = br.readLine()) != null) {
			info += line + "\n";
		}
		fr.close();
		br.close();
		return info;
	}
	
	public boolean remove(String path, String name) throws IOException {
		//Remove File
		File f = new File (path + name.trim().replace(" ", "").toLowerCase() + ".txt");
		if (f.delete()) {
			//Decrease number of clients
			String newLast = (Integer.toString(getNumber(path) - 1));
			File f2 = new File (path + "aacont.txt");
			FileWriter fw2 = new FileWriter(f2);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw2.write(newLast);
			bw2.close();
			//Remove name from list
			File f3 = new File(path + "aaall.txt");
			FileReader fr3 = new FileReader (f3);
			BufferedReader br3 = new BufferedReader(fr3);
			String line, total = "";
			while ((line = br3.readLine()) != null) {
				if (!(line.trim().replace(" ", "").toLowerCase().equals(name.trim().replace(" ", "").toLowerCase()))) {
					total += line + "\n";
				}
			}
			FileWriter fw3 = new FileWriter(f3);
			BufferedWriter bw3 = new BufferedWriter(fw3);
			bw3.write(total);
			br3.close();
			bw3.close();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean saveFile(String path, String name, File f) throws IOException {
    	String[] aux = f.getPath().replaceAll("%20", " ").split("\\.");
    	String ext = aux[aux.length - 1];
    	ext = "." + ext;
    	String dest = path + "aaFiles/" + (name + ext).trim().replace(" ", "").toLowerCase();
    	byte[] file = new byte[(int)f.length()];
        FileInputStream fileIn;
        FileOutputStream fileOut;
        fileIn = new FileInputStream(f.getPath().replaceAll("%20", " "));
        fileIn.read(file);
        fileOut = new FileOutputStream(dest);
        fileOut.write(file);
        fileIn.close();
        fileOut.close();
        return true;
	}
	
	public boolean openFile(String path, File file) throws IOException {
		if (file.exists()) {
			Desktop.getDesktop().open(file);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeFile(String path, File file) {
		if (file.exists()) {
			return file.delete();
		} else {
			return false;
		}
	}
}
