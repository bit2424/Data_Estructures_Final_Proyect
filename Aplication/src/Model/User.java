package Model;

import Estructures.linear_Structures.HashMap;

public class User {
	
	private String name;
	//[0] ciencia, [1] deprte, [2] Tecnologia
	private int[] points;
	private HashMap<String, Integer> hashtag;
	private HashMap<String, Integer> at;
	
	public User(String name, int c, int d, int t,HashMap hashtag,HashMap at){
		this.name = name;
		points = new int[3];
		points[0] = c;
		points[1] = d;
		points[2] = t;
		this.hashtag = hashtag;
		this.at = at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getPoints() {
		return points;
	}

	public void setPoints(int[] points) {
		this.points = points;
	}

	public HashMap<String, Integer> getHashtag() {
		return hashtag;
	}

	public void setHashtag(HashMap<String, Integer> hashtag) {
		this.hashtag = hashtag;
	}

	public HashMap<String, Integer> getAt() {
		return at;
	}

	public void setAt(HashMap<String, Integer> at) {
		this.at = at;
	}

}
