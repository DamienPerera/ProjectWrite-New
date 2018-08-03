package mnist;

import Classes.MainPanel;
import Classes.Network;
import Classes.NetworkTools;
import trainset.TrainSet;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Mnist {
	
    

	
    public static void main(String[] args) {
    	JTextField field1 = new JTextField();
    	JTextField field2 = new JTextField();
    	JTextField field3 = new JTextField();
    	JTextField field4 = new JTextField();
    	JTextField field5 = new JTextField();
    	
    	Object[] fields = {
    		    "Epoch Size", field1,
    		    "Batch Size:", field2,
    		    "Number of Loops:", field3,
    		    "First Hidden Layer Size:", field4,
    		    "Second Hidden Layer Size:", field5,
    		};
    	JOptionPane.showConfirmDialog(null,fields, "Enter Network Training Details", JOptionPane.OK_CANCEL_OPTION );
    	
    	
    	int epochsize = Integer.parseInt(field1.getText());
    	int batch = Integer.parseInt(field1.getText());
    	int loop = Integer.parseInt(field1.getText());
    	int h1 = Integer.parseInt(field1.getText());
    	int h2 = Integer.parseInt(field1.getText());
         
         
    	Network network = new Network(784, h1, h2, 10);
         
    	//trainData(network, createTrainSet(0, 100),1000, 100, 100, "res/test.txt");
    	trainData(network, createTrainSet(0, 100),epochsize, loop, batch, "res/test.txt");
    	//trainData(net, set, epochs, loops, batch_size, output_file);
    	
    	//Loading the Train Set
    	//try {
			//Network net = Network.loadNetwork("res/mnistl.txt");
			//testTrainSet(net,createTrainSet(10000,20000),10);
		
		//} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
    }

    public static TrainSet createTrainSet(int start, int end) {

        TrainSet set = new TrainSet(28 * 28, 10);

        try {

            String path = new File("").getAbsolutePath();

            MnistImageFile m = new MnistImageFile(path + "/res/trainImage.idx3-ubyte", "rw");
            MnistLabelFile l = new MnistLabelFile(path + "/res/trainLabel.idx1-ubyte", "rw");

            for(int i = start; i <= end; i++) {
                if(i % 100 ==  0){
                    System.out.println("prepared: " + i);
                }

                double[] input = new double[28 * 28];
                double[] output = new double[10];

                output[l.readLabel()] = 1d;
                for(int j = 0; j < 28*28; j++){
                    input[j] = (double)m.read() / (double)256;
                }

                set.addData(input, output);
                m.next();
                l.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         return set;
    }

    public static void trainData(Network net,TrainSet set, int epochs, int loops, int batch_size, String outputFile) {
        for(int e = 0; e < epochs;e++) {
            net.train(set, loops, batch_size);
            
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>   "+ e+ "   <<<<<<<<<<<<<<<<<<<<<<<<<<");
            
            try {
				net.saveNetwork(outputFile);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    public static void testTrainSet(Network net, TrainSet set, int printSteps) {
        int correct = 0;
        JOptionPane.showMessageDialog(null, "System Training Started");
        
        for(int i = 0; i < set.size(); i++) {

            double highest = NetworkTools.indexOfHighestValue(net.calculate(set.getInput(i)));
            double actualHighest = NetworkTools.indexOfHighestValue(set.getOutput(i));
            if(highest == actualHighest) {

                correct ++ ;
            }
            if(i % printSteps == 0) {
                System.out.println(i + ": " + (double)correct / (double) (i + 1));
            }
        }
        System.out.println("Testing finished, RESULT: " + correct + " / " + set.size()+ "  -> " + (double)correct / (double)set.size() +" %");
    }

}
