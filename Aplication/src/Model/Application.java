package Model;

import Persistence_Control.Hilo_ProcesarDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Application {

	private HashMap<String, Integer> raiz_relevantesS;
	private HashMap<String, Integer> raiz_relevantesP;
	private HashMap<String, Integer> raiz_relevantesT;

	public Application() {
		raiz_relevantesS = new HashMap<>();
		raiz_relevantesP = new HashMap<>();
		raiz_relevantesT = new HashMap<>();

		try {
			loadEspecialWords();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadEspecialWords() throws IOException {

		String names[] = {"./Persistence/Dictionaries/Politics_Dictionary","./Persistence/Dictionaries/Tecnology_Dictionary","./Persistence/Dictionaries/Sports_Dictionary"};

		loadType(names[0],raiz_relevantesP);

		loadType(names[1],raiz_relevantesT);

		loadType(names[2],raiz_relevantesS);
	}

	private void loadType(String name , HashMap<String,Integer> selected) throws IOException {
		File fl = new File(name);
		FileReader read = new FileReader(fl);
		BufferedReader rd = new BufferedReader(read);
		String dato = rd.readLine();

		while(dato != null) {
			String info[] = dato.split(",");

			for(int I =0 ; I<info.length ; I++){
				selected.put(info[I].toLowerCase(),0);
			}

			dato = rd.readLine();
		}


	}

	public void loadUsers(){

	}

	public void registerUser(){

	}


}

