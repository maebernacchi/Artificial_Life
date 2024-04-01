import java.util.Random;

abstract class Organism{

	    
	    private int energyLevel; 

	    Organism(){
	        this.energyLevel = 0;
	    }

	    void update(){
	        this.energyLevel += 1;
	    }
	    
	    int getEnergy(){
	        return this.energyLevel;
	    }

	    void incrementEnergy(){
	        this.energyLevel += 1;
	    }

	    void decrementEnergy(){
	        this.energyLevel -= 10; 
	    }

	    public abstract String getType();

	    public abstract Organism reproduce();

	    public abstract double getCooperationProbability();

	    public abstract boolean cooperates();
	}


class Cooperator extends Organism{

	private String type;
    private double prob;
    private boolean coop;
    Cooperator(){
        this.type = "Cooperator";
        this.prob = 100.0;
        this.coop = true;
    }

    @Override 
    public String getType(){
        return this.type;
    }

    @Override
    public Organism reproduce(){
        Cooperator child = new Cooperator();
        return child;
    }

    @Override
    public double getCooperationProbability(){
        return this.prob;
    }

    @Override
    public boolean cooperates(){
        return this.coop;
    }
}


class Defector extends Organism{
	private String type;
    private double prob;
    private boolean coop;
    Defector(){
        this.type = "Defector";
        this.prob = 0.0;
        this.coop = false;
    }

    @Override 
    public String getType(){
        return this.type;
    }

    @Override
    public Organism reproduce(){
        Defector child = new Defector();
        return child;
    }

    @Override
    public double getCooperationProbability(){
        return this.prob;
    }

    @Override
    public boolean cooperates(){
        return this.coop;
    }
}


class PartialCooperator extends Organism{
	private String type;
    private double prob;
    private boolean coop;
    PartialCooperator(){
        this.type = "PartialCooperator";
        this.prob = 50.0;
        this.coop = randomCoop();
    }
    
    private boolean randomCoop() {
    	Random num = new Random();
    	int result = num.nextInt(2);
    	if (result == 0) 
    		return true;
    	else
    		return false;
    }
    

    @Override 
    public String getType(){
        return this.type;
    }

    @Override
    public Organism reproduce(){
        PartialCooperator child = new PartialCooperator();
        return child;
    }

    @Override
    public double getCooperationProbability(){
        return this.prob;
    }

    @Override
    public boolean cooperates(){
        return this.coop;
    }
}

