public class Main {
   
    public static void main(String[] args) {
    	NeuronAdaline adaline=new NeuronAdaline();
    	adaline.learn(adaline);
    	adaline.test(adaline);
    }
}