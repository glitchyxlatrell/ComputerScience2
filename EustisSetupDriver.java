/*	INSERT STUDENT NAME
	Eustis Setup
	COP3503 Computer Science 2
	EustisSetupDriver.java
	Compile: javac EustisSetupDriver.java
	Run: java EustisSetupDriver [CASE]
*/

public class EustisSetupDriver
{
	public static void main(String args[])
	{
		int testCase = Integer.parseInt(args[0]);
		EustisSetup obj = new EustisSetup();
		
		switch(testCase)
		{
			case 1:
			
				obj.printMessages();
				break;
				
			case 2:
			
				//scenario 1
				int score = obj.computeScore(10, 1.0, 50);
				System.out.println("Mario's score is " + score);
				
				//scenario 2
				score = obj.computeScore(12, 1.5, 50);
				System.out.println("Mario's score is " + score);
				
				//scenario 3
				score = obj.computeScore(0, 2.0, 100);
				System.out.println("Mario's score is " + score);
				
				//scenario 4
				score = obj.computeScore(23, 2.0, 0);
				System.out.println("Mario's score is " + score);

				//scenario 5
				score = obj.computeScore(25, 3.0, 100);
				System.out.println("Mario's score is " + score);					
				break;
				
			case 3:
				
				//scenario 1
				EustisSetup p1 = new EustisSetup("Pikachu", 25, 30.0);
				
				//scenario 2
				EustisSetup p2 = new EustisSetup("Charizard", -120, 45.5);
				
				//scenario 3
				EustisSetup p3 = new EustisSetup("Magikarp", 0, -10.0);
				
				//scenario 4
				EustisSetup p4 = new EustisSetup("Venusaur", 65, 180.0);
				
				//scenario 5
				EustisSetup p5 = new EustisSetup("Blastoise", 156, 224.0);
				
				EustisSetup pokemon [] = {p1, p2, p3, p4, p5};
				
				for (EustisSetup p : pokemon)
					System.out.println(p.toString());

				break;
				
			case 4:
			
				obj.category();
				break;
				
			default:
				System.out.println("Invalid Test Case...");
		}
	}
}