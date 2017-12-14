public class Layer {
    public Neuron[] neuron;
    private int number_of_neurons;
    private double learning_rate;
    private Data data;


    public Layer(int number_of_neurons, double learning_rate){
        this.number_of_neurons = number_of_neurons;
        this.learning_rate = learning_rate;
        neuron = new Neuron[this.number_of_neurons];
        for(int i = 0; i< this.number_of_neurons; i++){
            neuron[i] = new Neuron(this.learning_rate);
        }
    }
    
    public double[] countLayer(Data data){
        double[] results = new double[neuron.length];
        this.data = data;
        for (int i=0 ; i < neuron.length; i++){
            results[i] = neuron[i].activationFunction(data);
        }
        return results;
    }

    public void change(int id){
        neuron[id].changeWeight(data);
    }
}