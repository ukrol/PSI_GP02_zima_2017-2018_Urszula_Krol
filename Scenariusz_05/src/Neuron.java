import java.util.Random;

public class Neuron {
    private double learning_rate;
    private double[] weight;

    public Neuron(double learning_rate) {
        this.learning_rate = learning_rate;
        weight= new double[4];
        randomWeight();
    }

    public double activationFunction(Data data){
    	double activate = 0;
        for(int i = 0; i < 4; i++) {
        	activate += data.getInput(i) * weight[i];
        }
        return activate;
    }
    
    private void randomWeight(){
    	Random random = new Random();
        for(int i = 0; i < 4; i++)
            weight[i] = random.nextDouble();
    }

    public void changeWeight(Data data) {
        for (int i = 0; i < 4; i++) {
            weight[i] = weight[i] + learning_rate * (data.getInput(i) - weight[i]);
        }
        normalizeWeight();
    }

    private void normalizeWeight(){
        double sumPow = weight[0]*weight[0] + weight[1]*weight[1] + weight[2]*weight[2] + weight[3]*weight[3];
        double sumPowSqrt = Math.sqrt(sumPow);
        weight[0] /= sumPowSqrt;
        weight[1] /= sumPowSqrt;
        weight[2] /= sumPowSqrt;
        weight[3] /= sumPowSqrt;
    }
}