/*  Latrell Kong
    Eustis Setup
    COP3503 Computer Science 2
    EustisSetup.java
*/

public class EustisSetup{

    public void printMessages(){
        System.out.println("The current month is January.");
        System.out.println("The current year is 2026.");
        System.out.println("I am a CS2 student this semester!");
        System.out.println("I am so excited to learn more algorithms and advanced data structures!");
        System.out.println("Stranger Things Season 5 came out recently on Netflix!");
    }

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







}