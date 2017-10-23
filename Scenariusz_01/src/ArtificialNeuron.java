import java.util.Random;

public class ArtificialNeuron {
	
	public double weight[];//wagi
	public static final double x[][] = {{2,-3}, {4,6}, {0,-1}, {5,-8}, {3,9}, {-1,8}, {4,-3}, {6, -7}, {0, 0}, {8, -2}};//tablica danych wejscia x do uczenia
	public int w = 10; //wiersze tablicy
	public int k = 2; //kolumny tablicy
	public static final double target_result[] = {0,1,0,0,1,1,1,0,0,1}; //1 jeżeli suma>0 lub 0 jeżeli suma<0
	public double out_data = 0; //dane wyjsciowe
	public double error;
	public double test_data[][];
	public int number_of_testing_data = 10; //ilość danych do testu
	
    public ArtificialNeuron() {
	weight = new double[k];
	test_data = new double[number_of_testing_data][k];	
    }
		
    private void random_weight() {
    	Random random = new Random();
    	for(int i = 0; i < k; i++){
        weight[i] = random.nextDouble() * 2 - 1; //losowe wagi w przedziale od -1 do 1
    	}
    	System.out.println("Weight: ");
    	for (int i = 0; i < k; i++) {
        System.out.println(weight[i]);

        }
    }
	
    public void learn(double learning_rate){ 
    	int iteration = 0;
    	random_weight();
    	do {
    		iteration++;
    		System.out.println("*****" + iteration + "*****");
			for (int i = 0; i < w; i++){
    			out_data = check(x[i][0],x[i][1]);
    			error = target_result[i]-out_data;
    			weight[0] += learning_rate * error * x[i][0]; //obliczanie wag
    			weight[1] += learning_rate * error * x[i][1];

    			System.out.println(x[i][0] + " " + x[i][1] + " " + target_result[i] + "    Result: "+ out_data );
    		}
    	} while(error*error > 0.01);
   }

   private double check(double x1,double x2){
	   
	   double sum = 0;
	   sum = x1 * weight[0] + x2 * weight[1];

        if(sum>0) { //czy suma jest dodatnia
        	sum = 1;
        }
        else {
        	sum = 0;
        }
        return sum;
    }
   
   public void test(){
       Random rand = new Random();
       for(int i=0; i<number_of_testing_data; i++){
           test_data[i][0]=rand.nextInt(21)-10; //losowe liczby do testu w przedziale od -10 do 10
           test_data[i][1]=rand.nextInt(21)-10;
       }
       System.out.println("Checking if the sum of this two numbers is a positive number: ");
       System.out.println("Testing...");
       for(int i=0; i<number_of_testing_data; i++){
    	   System.out.println(test_data[i][0] + " " + test_data[i][1] + "    Test result: " + check(test_data[i][0], test_data[i][1]));
       }
   }
}
