/*  Latrell Kong
    Optimizing Network Packet Encoding
    COP3503 Computer Science 2
    NetworkPacket.java
*/

import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class NetworkPacket
{
    // global variables
    HashMap<String, Integer> stringFreq = new HashMap<>();
    PriorityQueue<Node> huffmanQueue = new PriorityQueue<>();
    HashMap<String, String> binCodes = new HashMap<>();

    class Node implements Comparable<Node>
    {
        String symbol;
        int frequency;
        Node left;
        Node right;

        public Node(String symbol, int freq)
        {
            this.symbol = symbol;
            this.frequency = freq;
            this.left = null;
            this.right = null;
        }

        public Node()
        {
            this.symbol = null;
            this.frequency = 0;
            this.left = null;
            this.right = null;
        }

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

    public void analyzeFrequencies(ArrayList<String> frequencies)
    {
        for(int i = 0; i < frequencies.size(); i++)
        {
            if(stringFreq.containsKey(frequencies.get(i)))
            {
                int tempValue = stringFreq.get(frequencies.get(i));
                stringFreq.put(frequencies.get(i), ++tempValue);
            }
            else
            {
                stringFreq.put(frequencies.get(i), 1);
            }
        }
    }

    public void findCodes(Node temp, String tempCode)
    {   
        if(temp == null)
        {
            return;
        }
        if(temp.symbol != null)
        {
            binCodes.put(temp.symbol, tempCode);
            return;
        }
        findCodes(temp.left, tempCode + "0");
        findCodes(temp.right, tempCode + "1");
    }

    public void buildHuffmanTree()
    {
        for (String i : stringFreq.keySet())
        {
            Node temp = new Node(i, stringFreq.get(i));
            huffmanQueue.add(temp);
        }
        
        while(huffmanQueue.size() > 1)
        {
            Node z = new Node();
            Node left = huffmanQueue.poll();
            Node right = huffmanQueue.poll();
            z.left = left;
            z.right = right;
            z.frequency = left.frequency + right.frequency;
            huffmanQueue.add(z);
        }

        Node temp = huffmanQueue.peek();
        String tempCode = "";
        findCodes(temp, tempCode);
    }

    public String encode(ArrayList<String> frequencies)
    {
        String binaryCode = "";
        for(int i = 0; i < frequencies.size(); i++)
        {
            binaryCode += binCodes.get(frequencies.get(i));
        }

        return binaryCode;
    }

    public Double getHuffmanAvg(ArrayList<String> frequencies)
    {
        int totalBits = 0;

        for (String symbol : stringFreq.keySet())
        {
            totalBits += stringFreq.get(symbol) * binCodes.get(symbol).length();
        }

        return (double) totalBits / frequencies.size();
    }

    public double getRatio(double avg)
    {
        int numUnique = stringFreq.size();
        double fixAvg = Math.log(numUnique) / Math.log(2);
        fixAvg = Math.ceil(fixAvg);

        return fixAvg / avg;
    }

}
