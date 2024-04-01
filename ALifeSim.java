import java.util.HashMap;
import java.util.Map;

public class ALifeSim {

	public static void main(String[] args) {
		Map<String, Integer> entry = new HashMap<String, Integer>();
		entry.put("Iterations", Integer.parseInt(args[0]));
		entry.put("Cooperators", Integer.parseInt(args[1]));
		entry.put("Defectors", Integer.parseInt(args[2]));
		entry.put("Partial Cooperators", Integer.parseInt(args[3]));
		Population working = new Population(entry);
		for(int i = 0; i < entry.get("Iterations"); i++) {
			working.update();
		}
		
		System.out.println("After " + entry.get("Iterations") + "ticks:\n");
		Map<String, Integer> finals = working.getPopulationCounts();
		System.out.println("Cooperators: " + finals.get("Cooperators")+ "\n");
		System.out.println("Defectors: " + finals.get("Defectors")+ "\n");
		System.out.println("Partial Cooperators: " + finals.get("Partial Cooperators") + "\n");
		System.out.println("Mean Cooperation Probability = " + ((Population) finals).calculateCooperationMean());
	}

}
