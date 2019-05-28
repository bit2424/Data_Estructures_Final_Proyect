package Model;

import java.util.HashMap;

public class User implements Comparable<User>{
	
	private String name;
	//[0] Tecnology [1] Sports [2] Politics
	private int[] points;
	private HashMap<String, Integer> hashtag;
	private HashMap<String, Integer> at;
	
	public User(String name, int[] points_a,HashMap hashtag,HashMap at){
		this.name = name;
		points = new int[3];
		points[0] = points_a[0];
		points[1] = points_a[1];
		points[2] = points_a[2];
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

	@Override
	public int compareTo(User o) {
		return name.compareTo(o.name);
	}
}
