/*
COP3503C | Spring 2026 | Section 0201
Name: Latrell Kong
UCF ID: 5624427
*/
import java.util.*;

public class WordFrequencyLabStarter {

    // Tokenizer function to split paragraph into words
    public static String[] tokenize(String paragraph) {
        return paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
    }

    // Function to count word frequencies
    public static void countWords(String[] words, HashMap<String, Integer> map) {
        // TODO: Implement word counting using put and getOrDefault
        for(int i = 0; i < words.length; i++){
            int count = map.getOrDefault(words[i], 0);
            map.put(words[i], count + 1);
        }
    }

    // Function to print word frequencies
    public static void printFrequencies(HashMap<String, Integer> map) {
        // TODO: Print each word and its frequency
        System.out.println(map);
    }

    // Function to answer questions about the map
    public static void answerQuestions(HashMap<String, Integer> map) {
        // TODO: Use get, containsValue, remove, size, isEmpty, etc.

        // Does the paragraph contain the word "java"?
        if(map.containsKey("java")){
            System.out.println("Paragraph contains the word 'java'");
        }else{
            System.out.println("Paragraph does not contain the word 'java'");
        }

        // Frequency of "java"
        if(map.containsKey("java")){
            int frequencyJava = map.get("java");
            System.out.println("java appears with a frequency of " + frequencyJava);
        }

        // Is there any word with frequency 3?
        if(map.containsValue(3)){
            System.out.println("Paragraph contains a word with frequency 3");
        }else{
            System.out.println("Paragraph does not contain a word with frequency 3");
        }

        // Is there any word with frequency 5?
        if(map.containsValue(5)){
            System.out.println("Paragraph contains a word with frequency 5");
        }else{
            System.out.println("Paragraph does not contain a word with frequency 5");
        }

        // Remove the word "performance"
        map.remove("performance");

        // Remove "java" only if its frequency is 3
        if(map.containsKey("java")){
            if(map.get("java") == 3){
                map.remove("java");
            }
        }

        // Number of unique words
        int numUnique = map.size();
        System.out.println("The amount of unique words is " + numUnique);

        // Is the map empty?
        if(map.isEmpty()){
            System.out.println("Map is empty");
        }else{
            System.out.println("Map is not empty");
        }
    }

    public static void main(String[] args) {
        String paragraph1 = "Java is a versatile language. Java is used in web development, mobile apps, and enterprise systems. Many developers love Java for its portability and performance.";
        String paragraph2 = "HashTables are powerful. They allow fast access to data. Java’s HashMap is a popular implementation. Developers use HashTables to store and retrieve information efficiently.";

        HashMap<String, Integer> wordFreq = new HashMap<>();

        // Process paragraph 1
        String[] words1 = tokenize(paragraph1);
        countWords(words1, wordFreq);
        printFrequencies(wordFreq);
        answerQuestions(wordFreq);

        // Clear map and process paragraph 2
        wordFreq.clear();
        String[] words2 = tokenize(paragraph2);
        countWords(words2, wordFreq);
        printFrequencies(wordFreq);
        answerQuestions(wordFreq);
    }
}
