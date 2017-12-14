import java.util.ArrayList;

public class Network {
    private double learning_rate; 
    private int number_of_neurons; 
    private static int max = 100;
    private Data[] learning_data; 
    private Data[] testing_data;
    private int number_of_learning_data;
    private int number_of_testing_data;
    private Layer layer;

    public Network(double learning_rate, int number_of_neurons) {
        this.learning_rate = learning_rate;
        this.number_of_neurons = number_of_neurons;

        layer = new Layer(number_of_neurons, learning_rate);
        Data data = new Data();

        learning_data =  data.loadFromFile("data.txt");
        number_of_learning_data = data.getNumber();
        testing_data = data.loadFromFile("test.txt");
        number_of_testing_data = data.getNumber();
    }
    
    public void learn() {
    	normalizeData(learning_data);
        int iterations = 0;
        double[] result = new double[number_of_learning_data];
        do {
            for (int i = 0; i < number_of_learning_data; i++) {
                result = layer.countLayer(learning_data[i]);
                layer.change(maxElement(result));
            }
            iterations++;
        } while( iterations < max );
        System.out.println("Correct learning!");
        System.out.println("Iterations: " + iterations);
    }

    public void test() {
    	System.out.println("Testing...");
    	normalizeData(testing_data);
    	double[] result = new double[number_of_testing_data];
        ArrayList<Integer> winners = new ArrayList<Integer>();
        int winner;
        for(int i=0; i< number_of_testing_data; i++) {
            result = layer.countLayer(testing_data[i]);
            winner = maxElement(result);
            if(!winners.contains(winner)){
                winners.add(maxElement(result));
            }
            System.out.println(i+1 + ". Type: " + testing_data[i].getType() + "  Result: " + winner );
        }

        System.out.println("Winners: ");
        for(Integer w: winners)
            System.out.println(w.toString());
    }

    public void normalizeData(Data[] data) {
    	double sumPow = 0.0;
    	double sumPowSqrt = 0.0;
        for(int i =0 ; i< data.length; i++){
            sumPow = data[i].getInput(0)*data[i].getInput(0) + data[i].getInput(1)*data[i].getInput(1) + data[i].getInput(2)*data[i].getInput(2) + data[i].getInput(3)*data[i].getInput(3);
            sumPowSqrt = Math.sqrt(sumPow);
            data[i].setInput( data[i].getInput(0)/sumPowSqrt, 0);
            data[i].setInput( data[i].getInput(1)/sumPowSqrt, 1);
            data[i].setInput( data[i].getInput(2)/sumPowSqrt, 2);
            data[i].setInput( data[i].getInput(3)/sumPowSqrt, 3);
        }
    }
    
    public int maxElement(double[] tab) {
    	double wynik = tab[0];
    	int id = 0;
    	for (int i=1; i<tab.length; i++) {
    		if (wynik < tab[i]) {
    				wynik = tab[i];
    				id=i;
    		}
    	}
    	return id;
    }
}