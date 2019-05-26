package Model;

public class WeightUsers implements Comparable{

	private User users[];
	//[0] Tecnology [1] Sports [2] Politics
	private int[] differenceTypes;
	// [0] relation # [1] relation@
	private  int[] relation;
	// coeficiente de distancia (menos al 10% es bueno)
	private double relationcoefficient;
	private int allDifference;
	private int allCoefficient;
	public WeightUsers(User[] users) {
		this.users = users;
		calculateDifference();
		calculateRelation();
		calculateCoefficient();
	}
	private void calculateCoefficient() {
		relationcoefficient = allDifference/allCoefficient;
		
	}
	private void calculateDifference() {
		differenceTypes[0]= Math.abs(users[0].getPoints()[0]-users[1].getPoints()[0]);
		differenceTypes[1]= Math.abs(users[0].getPoints()[1]-users[1].getPoints()[1]);
		differenceTypes[2]= Math.abs(users[0].getPoints()[2]-users[1].getPoints()[2]);
		allDifference = differenceTypes[0]+differenceTypes[1]+differenceTypes[2];
	}
	private void calculateRelation() {
		for(String key : users[0].getAt().keySet()) {
			if(users[1].getAt().containsKey(key))
				relation[1]++;
		}
		for(String key : users[0].getHashtag().keySet()) {
			if(users[1].getHashtag().containsKey(key))
				relation[0]++;
		}
		allCoefficient = relation[0]+relation[1];
	}
	@Override
	public int compareTo(Object o) {
		return this.allDifference-((WeightUsers)o).allDifference;
	}
	
	
	
	

}
