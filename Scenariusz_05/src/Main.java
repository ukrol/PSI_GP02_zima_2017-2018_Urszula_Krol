public class Main {
    public static void main(String args[]){
    	double learning_rate = 0.1;
    	int number_of_neurons = 30;
        Network network = new Network(learning_rate, number_of_neurons);
        network.learn();
        network.test();
    }
}