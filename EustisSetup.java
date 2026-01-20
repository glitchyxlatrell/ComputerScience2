/*  Latrell Kong
    Eustis Setup
    COP3503 Computer Science 2
    EustisSetup.java
*/

// imports
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class EustisSetup{

    // problem 1 - simple print statements
    public void printMessages(){
        System.out.println("The current month is January.");
        System.out.println("The current year is 2026.");
        System.out.println("I am a CS2 student this semester!");
        System.out.println("I am so excited to learn more algorithms and advanced data structures!");
        System.out.println("Stranger Things Season 5 came out recently on Netflix!");
    }

    // problem 2 - computing 
    public int computeScore(int numCoins, double multiplier, int bonus){
        double score = numCoins * 10 * multiplier;
        score += bonus;

        score += 0.5;
        int finalScore = (int)(score);

        return finalScore;
    }

    private String pokemonName;
    private int pokemonLevel;
    private double pokemonPower;

    public EustisSetup(){
        this.pokemonName = null;
        this.pokemonLevel = 1;
        this.pokemonPower = 0.0;
    }

    public EustisSetup(String name, int level, double power){
        this.pokemonName  = name;

        if(level < 1){
            this.pokemonLevel = 1;
        }else if(level > 100){
            this.pokemonLevel = 100;
        }else{
           this.pokemonLevel = level;
        }

        if(power < 0){
            this.pokemonPower = 0;
        }else{
           this.pokemonPower = power;
        }
    }

    public String toString(){
       return "Pokemon{name = '" + this.pokemonName + "', level = " + this.pokemonLevel + ", power = " + this.pokemonPower + "}";
    }

    public void category(){

        Scanner fileIn = null;

        try{
            fileIn = new Scanner(new File("movies.in"));
        }catch (FileNotFoundException e) {
				System.out.println("Error accessing input file");
				return;
			}

        String title = null;
        double budget = 0.0;
        double gross = 0.0;
        double roi = 0.0;

        DecimalFormat truncating = new DecimalFormat("0.0#");
        DecimalFormat roiFormat = new DecimalFormat("0.00");
        int numMovies = 0;
        double roiTotal = 0.0;
        double roiMax = 0.0;
        String titleMax = null;

        int numPoor = 0;
        int numUnderPer = 0;
        int numBreakEven = 0;
        int numHit = 0;
        int numBlockBust = 0;

        while(fileIn.hasNextLine()){
            String movie = fileIn.nextLine();
            String[] info = movie.split(" ");

            title = info[0];
            title = title.replace('_',' ');

            budget = Double.parseDouble(info[1]);
            gross = Double.parseDouble(info[2]);
            roi = gross / budget;
            roi = Math.round(roi * 100.0) / 100.0;

            numMovies++;
            roiTotal += roi;
            if(roi > roiMax){
                titleMax = title;
                roiMax = roi;
            }

            if(roi >= 2){
                numBlockBust++;
            }else if(roi >= 1.3){
                numHit++;
            }else if(roi >= 1.1){
                numBreakEven++;
            }else if(roi >= 0.8){
                numUnderPer++;
            }else{
                numPoor++;
            }

            System.out.println("-----------------------------------");
            System.out.println("Title: " + title);
            System.out.println("Budget: " + truncating.format(budget));
            System.out.println("Gross: " + truncating.format(gross));
            System.out.println("ROI: " + roiFormat.format(roi));
            System.out.println("-----------------------------------");
        }

        fileIn.close();

        double roiAverage = roiTotal / numMovies;
        System.out.println("Movies loaded: " + numMovies);
        System.out.println("Average ROI: " + roiFormat.format(roiAverage));
        System.out.println("Top ROI: " + titleMax + " (" + roiFormat.format(roiMax) + ")");
        System.out.println("Poor: " + numPoor + " | Underperformer: " + numUnderPer + " | Break Even: " + numBreakEven + " | Hit: " + numHit + " | Blockbuster: " + numBlockBust);
    }


}