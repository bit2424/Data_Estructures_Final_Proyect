package Structures.linear_Structures;

import java.io.*;
import java.util.HashSet;

public class HashMapSuma {
	
	public static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		String datos1 = rd.readLine();
		int casos = Integer.parseInt(datos1);
		
		int arr1[];
		int arr2[];
		
		for (int i = 0; i < casos; i++) {
			String datos4 = rd.readLine();
			String datos2 = rd.readLine();
			String datos3 = rd.readLine();
			
			int specialN = Integer.parseInt(datos4);
			
			String raw1[] = datos2.split(" ");
			String raw2[] = datos3.split(" ");
			arr1 = new int[raw1.length];
			arr2 = new int [raw2.length];
			
			HashSet<Integer> nums = new HashSet<>();
			HashMap<String, Integer> lol = new HashMap<>();
			
			
			for (int j = 0; j < raw1.length; j++) {
				arr1[j] = Integer.parseInt(raw1[j]);
				nums.add(-arr1[j]+specialN);
			}
			
			for (int j = 0; j < raw2.length; j++) {
				arr2[j] = Integer.parseInt(raw2[j]);
				 
				if(nums.contains(arr2[j])) {
					System.out.println(arr2[j] + " , "+ ((specialN)-arr2[j]));
				}
			}
			
			
		}

	}

}
