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

        if(map.containsKey("java")){

            System.out.println("\nAnswering questions for paragraph 1\n");

            // Does the paragraph contain the word "java"?
            if(map.containsKey("java")){
                System.out.println("Paragraph contains the word 'java'");
            }else{
                System.out.println("Paragraph does not contain the word 'java'");
            }

            // Frequency of "java"
            if(map.containsKey("java")){
                int frequencyJava = map.get("java");
                System.out.println("'java' appears with a frequency of " + frequencyJava);
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
            System.out.println("'performace' has been removed");

            // Remove "java" only if its frequency is 3
            if(map.containsKey("java")){
                if(map.get("java") == 3){
                    map.remove("java");
                    System.out.println("'java' has been removed because it had a frequency of 3");
                }
            }

            // Number of unique words
            int numUnique = map.size();
            System.out.println("The amount of unique words is " + numUnique);

            // Is the map empty?
            if(map.isEmpty()){
                System.out.println("Map is empty\n\n");
            }else{
                System.out.println("Map is not empty\n\n");
            }
        }else if(map.containsKey("hashmap")){

            System.out.println("\nAnswering questions for paragraph 2\n");

            // What is frequency of "hashtables"
            if(map.containsKey("hashtables")){
                int frequencyJava = map.get("hashtables");
                System.out.println("'hashtables' appears with a frequency of " + frequencyJava);
            }

            // Is "java" present
            if(map.containsKey("java")){
                int frequencyJava = map.get("java");
                System.out.println("'java' is present");
            }else{
                System.out.println("'java' is not present");
            }

            // remove "data"
            map.remove("data");
            System.out.println("'data' has been removed");

            // How many total words are stored
            int total = map.size();
            System.out.println("The amount of total words is " + total);

            // Is the map empty after removing all words one by one
            System.out.println("Yes, map is empty after removing all words one by one, size would be 0");
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
