package Classes;
import java.util.*;
public class Network {
	 
    private double [][] output;
    //1 Layer, 2nd Layer, Neuron in previous layer.
    private double [] [] [] weights;
    private double [] [] bias;
    
    
    public final int [] NETWORK_LAYER_SIZES;
    public final int  INPUT_SIZE;
    public final int  OUTPUT_SIZE;
    public final int  NETWORK_SIZE; 
    
    public Network(int... NETWORK_LAYER_SIZES) {
        this.NETWORK_LAYER_SIZES = NETWORK_LAYER_SIZES;
        this.INPUT_SIZE =NETWORK_LAYER_SIZES[0];
        this.NETWORK_SIZE= this.NETWORK_LAYER_SIZES.length;
        this.OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_SIZE-1];
        
        this.output = new double [NETWORK_SIZE] [];
        this.weights = new double [NETWORK_SIZE] [][];
        this.bias = new double [NETWORK_SIZE] [];
        
        for(int i = 0; i < NETWORK_SIZE;i++)
        {
            this.output[i] = new double [NETWORK_LAYER_SIZES [i]];
            
            
             this.bias [i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7);
            
            if (i>0)
            {
              
                weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i-1],  -0.3, 0.5);
            }
        }
        
                
       
    }
    
    public double [] calculate (double... input)
    {
        if (input.length != this.INPUT_SIZE) return null;
        this.output[0] = input;
        for(int layer = 1; layer < NETWORK_SIZE; layer ++)
        {
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++)
            {
                double sum = bias [layer] [neuron];
                for(int prevNeuron = 0; prevNeuron <  NETWORK_LAYER_SIZES [layer-1]; prevNeuron++ )
                {
                    sum += output [layer-1][prevNeuron] * weights [layer] [neuron] [prevNeuron];
                    
                }
                output[layer][neuron]=sigmoid(sum);
            }
        }
        return output[NETWORK_SIZE-1];
    }
    
    private double sigmoid (double x)
    {
        return 1d / (1 + Math.exp(-x));
    }
    
    public static void main(String[] args)
    {
        Network net = new Network(4,1,3,4);
        double [] output = net.calculate(0.2,0.9,0.3,0.4);
        System.out.println(Arrays.toString(output));

    }


}
