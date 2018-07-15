package Classes;
import java.util.*;
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
        
        
        //Iterate through every layer
        for(int i = 0; i < NETWORK_SIZE;i++)
        {
        	//Creation of output array (size is network layer size)
            this.output[i] = new double [NETWORK_LAYER_SIZES [i]];
            
            //Creation of bias array
             this.bias [i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7);
            
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
    	//If the input is not equals to the ammount of neurons in the input layer. Too much or too less data to calculate
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
            }
        }
        //return output
        return output[NETWORK_SIZE-1];
    }
    
    private double sigmoid (double x)
    {
    	//activation function
        return 1d / (1 + Math.exp(-x));
    }
    
    public static void main(String[] args)
    {
    	
    	//4 input neurons,2 neurons in first hidden layer, 3 neurons in second hidden layer,   4 outputs
    	
        Network net = new Network(4,2,3,4);
        
        //Test values
        double [] output = net.calculate(0.2,0.9,0.3,0.4);
        System.out.println(Arrays.toString(output));

    }


}
