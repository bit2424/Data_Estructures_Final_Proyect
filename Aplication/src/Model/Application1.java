package Model;

import Persistence_Control.ProcessData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Estructures.Graphs.AdjacencyListGraph;

public class Application1 {

 	private HashMap<String, Integer> raiz_relevantesS;
	private HashMap<String, Integer> raiz_relevantesP;
	private HashMap<String, Integer> raiz_relevantesT;
	private ProcessData hilo;
	private AdjacencyListGraph<User, Integer> graphHashtag;
	private AdjacencyListGraph<User, Integer> graphAt;
	public Application1() {
		raiz_relevantesS = new HashMap<>();
		raiz_relevantesP = new HashMap<>();
		raiz_relevantesT = new HashMap<>();
		graphAt = new AdjacencyListGraph<>(false, true);
		graphHashtag = new AdjacencyListGraph<>(false, true);

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
		hilo = new ProcessData("./Persistence/Users/nuevo", raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
		System.out.println(hilo.getUser_Name()+"  "+  hilo.getPuntaje_Usuario()[0] +" Tecnologia   "+ hilo.getPuntaje_Usuario()[1]+"  Deporte   "
				+  hilo.getPuntaje_Usuario()[2]+"   Politica   "+  hilo.getHashtags().size()+" Cantidad de  #      "+ hilo.getMenciones().size()+" Cantidad de  @");
		graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
		graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
		System.out.println(graphHashtag.getNumberOfVertices());
		joinEdges(graphHashtag.getNumberOfVertices()-1);
	}

	private void joinEdges(int index) {
		for (int i = 0; i < index; i++) {
			int relationHash = 0;
			int relationAt = 0;
			User newUser = graphHashtag.getVerticesL().get(index).getValue();
			User aux = graphHashtag.getVerticesL().get(i).getValue();
			for(String key : newUser.getAt().keySet()) {
				if(aux.getAt().containsKey(key))
					relationAt++;
			}
			for(String key : newUser.getHashtag().keySet()) {
				if(aux.getHashtag().containsKey(key))
					relationHash++;
			}
			if(relationHash > 0 )
				graphHashtag.insertEdge(index, i, relationHash);
			if(relationAt>0)
				graphAt.insertEdge(index, i, relationAt);
				
			System.out.println(relationAt+" Aristas @  peso --- "+ relationHash+ "Aristas# peso");
		}
	}

	public AdjacencyListGraph<User, Integer> getGraphHashtag() {
		return graphHashtag;
	}

	public AdjacencyListGraph<User, Integer> getGraphAt() {
		return graphAt;
	}
	


}

