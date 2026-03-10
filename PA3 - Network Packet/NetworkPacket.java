/*  Latrell Kong
    Optimizing Network Packet Encoding
    COP3503 Computer Science 2
    NetworkPacket.java
*/

// importing to store symbol freq, huffman code, list of symbols, and to build huffman tree
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class NetworkPacket
{
    // global maps and queue to use later in methods
    HashMap<String, Integer> stringFreq = new HashMap<>();
    PriorityQueue<Node> huffmanQueue = new PriorityQueue<>();
    HashMap<String, String> binCodes = new HashMap<>();

    // creating node class for huffman tree
    class Node implements Comparable<Node>
    {
        String symbol;
        int frequency;
        Node left;
        Node right;

        // creaqting node with name and frequency
        public Node(String symbol, int freq)
        {
            this.symbol = symbol;
            this.frequency = freq;
            this.left = null;
            this.right = null;
        }

        // creating node with no name or frequency
        public Node()
        {
            this.symbol = null;
            this.frequency = 0;
            this.left = null;
            this.right = null;
        }

        // comparable method for min priority queue
        public int compareTo(Node compare)
        {
            if((this.frequency - compare.frequency) == 0)
            {
                return 0;
            }
            else if((this.frequency - compare.frequency) > 0)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }

    // method that keeps track of frequencies of each symbol from the file
    public void analyzeFrequencies(ArrayList<String> frequencies)
    {
        // parsing through all symbols
        for(int i = 0; i < frequencies.size(); i++)
        {
            // if already in hashmap, adds 1 to frequency
            if(stringFreq.containsKey(frequencies.get(i)))
            {
                int tempValue = stringFreq.get(frequencies.get(i));
                stringFreq.put(frequencies.get(i), ++tempValue);
            }
            // if not found, adds it to hashmap, and sets frequency to 1
            else
            {
                stringFreq.put(frequencies.get(i), 1);
            }
        }
    }

    // method to recursively find binary code
    public void findCodes(Node temp, String tempCode)
    {   
        // returning if temp does not exist
        if(temp == null)
        {
            return;
        }

        // if the node has symbol, place symbol with code in code hashmap
        if(temp.symbol != null)
        {
            if(tempCode.equals(""))
            {
                tempCode = "0";
            }

            binCodes.put(temp.symbol, tempCode);
            return;
        }
        
        // recursive call for left and right child if not a leaf symbol node
        findCodes(temp.left, tempCode + "0");
        findCodes(temp.right, tempCode + "1");
    }

    // method to build huffman tree
    public void buildHuffmanTree()
    {
        // parsing through all symbols in frequency hashmap
        for (String i : stringFreq.keySet())
        {
            // creating a node for every symbol, and adding it into the priority queue
            Node temp = new Node(i, stringFreq.get(i));
            huffmanQueue.add(temp);
        }
        
        // while there is at least 2 nodes in priority queue, do huffman tree algorithm
        while(huffmanQueue.size() > 1)
        {
            Node z = new Node();
            Node left = huffmanQueue.poll();
            Node right = huffmanQueue.poll();

            // z gets combined frequency of first 2 min values, and gets inserted back into queue
            z.left = left;
            z.right = right;
            z.frequency = left.frequency + right.frequency;
            huffmanQueue.add(z);
        }

        // get root node and find all binary codes
        Node temp = huffmanQueue.peek();
        String tempCode = "";
        findCodes(temp, tempCode);
    }

    // method that returns the binary string for all symbols
    public String encode(ArrayList<String> frequencies)
    {
        String binaryCode = "";

        // parsing through symbols and getting binary code for each, then adding it to string
        for(int i = 0; i < frequencies.size(); i++)
        {
            binaryCode += binCodes.get(frequencies.get(i));
        }

        return binaryCode;
    }

    // method to get average number of bits per symbol
    public Double getHuffmanAvg(ArrayList<String> frequencies)
    {
        int totalBits = 0;

        // parsing through all symbols, and adding to sum the code length times frequency
        for (String symbol : stringFreq.keySet())
        {
            totalBits += stringFreq.get(symbol) * binCodes.get(symbol).length();
        }

        return (double) totalBits / frequencies.size();
    }

    // method to compute compression ratio
    public double getRatio(double avg)
    {
        // using math to get compression ratio
        int numUnique = stringFreq.size();
        double fixAvg = Math.log(numUnique) / Math.log(2);
        fixAvg = Math.ceil(fixAvg);

        return fixAvg / avg;
    }
}
