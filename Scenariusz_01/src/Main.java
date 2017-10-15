public class Main {

	public static void main(String[] args) {
		double learning_rate = 0.1;
		ArtificialNeuron neuron = new ArtificialNeuron();
		neuron.learn(learning_rate);
		neuron.test();
	}
}
