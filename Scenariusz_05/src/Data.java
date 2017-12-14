import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Data {
    private double[] input;
    private String type;
    private InputStream is;
    private InputStreamReader reader;
    private BufferedReader bufferedreader;
    private int number_of_data;

    public Data[] loadFromFile(String file) {
    	Data data[] = null;
        String line;
        String elements[];

        try {
            is = new FileInputStream(new File(file));
            reader = new InputStreamReader(is);

            bufferedreader = new BufferedReader(reader);
            line = bufferedreader.readLine();
            elements = line.split("	");
            number_of_data = Integer.parseInt(elements[0]);
            data = new Data[number_of_data];

            for (int i = 0; i < number_of_data; i++) {
                line = bufferedreader.readLine();
                elements = line.split("	");
                data[i] = new Data();
                for (int j = 0; j < 4; j++) {
                    data[i].setInput(Double.parseDouble(elements[j]), j);
                }
                data[i].setType(elements[4]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return data;
    }

    public int getNumber() {
        return number_of_data;
    }
    
    public Data(double[] input, String type) {
        this.input = input;
        this.type = type;
    }

    public Data(){
        this.input = new double[4];
    }

    public void setInput(double input, int i){
        if(i < 4) {
            this.input[i]=input;
        }
    }

    public double getInput(int i){
        if(i < 4) {
            return this.input[i];
        }
        else return 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}