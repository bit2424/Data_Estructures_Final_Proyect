package Model;

import Structures.Graphs.*;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;
import Structures.trees.RBTree;
import Persistence_Control.ProcessData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

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
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            joinEdges(graphHashtag.getNumberOfVertices() - 1);
        } catch (Exception e) {
            try {
                hilo = new ProcessData("/Aplication/Persistence/Users/nuevo", raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
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
                }
            }
            for (String key : newUser.getHashtag().keySet()) {
                if (aux.getHashtag().containsKey(key)) {
                    relationHash++;
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
            User a = aux.get(I).getValue();
            if(a.compareTo(user) == 0){
                i = I;
            }
        }

        ArrayList<VertexL<User,Double>> close =  aux.get(i).getAdjacencyList();
        ArrayList<VertexL<User,Double>> closeOnes = new ArrayList<>();
        RBTree<Double,User> keeper  = new RBTree<>();

        Double val = 0d;
        for(int K = 0; K < close.size();K++){
            closeOnes = store.get(close.get(K).getValue());

            for(int M = 0; M<closeOnes.size(); M++){
                if(graphRelations.getEdges(K,M).size()>0){
                    val = graphRelations.getEdges(K,M).get(0).getWeight();
                    keeper.insert(val,closeOnes.get(M).getValue());
                }else{
                    val = -1d;
                    keeper.insert(val,closeOnes.get(M).getValue());
                }
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
            if (aux.get(I).getValue().compareTo(uRecive) == 0) {
                iR = I;
            }

            if (aux.get(I).getValue().compareTo(uSend) == 0) {
                iS = I;
            }
        }

        int[] parents = (int[]) graphRelations.Dijsktra(iS)[1];

        ArrayList<User> result = new ArrayList<>();
        Stack<User> temp = new Stack<>();
        int actual_parent = parents[iR];

        temp.push(uRecive);

        while (actual_parent != -1) {
            temp.push(aux.get(actual_parent).getValue());
            actual_parent = parents[actual_parent];
        }

        int a = temp.size();

        for (int I = 0;I<a;I++){
            result.add(temp.pop());
        }

        if (result.size() == 1) {
            return null;
        } else {
            return result;
        }
    }


    //Requerimiento 5



    public HashMap<User,Integer> usersUpScore(int score,User ref,int category){
        ArrayList<VertexL<User,Double>> aux = graphRelations.getVerticesL();
        int i = 0;
        for(int I = 0;I<aux.size();I++){
            if(aux.get(I).getValue().compareTo(ref) == 0){
                i = I;
            }
        }
        try {
            graphRelations.BFS(i);
        } catch (UnderflowException e) {
            e.printStackTrace();
        }


        HashMap<User,Integer> result = new HashMap<>();


        for(int I = 0; I<aux.size();I++){

            if(aux.get(I).getValue().getPoints()[category] >= score) {
                result.put(aux.get(I).getValue(), (int)aux.get(I).getDistance());
            }
        }

        return result;
    }





    //Requerimiento 4
    public ArrayList<ArrayList<User>> getArCoincidentUsers(){
        ArrayList<ArrayList<Integer>> ref = graphAt.DFS();
        ArrayList<VertexL<User,Integer>> refUsers = graphAt.getVerticesL();
        ArrayList<ArrayList<User>> outputUsers = new ArrayList<>();

        for(int I = 0;I<ref.size();I++){
            outputUsers.add(new ArrayList<>());
            for(int K = 0; K<ref.get(I).size(); K++){
                outputUsers.get(I).add(refUsers.get(ref.get(I).get(K)).getValue());
            }
        }

        return outputUsers;
    }

    //Requerimiento 3
    public ArrayList<ArrayList<User>> getHashCoincidentUsers(){
        ArrayList<ArrayList<Integer>> ref = graphHashtag.DFS();
        ArrayList<VertexL<User,Integer>> refUsers = graphHashtag.getVerticesL();
        ArrayList<ArrayList<User>> outputUsers = new ArrayList<>();

        for(int I = 0;I<ref.size();I++){
            outputUsers.add(new ArrayList<>());
            for(int K = 0; K<ref.get(I).size(); K++){
                outputUsers.get(I).add(refUsers.get(ref.get(I).get(K)).getValue());
            }
        }

        return outputUsers;
    }

    //Requrimiento 2
    //Retorno el usuario y su puntaje
    public ArrayList<AdjacencyMatrixGraph.pair> getClasificatedUsers(int category){
        ArrayList<VertexL<User,Double>> refUsers = graphRelations.getVerticesL();
        ArrayList<AdjacencyMatrixGraph.pair> outputUsers = new ArrayList<>();
        HashMap<User,Integer> result = new HashMap<>();


        for(int I = 0;I<refUsers.size();I++){
            outputUsers.add(new AdjacencyMatrixGraph.pair(I,refUsers.get(I).getValue().getPoints()[category]));
            System.out.println(refUsers.get(I).getValue().getName());
        }

        Collections.sort(outputUsers);


        return outputUsers;
    }


    public void registerData(String link) throws IOException, URISyntaxException {
        try {
            hilo = new ProcessData(link, raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            joinEdges(graphHashtag.getNumberOfVertices() - 1);
        } catch (Exception e) {
            try {
                hilo = new ProcessData("/Aplication"+link, raiz_relevantesS, raiz_relevantesP, raiz_relevantesT);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            graphHashtag.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphAt.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            graphRelations.insertVertex(new User(hilo.getUser_Name(), hilo.getPuntaje_Usuario(), hilo.getHashtags(), hilo.getMenciones()));
            joinEdges(graphHashtag.getNumberOfVertices() - 1);
        }
    }


}