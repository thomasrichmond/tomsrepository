import java.util.Scanner;
public class BlastSafety { 

	/*---------------------------------------
	My name: Thomas Richmond
	My student number: 6427819
	My email address: tr989@uowmail.edu.au
	My subject Code: CSIT111
	Assignment number: 1
	----------------------------------------*/

	/* 
	Pre-design notes 

	Safe Distance = FOS * 11 * sdob^-2.167 * d^0.667
	Factor of Safety = 2.0
	d = borehole diameter (mm)
	sdob = depth of burial (m/kg^1/3)

	sdob calculated as: 
	sdob = (sl + 0.0005 * cclf * d) / (0.00923 * (cclf * d^3 * ED)^0.333)

	sl = stemming length (m)
	ED is explosive density (g/cm^3)
	cclf is contributing charge length factor

	cclf calculated as: 
	cclf = 1000 * cl/d

	where 
	cl = charge length (m)
	d = borehole diameter (mm)

	Explosive density (ED) = 1.2 (g/cm^3) 

	Parameters: 
	When cclf is creater than 10, it is limited to 10. Have to create an IF statement for that in class constants. 
	*/


	//Declare constants so the formula can work with any given set of numbers, also so the formula can't be hard coded
	public static final double EXPLOSIVE_DENSITY = 1.2;
	public static final double FACTOR_OF_SAFETY = 2.0;

	public static void main(String[] args) {

		// Declare variables
		double chargeLength, stemmingLength, boreholeDiameter, contributingChargeLengthFactor, safeDepthOfBurial, blastSafeDistanceDbl;
		int blastSafeDistance;
	
		//User enters the values, this is done using a scanner utility entered above the class
		System.out.println(" - Blast safe distance estimation -");
		System.out.println();
		System.out.println("Enter the blast parameters");
		Scanner user_input = new Scanner(System.in);
		System.out.print("Charge length (m): ");
		chargeLength = user_input.nextDouble();
		System.out.print("Stemming length (m): ");
		stemmingLength = user_input.nextDouble();
		System.out.print("Borehole diameter (mm): ");
		boreholeDiameter = user_input.nextDouble();
		// Close user input to stop resource leakage
		user_input.close();
	
		// Calculating contributingChargeLengthFactor
		contributingChargeLengthFactor = 1000* chargeLength / boreholeDiameter;
		contributingChargeLengthFactor = Math.min(contributingChargeLengthFactor, 10);
		
		// Calculating safeDepthOfBurial
		safeDepthOfBurial = (stemmingLength + (0.0005 * contributingChargeLengthFactor * boreholeDiameter)) / 
		(0.00923 * Math.pow((contributingChargeLengthFactor * Math.pow(boreholeDiameter, 3) * EXPLOSIVE_DENSITY), 0.333));
		
		// Calculating blastSafeDistance
		blastSafeDistanceDbl = (FACTOR_OF_SAFETY * 11 * Math.pow(safeDepthOfBurial, -2.167) * Math.pow(boreholeDiameter, 0.667));
		
		// Creating an integer display for the blastSafeDistance so when you have a double which is over .5 it automatically rounds to the next number
		blastSafeDistance = (int) Math.round(blastSafeDistanceDbl);
		
		// Final result displayed to user
		System.out.println();
		System.out.println("The safe blast distance is " + blastSafeDistance + " meters");
	}

}




