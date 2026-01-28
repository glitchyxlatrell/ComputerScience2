
import java.util.*;

public class WordFrequencyLabStarter {

    // Tokenizer function to split paragraph into words
    public static String[] tokenize(String paragraph) {
        return paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\s+");
    }

    // Function to count word frequencies
    public static void countWords(String[] words, HashMap<String, Integer> map) {
        // TODO: Implement word counting using put and getOrDefault
    }

    // Function to print word frequencies
    public static void printFrequencies(HashMap<String, Integer> map) {
        // TODO: Print each word and its frequency
    }

    // Function to answer questions about the map
    public static void answerQuestions(HashMap<String, Integer> map) {
        // TODO: Use get, containsValue, remove, size, isEmpty, etc.

        // Does the paragraph contain the word "java"?
        
        // Frequency of "java"
        

        // Is there any word with frequency 3?
        

        // Is there any word with frequency 5?
        

        // Remove the word "performance"
        

        // Remove "java" only if its frequency is 3
        

        // Number of unique words
        

        // Is the map empty?
    }

    public static void main(String[] args) {
        String paragraph1 = "Java is a versatile language. Java is used in web development, mobile apps, and enterprise systems. Many developers love Java for its portability and performance.";
        String paragraph2 = "HashTables are powerful. They allow fast access to data. Java\’s HashMap is a popular implementation. Developers use HashTables to store and retrieve information efficiently.";

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
