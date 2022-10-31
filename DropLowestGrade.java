import java.util.Scanner;

	public class DropLowestGrade 
	{
		public static String[] studentList = {"Ann", "Bob", "Cara", "Don", "Eve"}; // Array of student names
		public static void main(String[] args) 
		{
			Scanner input = new Scanner(System.in);
			
			//Variable declarations
			int[][] studentTestScores = new int[studentList.length][]; //2D Array 
			int[] finalGrades = new int[studentList.length];
			double studentAverage;
			int lowestGrade;
			char letterGrades;
			double finalAverage;
			char finalLetter;
			
			for (int i = 0; i < studentList.length; i++)
			{
				System.out.println("Please enter test scores (0 - 100) for " + studentList[i]);
				studentTestScores[i] = getStudentTestScores();
				studentAverage = calculateAverageGrade(studentTestScores[i]);
				letterGrades = assignLetterGrade(studentAverage);
				lowestGrade = findLowestGrade(studentTestScores[i]);
				finalGrades = removeLowestGrade(studentTestScores[i], lowestGrade);
				finalAverage = calculateAverageGrade(finalGrades);
				finalLetter = assignLetterGrade(finalAverage);
				displayStudentGradeReport(studentList[i], studentTestScores[i], studentAverage, letterGrades, lowestGrade, finalAverage, finalLetter);
			} 
		} // End of main 
		
		// Gets student test scores through user input
		// Returns an array of integers
		public static int[] getStudentTestScores()
		{
			Scanner input = new Scanner(System.in);
			int[] studentTestScores = new int[5];
			
			for (int i = 0; i < studentTestScores.length; i++)
			{
				System.out.print("Test #   " + (i + 1) + ": ");
				studentTestScores[i] = input.nextInt();
			}
			
			return studentTestScores;
		}
		
		// Calculates average grade
		// Returns average grade as double
		public static double calculateAverageGrade(int[] studentTestScores)
		{
			float total = 0;
			double studentAverage;
			
			for (int i = 0; i < studentTestScores.length; i++)
			{
				total += studentTestScores[i];
			}
			studentAverage = (total / studentTestScores.length);
			
			return studentAverage;
		}
		
		// Finds lowest grade
		// Returns lowest grade as integer 
		public static int findLowestGrade(int[] studentTestScores)
		{
			int lowestGrade = studentTestScores[0];
			
			for (int i = 1; i < studentTestScores.length; i++)
			{
				if (lowestGrade >= studentTestScores[i])
				{
					lowestGrade = studentTestScores[i];
				}
			}
			return lowestGrade;
		}
		
		// Removes lowest grade 
		// Returns final grades as array of integers
		public static int[] removeLowestGrade(int[] studentTestScores, int lowestGrade) 
		{
			int[] finalGrades = new int[studentTestScores.length - 1];
			boolean droppedGrade = false;
			
			for(int i = 0; i < studentTestScores.length; i++)
			{
				if(studentTestScores[i] == lowestGrade && !droppedGrade)
				{
					droppedGrade = true;
					continue;
				}
				// If droppedGrade is true, subtract 1 from i
				finalGrades[droppedGrade ? i - 1 : i] = studentTestScores[i];
			}
			return finalGrades;
		}
		
		// Assigns letter grade based on studentAverage grades 
		// Returns letterGrade as char 
		public static char assignLetterGrade(double studentAverage) 
		{
			char letterGrade = 'F';
			
			if(studentAverage >= 90)
				letterGrade = 'A';
			else if(studentAverage >= 80)
				letterGrade = 'B';
			else if(studentAverage >= 70)
				letterGrade = 'C';
			else if (studentAverage >= 60)
				letterGrade = 'D';

			return letterGrade;
		}
		
		// Displays student grade report in its entirety
		public static void displayStudentGradeReport(String studentName, int[] studentTestScores,
			double studentAverage, char letterGrades, int lowestGrade, double finalAverage, char finalLetter)
		{
			System.out.printf("%81s%26s%n", "Before Drop", "After Drop");
			System.out.printf("%-17s%-11s%-10s%-10s%-10s%-10s%-10s%-9s%-8s%-9s%-12s%n", "StudentName", "Test 1",
				"Test 2", "Test 3", "Test 4", "Test 5", "Average", "Grade", "Lowest", "Average", "Grade");
			System.out.printf("%-8s%15d%11d%10d%10d%10d%10.1f%9c%10d%9.1f%6c%n", studentName, studentTestScores[0], 
				studentTestScores[1], studentTestScores[2], studentTestScores[3], studentTestScores[4],
				studentAverage, letterGrades, lowestGrade, finalAverage, finalLetter);
			System.out.println();
		}
	}
