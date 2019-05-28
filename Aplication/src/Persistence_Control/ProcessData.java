package Persistence_Control;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;


public class ProcessData {
	static final long serialVersionUID = 42L;
	public FileReader read;
	public BufferedReader rd;
	private String nombre_archivo;
	private String user_Name;
	private HashMap<String, Integer> hashtags;
	private HashMap<String, Integer> raiz_relevantesS;
	private HashMap<String, Integer> raiz_relevantesP;
	private HashMap<String, Integer> raiz_relevantesT;
	private HashMap<String, Integer> menciones;
	private  int[] Puntaje_Usuario;

	/**
	 * ProcessData - Metodo constructor de la clase
	 * @param nombre_archivo Un String indicando el nombre del archivo donde se guardo la informaci�n del usuario
	 * pos : primer_link queda inicializado
	 * pos : hashtags queda inicializado
	 * pos : raiz_relevante queda inicializado
	 * pos : nombre_archivo queda inicializado
	 */
	public ProcessData(String nombre_archivo, HashMap<String, Integer> raiz_relevantesS, HashMap<String, Integer> raiz_relevantesP, HashMap<String, Integer> raiz_relevantesT) throws IOException, URISyntaxException {
		super();
		hashtags = new HashMap<>();
		this.raiz_relevantesT = raiz_relevantesT;
		this.raiz_relevantesP = raiz_relevantesP;
		this.raiz_relevantesS = raiz_relevantesS;
		Puntaje_Usuario = new int[3];
		user_Name = "LOL";

		this.nombre_archivo = nombre_archivo;

		menciones = new HashMap<>();

		System.out.println("Intentandolo");

		run();
	}


	
	/**
	 * run - Metodo para proceasar la informacion del usuario y crear un nuevo Usuario
	 * pre : primer_link queda inicializado
	 * pre : hashtags queda inicializado
	 * pre : raiz_relevante queda inicializado
	 * pre : nombre_archivo queda inicializado
	 * pos : Se crea un nuevo Usuario con una lista de Tweet inicializada
	 * pos : Se a�aden objetos a primer_link
	 * pos : Se a�aden objetos a hashtags
	 */
	public void run() throws IOException, URISyntaxException {

			ArrayList<String> Texto_Bruto = new ArrayList<>();
			File fl = new File("."+nombre_archivo);
			FileReader read = new FileReader(fl);
			BufferedReader rd = new BufferedReader(read);
			String nombre_Usuario = "";
			String dato = "";
			String seguidores = "";
			String seguidos = "";
			
			int contador = 0;

			int forSure = 0;

			while(dato != null && forSure != 2) {

			    if(dato.trim().compareTo("Who to follow ·  Refresh · View all") == 0){
			        forSure++;
                }


				dato = dato.trim();

				if(dato.length()>=2 && dato.substring(0, 1).compareToIgnoreCase("@") == 0 && user_Name.equals("LOL")) {
					user_Name = dato;
					System.out.println("Nombre: " +dato);
				} else if(contador == 27) {
					seguidos = dato;
				}else if(contador == 30) {
					seguidores = dato;
				}
				
				dato = rd.readLine();
				Texto_Bruto.add(dato);
				contador++;
			}
	

			contador = 0;
			dato = Texto_Bruto.get(contador);
			
			Object a[] = new Object[2];
			
			int cantidad = 0;

			forSure = 0;

			while(dato != null && forSure!=2) {
                if(dato.trim().compareTo("Who to follow ·  Refresh · View all") == 0){
                    forSure++;
                }

				if(dato.compareTo("Verified account") == 0) {
					a = recopilarTweet(Texto_Bruto,contador+1,nombre_Usuario);
					int resul[] = (int[])a[0];
					contador = (int)a[1]; 
					Puntaje_Usuario[0] += resul[0];
					Puntaje_Usuario[1] += resul[1];
					Puntaje_Usuario[2] += resul[2];
					cantidad++;
				}
				contador++;
				dato = Texto_Bruto.get(contador);
			}



			rd.close();

			
	}
	

	


	
	/**
	 * recopilarTweet - Metodo para crear un Tweet
	 * @param t - El ArrayList de String con todo el texto plano del usurio separado por " "	t != null
	 * @param c - Un numero entero con el numero de linea que se est� procesando en Arraylist	c != null c >= 15
	 * @param n - Un String con el nombre del usuario n != null	n != ""
	 * @return fin - Un arreglo de tipo {@link Object} que contiene el numero de la ultima linea que se estaba procesando y el Tweet creado
	 * @throws URISyntaxException
	 */
	private Object[] recopilarTweet(ArrayList<String> t, int c, String n) throws URISyntaxException {
		String dato = t.get(c);
		int Cont_en_Tweet = 0;
		
		String fecha = "";
		String likes = "";
		String reTweets = "";
		boolean seguir = false;
		
		int Puntaje_Tweet[] = new int[3];
		
		while(!seguir && dato != null && dato.compareTo("Verified account") != 0 && dato.trim().compareTo("Who to follow �  Refresh � View all") != 0) {
			String herramienta[] = dato.split(" ");
			if(herramienta.length >= 6 && herramienta[herramienta.length-1].compareTo("message") == 0 && herramienta[herramienta.length-2].compareTo("Direct") == 0) {
				herramienta = t.get(c-1).split(" ");
				reTweets = herramienta[2];
				likes = herramienta[4];
				seguir = true;	
				//System.out.println("No agregado por Herramienta   //// "+dato+ " LOL "+ herramienta[5] + " LOL "+herramienta[9]);
			}else if(Cont_en_Tweet == 2) {
				fecha = dato;
				int puntos[] = identificarPalabra(dato);
				Puntaje_Tweet[0] += puntos[0];
				Puntaje_Tweet[1] += puntos[1];
				Puntaje_Tweet[2] += puntos[2];
			}else if(!seguir && dato.trim().compareTo("Who to follow �  Refresh � View all") != 0){
				for (int i = 0; i < herramienta.length; i++) {
					int puntos[] = identificarPalabra(herramienta[i]);
					Puntaje_Tweet[0] += puntos[0];
					Puntaje_Tweet[1] += puntos[1];
					Puntaje_Tweet[2] += puntos[2];
				}
			}else if(herramienta.length >= 2 && herramienta[herramienta.length-1].compareTo("Retweeted") == 0){
				seguir = true;
			}

			c++;
			Cont_en_Tweet++;
			dato = t.get(c);
		}
		

		
		Object fin[] = {Puntaje_Tweet,c-1};
		
		return fin;
	}
	
	/**
	 * identificarPalabra - Metodo para identificar que tipo de palabra se va a a�adir al tweet y su respectivo puntaje
	 * @param s - Es un String con la palabra a identificar
	 * @return salida un arreglo de enteros 
	 * @throws URISyntaxException
	 */
	private int[] identificarPalabra(String s) throws URISyntaxException {
		int salida[] = new int[3];
		salida[0] = 0;
		salida[1] = 0;
		salida[2] = 0;
		if(s.length()>=2 && s.substring(0, 1).compareToIgnoreCase("@") == 0) {

			menciones.put(s.substring(1,s.length()),0);
		}else if(s.length()>=1 && s.substring(0, 1).compareToIgnoreCase("#") == 0){
			if(hashtags.containsKey(s)) {
				hashtags.replace(s,hashtags.get(s)+1);
			}else {
				hashtags.put(s,0);
			}
		}else {
			s = s.toLowerCase();

			if(raiz_relevantesT.containsKey(s)){
				salida[0] = 1;
			}

			if(raiz_relevantesS.containsKey(s)){
				salida[1] = 1;
			}

			if(raiz_relevantesP.containsKey(s)){
				salida[2] = 1;
			}

		}                
		
		return salida;
	}

	public HashMap<String, Integer> getHashtags() {
		return hashtags;
	}

	public HashMap<String, Integer> getMenciones() {
		return menciones;
	}

	public int[] getPuntaje_Usuario() {
		return Puntaje_Usuario;
	}

	public String getUser_Name() {
		return user_Name;
	}


}
