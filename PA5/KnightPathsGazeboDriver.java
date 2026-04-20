/*	INSERT STUDENT NAME
	Shade Before Hydration: Guiding Knights Across Campus
	COP3503 Computer Science 2
	KnightPathsGazeboDriver.java
	Compile: javac KnightPathsGazeboDriver.java
	Run: java KnightPathsGazeboDriver [CASE[INSERTNUMBER].in]
*/

import java.io.*;
import java.util.*;

public class KnightPathsGazeboDriver
{
    public static void main(String[] args) throws Exception 
	{		
		KnightPathsGazebo testcase = new KnightPathsGazebo();
		
		String testcasefile = "case" + args[0] + ".in";
		
		KnightPathsGazebo.Graph g = testcase.buildGraph(testcasefile);
		
		if(g == null)
		{
			System.out.println("No such path exists!");
			return;
		}
		
		//BFS from source
		int [] d = new int[g.n + 1];
		int [] pi = new int[g.n + 1];
		testcase.bfs(g, g.s, d, pi);
		
		ArrayList<Integer> path = testcase.computePath(g, d, pi);
		
		if(path != null)
		{
			System.out.println(testcase.getBestDistance());
			System.out.println(testcase.getBestStation());
			testcase.printPath(path);
		}
		else
			System.out.println("No such path exists!");
    }
}