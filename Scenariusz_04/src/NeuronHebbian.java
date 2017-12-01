import java.util.Random;

public class NeuronHebbian {
    public double weight[];
    double learning_rate;
    double forgeting_rate;
    
    public static final char[] letters = {'A','B','C','D','E','F','G','H','I','J','M','N','O','P','R','S','T','U','W','X'};
    public static final int[][] learning_letters = {{0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},//A
				 									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0},//B
				 									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,1,1,1,0},//C
				 									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0},//D
				 									{1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1},//E
				 									{1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},//F
				 									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//G
				 									{1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},//H
				 									{0,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,1,1,0},//I
				 									{1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//J
				 									{1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},//M
				 									{1,0,0,0,1,1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,1,0,0,0,1},//N
				 									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//O
				 									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},//P
				 									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1},//R
				 									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//S
				 									{1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},//T
				 									{1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//U
				 									{1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0},//W
				 									{1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1}};//X
    
    public NeuronHebbian() {
    	weight = new double[35];
    	learning_rate = 0.1;
    	forgeting_rate = 0.1;
    	initializeWeight();
    }
       
    
    public void learn() {
    	for (int n = 0; n < 20; n++) {
            double sum;
            int[] tab;
            tab = learning_letters[n];
            sum= countSum(tab, weight);
            for (int i = 0; i < 35; i++) {
            	weight[i] = weight[i] * forgeting_rate + learning_rate * activationFunction(sum) * (tab[i] - weight[i]);
            }  
    	}
    }
    
    public void test(){
        for (int n = 0; n <20 ; n++) {
            double result;
            int[] tab;
            tab = learning_letters[n];
     
            double sum = countSum(tab, weight);
            result = activationFunction(sum);
     
            System.out.println("Letter "+ letters[n] +"-> Result: "+ result);
        }
    }
    
    public double countSum(int[] table, double[] weight){
        double sum = 0.0;
        for(int x=0; x < table.length; x++)
            sum += table[x] * weight[x];
        return sum;
    }
    
    private void initializeWeight() {
        Random random = new Random();
        for (int i = 0; i < 35; i++) {
            weight[i] = random.nextDouble();
        }
    }

    public double activationFunction(double s){
    	double result = (1 - Math.exp(-s)) / (1 + Math.exp(-s));
		return result;
    }
}
