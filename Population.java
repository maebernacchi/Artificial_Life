import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Population {
	private int coops;
	private int defs;
	private int parts;
	private int rounds;
	private HashMap<Organism, Integer> orgs = new HashMap<Organism, Integer>();

	Population(Map<String, Integer> counts) throws IllegalArgumentException{
		int num = 0;
		this.coops = counts.get("Cooperators");
		this.defs = counts.get("Defectors");
		this.parts = counts.get("Partial Cooperators");
		this.rounds = counts.get("Iterations");
		if(this.coops != 0) {
			for(int i = 0; i < this.coops; i++) {
				Cooperator newTemp = new Cooperator();
				orgs.put(newTemp, num);
				num+= 1;
			}
		} 
		if(this.defs != 0) {
			for(int i = 0; i < this.coops; i++) {
				Defector newTemp = new Defector();
				orgs.put(newTemp, num);
				num+= 1;
			}
		}
		if(this.parts != 0) {
			for(int i = 0; i < this.coops; i++) {
				PartialCooperator newTemp = new PartialCooperator();
				orgs.put(newTemp, num);
				num+= 1;
			}
		}
	}
	
	public void update() {
		@SuppressWarnings("rawtypes")
		Iterator iter = orgs.entrySet().iterator();
		while(iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry element = (Map.Entry)iter.next();
			Organism looking = (Organism)element.getKey();
			looking.update();
			boolean replacer = looking.cooperates();
			if(looking.getEnergy() == 10) {
				Organism temp = looking;
				if(temp.cooperates()) {
					while(iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry ele = (Map.Entry)iter.next();
						Organism te = (Organism)ele.getKey();
						if(te.cooperates() != replacer) {
							te = temp;
							te.decrementEnergy();
							looking.decrementEnergy();
							return;
						}
					}
					if(looking.getType().equals("Cooperator")) {
						this.defs -= 1;
						this.parts -= 1;
						this.coops += 1;
					}
					if(looking.getType().equals("Defector")) {
						this.defs += 1;
						this.parts -= 1;
						this.coops -= 1;
					}
					if(looking.getType().equals("PartialCooperator")) {
						this.defs -= 1;
						this.parts += 1;
						this.coops -= 1;
					}
				}
				if(!temp.cooperates()){
					while(iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry ele = (Map.Entry)iter.next();
						Organism te = (Organism)ele.getKey();
						if(te.cooperates() != replacer) {
							te = temp;
							te.decrementEnergy();
							looking.decrementEnergy();
							return;
						}
						if(looking.getType().equals("Cooperator")) {
							this.defs -= 1;
							this.parts -= 1;
							this.coops += 1;
						}
						if(looking.getType().equals("Defector")) {
							this.defs += 1;
							this.parts -= 1;
							this.coops -= 1;
						}
						if(looking.getType().equals("PartialCooperator")) {
							this.defs -= 1;
							this.parts += 1;
							this.coops -= 1;
						}
					}
				}
			}
		}
	}
	public double calculateCooperationMean() {
		double calc = 0.0;
		@SuppressWarnings("rawtypes")
		Iterator iter = orgs.entrySet().iterator();
		while(iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry element = (Map.Entry)iter.next();
			Organism looking = (Organism)element.getKey();
			calc += looking.getCooperationProbability();
		}
		calc /= this.rounds;
		return calc;
	}
	
	public Map<String, Integer> getPopulationCounts(){
		Map<String, Integer> working = new HashMap<String, Integer>();
		working.put("Cooperators", this.coops);
		working.put("Defectors", this.defs);
		working.put("Partial Cooperators", this.parts);
		return working;
	}
	
	
	
	
	
	
}
