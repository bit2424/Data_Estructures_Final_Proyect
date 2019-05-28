package Model;

import Structures.Graphs.EdgeL;
import Structures.Graphs.VertexL;
import Structures.Graphs.VertexM;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;
import Structures.trees.RBTree;
import Persistence_Control.ProcessData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import Structures.Graphs.AdjacencyListGraph;

public class Application1 {

    private HashMap<String, Integer> raiz_relevantesS;
    private HashMap<String, Integer> raiz_relevantesP;
    private HashMap<String, Integer> raiz_relevantesT;
    private ProcessData hilo;
    private AdjacencyListGraph<User, Integer> graphHashtag;
    private AdjacencyListGraph<User, Integer> graphAt;
    private AdjacencyListGraph<User, Double> graphRelations;

    public Application1() {
        raiz_relevantesS = new HashMap<>();
        raiz_relevantesP = new HashMap<>();
        raiz_relevantesT = new HashMap<>();
        graphAt = new AdjacencyListGraph<>(false, true);
        graphHashtag = new AdjacencyListGraph<>(false, true);
        graphRelations = new AdjacencyListGraph<>(false, true);

        try {
            loadEspecialWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEspecialWords() throws IOException {
        try {
            String names[] = {"./Persistence/Dictionaries/Politics_Dictionary", "./Persistence/Dictionaries/Tecnology_Dictionary", "./Persistence/Dictionaries/Sports_Dictionary"};

            loadType(names[0], raiz_relevantesP);

            loadType(names[1], raiz_relevantesT);

            loadType(names[2], raiz_relevantesS);


        } catch (Exception e) {

            //Tu version

            e.printStackTrace();

            String names[] = {"./Aplication/Persistence/Dictionaries/Politics_Dictionary", "./Aplication/Persistence/Dictionaries/Tecnology_Dictionary", "./Aplication/Persistence/Dictionaries/Sports_Dictionary"};

            loadType(names[0], raiz_relevantesP);

            loadType(names[1], raiz_relevantesT);

            loadType(names[2], raiz_relevantesS);
        }

    }

    private void loadType(String name, HashMap<String, Integer> selected) throws IOException {
        File fl = new File(name);
        FileReader read = new FileReader(fl);
        BufferedReader rd = new BufferedReader(read);
        String dato = rd.readLine();

        while (dato != null) {
            System.out.println("Cargando   Datos  "+ dato);
            String info[] = dato.split(",");
            for (int I = 0; I < info.length; I++) {
                selected.put(info[I].toLowerCase(), 0);
            }

            dato = rd.readLine();
        }


    }

    public void loadUsers() {

    }

    public void registerUser() {

        try {
            hilo = new ProcessData("/Persistence/Users/nuevo", raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
            System.out.println(hilo.getUser_Name() + "  " + hilo.getPuntaje_Usuario()[0] + " Tecnologia   " + hilo.getPuntaje_Usuario()[1] + "  Deporte   "
                    + hilo.getPuntaje_Usuario()[2] + "   Politica   " + hilo.getHashtags().size() + " Cantidad de  #      " + hilo.getMenciones().size() + "  Cantidad de  @");
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            System.out.println(graphHashtag.getNumberOfVertices());
            joinEdges(graphHashtag.getNumberOfVertices() - 1);
        } catch (Exception e) {
            try {
                hilo = new ProcessData("/Aplication/Persistence/Users/nuevo", raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            System.out.println(hilo.getUser_Name() + "  " + hilo.getPuntaje_Usuario()[0] + " Tecnologia   " + hilo.getPuntaje_Usuario()[1] + "  Deporte   "
                    + hilo.getPuntaje_Usuario()[2] + "   Politica   " + hilo.getHashtags().size() + " Cantidad de  #      " + hilo.getMenciones().size() + "  Cantidad de  @");
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            System.out.println(graphHashtag.getNumberOfVertices());
            joinEdges(graphHashtag.getNumberOfVertices() - 1);
        }


    }

    private void joinEdges(int index) {
        for (int i = 0; i < index; i++) {
            int relationHash = 0;
            int relationAt = 0;
            User newUser = graphHashtag.getVerticesL().get(index).getValue();
            User aux = graphHashtag.getVerticesL().get(i).getValue();
            for (String key : newUser.getAt().keySet()) {
                if (aux.getAt().containsKey(key)) {
                    relationAt++;
                    System.out.println(key + " We found this in common");
                }
            }
            for (String key : newUser.getHashtag().keySet()) {
                if (aux.getHashtag().containsKey(key)) {
                    relationHash++;
                    System.out.println(key + " We found this in common");
                }
            }

            if (relationHash > 0) {
                graphHashtag.insertEdge(index, i, relationHash);
            }

            if (relationAt > 0) {
                graphAt.insertEdge(index, i, relationAt);
            }

            if (relationHash > 0 || relationAt > 0) {
                double weight = (relationAt + relationHash);
                double a = Math.abs(newUser.getPoints()[0] - aux.getPoints()[0]) + Math.abs(newUser.getPoints()[1] - aux.getPoints()[1]) + Math.abs(newUser.getPoints()[2] - aux.getPoints()[2]);
                weight = weight / a;
                graphRelations.insertEdge(index, i, weight);
            }

            System.out.println(relationAt + " Aristas @  peso --- " + relationHash + "  Aristas # peso");
        }
    }

    public AdjacencyListGraph<User, Integer> getGraphHashtag() {
        return graphHashtag;
    }

    public AdjacencyListGraph<User, Integer> getGraphAt() {

        return graphAt;
    }

    //Requerimiento 6
	public User getNextProbableRelation(User user){
		ArrayList<VertexL<User,Double>> aux = graphRelations.getVerticesL();
		HashMap<User,ArrayList<VertexL<User,Double>>> store = new HashMap<>();
		int i = 0;
		for(int I = 0;I<aux.size();I++){
			store.put(aux.get(I).getValue(),aux.get(I).getAdjacencyList());
			if(aux.get(I).getValue().equals(user)){
				i = I;
			}
		}

		ArrayList<VertexL<User,Double>> close =  aux.get(i).getAdjacencyList();
		ArrayList<VertexL<User,Double>> closeOnes = new ArrayList<>();
		RBTree<Double,User> keeper  = new RBTree<>();

		for(int K = 0; K < close.size();K++){
			closeOnes = store.get(close.get(K).getValue());

			for(int M = 0; M<closeOnes.size(); M++){
				Double val = graphRelations.getEdges(K,M).get(0).getWeight();
				keeper.insert(val,closeOnes.get(M).getValue());
			}
		}

		User result = keeper.search(keeper.getMax());

		return result;
	}

    //Requerimiento 7
    public ArrayList<User> getDifusionGroup(User uSend, User uRecive) {
        ArrayList<VertexL<User, Double>> aux = graphRelations.getVerticesL();
        int iR = 0;
        int iS = 0;

        for (int I = 0; I < aux.size(); I++) {
            if (aux.get(I).getValue().equals(uSend)) {
                iR = I;
            }

            if (aux.get(I).getValue().equals(uRecive)) {
                iS = I;
            }
        }

        int[] parents = (int[]) graphRelations.Dijsktra(iS)[1];

        ArrayList<User> result = new ArrayList<>();
        result.add(uRecive);
        int actual_parent = parents[iR];

        while (actual_parent != -1) {
            result.add(aux.get(actual_parent).getValue());
            actual_parent = parents[actual_parent];
        }

        if (result.size() == 1) {
            return null;
        } else {
            return result;
        }
    }


    //Requerimiento 5



	public HashMap<User,Integer> usersUpScorePolitic(int score,User ref){
        int search = graphRelations.getVerticesL().indexOf(ref);

        try {
            graphRelations.BFS(search);
        } catch (UnderflowException e) {
            e.printStackTrace();
        }

        ArrayList<VertexL<User,Double>> aux = graphRelations.getVerticesL();

        HashMap<User,Integer> result = new HashMap<>();


        for(int I = 0; I<aux.size();I++){

            if(aux.get(I).getValue().getPoints()[2] > score) {
                result.put(aux.get(I).getValue(), (int)aux.get(I).getDistance());
            }
        }

        return result;
	}

	public HashMap<User,Integer> usersUpScoreSports(int score,User ref){
        int search = graphRelations.getVerticesL().indexOf(ref);

        try {
            graphRelations.BFS(search);
        } catch (UnderflowException e) {
            e.printStackTrace();
        }

        ArrayList<VertexL<User,Double>> aux = graphRelations.getVerticesL();

        HashMap<User,Integer> result = new HashMap<>();

        for(int I = 0; I<aux.size();I++){
            if(aux.get(I).getValue().getPoints()[1] > score) {
                result.put(aux.get(I).getValue(), (int)aux.get(I).getDistance());
            }
        }

        return result;
	}

    public HashMap<User, Integer> usersUpScoreTecnology(int score, User ref) {
        int search = graphRelations.getVerticesL().indexOf(ref);

        try {
            graphRelations.BFS(search);

        } catch (UnderflowException e) {
            e.printStackTrace();
        }

        ArrayList<VertexL<User, Double>> aux = graphRelations.getVerticesL();

        HashMap<User, Integer> result = new HashMap<>();

        for (int I = 0; I < aux.size(); I++) {
            if (aux.get(I).getValue().getPoints()[0] > score) {
                result.put(aux.get(I).getValue(), (int) aux.get(I).getDistance());
            }
        }

        return result;
    }


}

