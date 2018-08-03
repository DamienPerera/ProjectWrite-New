package Classes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;
import parser.*;
import org.omg.IOP.TransactionService;

import trainset.TrainSet;
public class Network {
	

	//First the layers and second is for the neuron we will work with
    private double [][] output;
    
    //1 Layer, 2nd Layer, Neuron in previous layer its connected with
    //Weights will connect the neurons
    private double [] [] [] weights;
    
    //Bias for each neuron (bias is needed to scale down)
    private double [] [] bias;
    
    
    //Contains the number of neurons the network has 
    public final int [] NETWORK_LAYER_SIZES;
    
    
    //Contains input size
    public final int  INPUT_SIZE;
    
    //Contains output size
    public final int  OUTPUT_SIZE;
    
    //Stores the amount of layers
    public final int  NETWORK_SIZE; 
    
    private double[] [] errorsignal;
    private double[] [] outputDerivative; 
    //Constructor
    public Network(int... NETWORK_LAYER_SIZES) {
        this.NETWORK_LAYER_SIZES = NETWORK_LAYER_SIZES;
        this.INPUT_SIZE =NETWORK_LAYER_SIZES[0];
        this.NETWORK_SIZE= this.NETWORK_LAYER_SIZES.length;
        //Index of first layer is 0 
        this.OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_SIZE-1];
        
        //For every layer create a new array
        this.output = new double [NETWORK_SIZE] [];
        this.weights = new double [NETWORK_SIZE] [][];
        this.bias = new double [NETWORK_SIZE] [];
        this.errorsignal = new double [NETWORK_SIZE] [];
        this.outputDerivative = new double [NETWORK_SIZE] [];
        
        //Iterate through every layer
        for(int i = 0; i < NETWORK_SIZE;i++)
        {
        	//Creation of output array (size is network layer size)
            this.output[i] = new double [NETWORK_LAYER_SIZES [i]];
            this.errorsignal[i] = new double [NETWORK_LAYER_SIZES [i]];
            this.outputDerivative[i] = new double [NETWORK_LAYER_SIZES [i]];
            //Creation of bias array
             this.bias [i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], -0.5, 0.7);
            
            // Create a weights array for every layer except the first layer
            if (i>0)
            {
              
                weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i-1],  -0.3, 0.5);
            }
        }
        
                
       
    }
    
    //Feedforward network To return output
    public double [] calculate (double... input)
    {
    	//If the input is not equals to the amount of neurons in the input layer. Too much or too less data to calculate
        if (input.length != this.INPUT_SIZE) return null;
        
        //out of the first layer set to the input
        this.output[0] = input;
        
        //Iterations of every other layer
        //Calculate the output by taking the output of the previous layer and their wights 
        for(int layer = 1; layer < NETWORK_SIZE; layer ++)
        {
        	//Iterate through every neuron
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++)
            {
            	
                double sum = bias [layer] [neuron];
                
                
                for(int prevNeuron = 0; prevNeuron <  NETWORK_LAYER_SIZES [layer-1]; prevNeuron++ )
                {
                	//sum increased by the output of the neuron
                    sum += output [layer-1][prevNeuron] * weights [layer] [neuron] [prevNeuron];
                    
                }
                
                output[layer][neuron]=sigmoid(sum);
                outputDerivative [layer][neuron] =  output[layer][neuron] * (1 -output[layer][neuron]);
            }
        }
        //return output
        return output[NETWORK_SIZE-1];
    }
    
    public void train (TrainSet set, int loops, int batchSize)
    {
    	for (int i = 0; i <loops; i++) 
    	{
    		if (set.INPUT_SIZE != INPUT_SIZE || set.OUTPUT_SIZE != OUTPUT_SIZE) return;
    		TrainSet batch = set.extractBatch(batchSize);
    		for (int b = 0; b< batch.size(); b++)
    		{
    			this.train(batch.getInput(b), batch.getOutput(b), 0.3);
    		}
    		System.out.println(MSE(batch));
		}
    }
    //Input data, a Target vector for out put value and learning rate
    private void train(double[] input, double [] target, double learningRate) {
    	///Input data does not have right dimension size
		if (input.length != INPUT_SIZE || target.length != OUTPUT_SIZE) return;
		//calculate output of our network
		//each out put of each neuron is calculated
		calculate(input);
		backpropogationError(target);
		updateWeights(learningRate);
	}
    
    //Calculates how close the output and the target vector are together 
    public double MSE (double [] input, double [] target)
    {
    	if (input.length != INPUT_SIZE||target.length !=OUTPUT_SIZE) return 0;
    	calculate (input);
    	double v = 0;
    	for(int i =0; i < target.length; i++)
    	{
    		v += (target[i] - output[NETWORK_SIZE-1][i]) * (target[i]- output [NETWORK_SIZE-1][i]);
    		
    	}
    	return v/ (2d*target.length);
    			
    }
    
    
    public double MSE (TrainSet set)
    {
    	double v=0;
    	for(int i=0; i<set.size(); i++)
    	{
    		v += MSE (set.getInput(i),set.getOutput(i));
    		
    	}
    	return v/set.size();
    	}
    //back propagation
    public void backpropogationError (double [] target)
    {
    	
    	for (int neuron = 0; neuron < NETWORK_LAYER_SIZES[NETWORK_SIZE-1]; neuron++) {
    		errorsignal[NETWORK_SIZE-1] [neuron] = (output[NETWORK_SIZE-1] [neuron] - target [neuron])* outputDerivative[NETWORK_SIZE-1] [neuron]; 
    		
    	}
    	for (int layer = NETWORK_SIZE-2; layer >0; layer --)
    	{
    		for(int neuron = 0; neuron < NETWORK_LAYER_SIZES [layer]; neuron ++)
    		{
    			double sum =0;
    			for (int nextNeuron =0; nextNeuron < NETWORK_LAYER_SIZES[layer+1]; nextNeuron++)
    			{
    				sum += weights[layer + 1][nextNeuron][neuron] * errorsignal[layer+1][nextNeuron];
    				
    			}
    			this.errorsignal[layer][neuron] = sum * outputDerivative[layer] [neuron];
    		}
    	}
    }
    
    public void updateWeights (double learningRate )
    {
    	for (int layer = 1; layer < NETWORK_SIZE; layer++ )
    	{
    		for(int neuron = 0; neuron <NETWORK_LAYER_SIZES[layer]; neuron++)
    		{
    			double delta = -learningRate * errorsignal[layer][neuron];
    			bias [layer][neuron] +=delta;
    			
    			for(int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES [layer -1]; prevNeuron++ )
    			{
    				
    				weights[layer][neuron][prevNeuron] +=delta * output[layer-1] [prevNeuron] ;
    			}
    			
    		}
    	}
    }
    
    private double sigmoid (double x)
    {
    	//activation function
        return 1d / (1 + Math.exp(-x));
    }
    
    public void saveNetwork(String fileName) throws Exception {
        Parser p = new Parser();
        p.create(fileName);
        Node root = p.getContent();
        Node netw = new Node("Network");
        Node ly = new Node("Layers");
        netw.addAttribute(new Attribute("sizes", Arrays.toString(this.NETWORK_LAYER_SIZES)));
        netw.addChild(ly);
        root.addChild(netw);
        for (int layer = 1; layer < this.NETWORK_SIZE; layer++) {

            Node c = new Node("" + layer);
            ly.addChild(c);
            Node w = new Node("weights");
            Node b = new Node("biases");
            c.addChild(w);
            c.addChild(b);

            b.addAttribute("values", Arrays.toString(this.bias[layer]));

            for (int we = 0; we < this.weights[layer].length; we++) {

                w.addAttribute("" + we, Arrays.toString(weights[layer][we]));
            }
        }
        p.close();
    }

    public static Network loadNetwork(String fileName) throws Exception {

        Parser p = new Parser();

            p.load(fileName);
            String sizes = p.getValue(new String[] { "Network" }, "sizes");
            int[] si = ParserTools.parseIntArray(sizes);
            Network ne = new Network(si);

            for (int i = 1; i < ne.NETWORK_SIZE; i++) {
                String biases = p.getValue(new String[] { "Network", "Layers", new String(i + ""), "biases" }, "values");
                double[] bias = ParserTools.parseDoubleArray(biases);
                ne.bias[i] = bias;

                for(int n = 0; n < ne.NETWORK_LAYER_SIZES[i]; n++){

                    String current = p.getValue(new String[] { "Network", "Layers", new String(i + ""), "weights" }, ""+n);
                    double[] val = ParserTools.parseDoubleArray(current);

                    ne.weights[i][n] = val;
                }
            }
            p.close();
            return ne;

    }
    
    
   
    public static void main (String [] args)
    {
   
   	  try {
		//Network net = new Network (4,3,2);
		//net.saveNetwork("res/test2.txt");
   		Network net = Network.loadNetwork("res/test2.txt");  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	  
    }
}
