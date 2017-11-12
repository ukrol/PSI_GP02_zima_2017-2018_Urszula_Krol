public class NeuronAdaline {
	public double w1, w2;
    public double weight[] = new double[35];
    double learning_rate = 0.01;
    
    public static final int[] target_result = {1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0}; //1 gdy litera jest duza, 0 gdy mala
    public static final char[] letters = {'A','B','C','D','E','F','G','H','I','J','a','b','c','d','e','f','g','h','r','s'};
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
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1,1,1},//a
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,0,1,0,1,0,0,1,1,1},//b
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1},//c
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,1,1},//d
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,1,0,1,1,1,0,0,1,0,0,0,0,1,1,1,1},//e
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,1,1,1,0,0,0,1,0,0,0,0,1,0},//f
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,0,1,0,0,1,1,1,0,0,0,0,1,0,0,1,1,1},//g
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1},//h
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,0,0},//r
                    					   			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,0,0}};//s
    
    public static final char[] t_letters ={'M','N','O','P','R','S','T','U','W','X','Y','h','k','l','m','n','o','p','u','w'};
    public static final int[][] test_letters = {{1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},//M
		    									{1,0,0,0,1,1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,1,0,0,0,1},//N
		    									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//O
		    									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},//P
		    									{1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1},//R
		    									{0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//S
		    									{1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},//T
		    									{1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},//U
		    									{1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0},//W
		    									{1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1},//X
		    									{1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},//Y
    											{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,0,1,0,1,0,0,1,0,1},//h
                   						    	{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,1},//k
                   						    	{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1},//l
                   						    	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1},//m
                					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0},//n
                					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1,1,0},//o
                					   			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0},//p
                					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1,1,1},//u
                					   			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0}};//w
       
    public void learn(NeuronAdaline adaline) {
        int[][] letters = NeuronAdaline.learning_letters; //dane uczace
        int[] results = NeuronAdaline.target_result; //wyniki oczekiwane
        int iteration = 0; //iteracja
        boolean standard = false; //zmienna okreslajaca czy blad miesci sie w normie
        double error = 0; //b³¹d
        double delta;  //target_result-sum
        double[] another_weight;  //nowe wagi
        double[] sum = new double[20];  //tablica zawiera sumê dla ka¿dej litery z zestawu
        
        for(int i=0; i<35; i++){ //randomowe wagi
        	weight[i] = Math.random();
        }
        
        while (standard==false) {
        	iteration++;
            System.out.println("Iteration number: " + iteration);
            standard = false; 
            error =0;
            for (int x=0; x<20; x++){ //dla kazdej litery uczacej
                int[] tab = letters[x];
                sum[x] = adaline.countSum(tab ,weight); //liczy sume
                delta = results[x] - sum[x]; //liczy delte
                another_weight = adaline.countWeight(tab, weight, learning_rate, delta);//liczy wagi
                weight = another_weight;
            }
            error = 0.5*(results[0] - sum[0])*(results[0] - sum[0]); 
            System.out.println("Error: " + error);
            if(error>0.001) {
            	standard = false;
            }
            else {
            	standard = true;
            }
        }
        System.out.println("Iterations: "+ iteration);
        System.out.println();
        check(adaline);
    }
    
    public void check(NeuronAdaline adaline) {
    	System.out.println("Learning data:");
        double[] sum = new double[20];
        int[] tab;
        for (int x=0; x<20; x++){
            tab = adaline.learning_letters[x];
            sum[x] = adaline.countSum(tab, weight);
            System.out.print("Letter "+ adaline.letters[x]+" is: ");
            if(adaline.activationFunction(sum[x])==1) { //wynik uzyskany przez uzycie funkcji aktywacji
                System.out.println("big");
            }else{
                System.out.println("small");
            }
        }
    }
    
    public void test(NeuronAdaline adaline) {

    	double[] sum = new double[20];
        int[] tab;
        System.out.println();
        System.out.println("Test data:");
        for (int x = 0; x< adaline.t_letters.length; x++){
            tab = adaline.test_letters[x];
            sum[x] = adaline.countSum(tab, weight);
            System.out.print("Letter "+ adaline.t_letters[x]+" is: ");
            if(adaline.activationFunction(sum[x])==1) {
                System.out.println("big");
            }else{
                System.out.println("small");
            }
        }
    }
    
    public double countSum(int[] table, double[] weight){ //funkcja zwracaj¹ sumê
        double sum = 0.0;
        for(int x=0; x < table.length; x++)
            sum += table[x] * weight[x];
        return sum;
    }

    public double[] countWeight(int[] table, double[] weight, double learning_rate, double delta){  //zwraca wagi obliczone zgodnie ze wzorem
        double[] newWeight = new double[weight.length];
        for(int x=0; x < weight.length;x++)
            newWeight[x] = learning_rate * delta * table[x] + weight[x];
        return newWeight;
    }
    
    public int activationFunction(double sum){ //aktywacja
        int result;
        if(sum > 0.5){
        	result = 1;
        }
        else{
        	result=0;
        }
        return result;
    }
}
