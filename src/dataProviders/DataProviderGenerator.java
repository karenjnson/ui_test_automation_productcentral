package dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataProviderGenerator {
	/*  The Data Provider Generator takes a flat file of test data and converts it into a useable 
	 * 		Object.
	 * 
	 * 		INPUT PARAMETERS
	 * 
	 *     		TestCase File:  Specific Test Case File
	 *     			That has the list of Fields that should be included in the data
	 *     
	 *     		AND/OR an ArrayList of Fields to be included in the data
	 *     
	 *     OUTPUTs:
	 *     
	 *     		An Object[][] needed for TESTNG dataProvider functionality
	 */

	//Instance Variables
	String inputTestCaseFileName = "";
	File inputTestCaseFile = new File(inputTestCaseFileName);


	// Constructors
	public DataProviderGenerator ()  throws FileNotFoundException  {
	}

	public DataProviderGenerator (String inputTestCaseFileName) throws FileNotFoundException {
		System.out.println("IN THE DATA GENERATOR:   "+inputTestCaseFileName);
		this.inputTestCaseFileName = inputTestCaseFileName;
		this.inputTestCaseFile = new File(inputTestCaseFileName);
	}

	public Object[][] testData() throws Exception  {
		// Find out the number of lines in the file
		// AND obtain the list of Fields to use to generate the data for the Object
		// The list of fields will retrieved from the line that starts with "FORMAT:"
		Scanner file = new Scanner(inputTestCaseFile);
		
		// Check if the FILE EXIST
		if (! inputTestCaseFile.exists()) {
			System.out.println("The data file does not exist. "+inputTestCaseFileName);
			System.exit(0);
		}

		int count = 0;
		String tline = "";
		String[] theDataFields = {};
		while(file.hasNextLine())
		{
			count++;
			tline = file.nextLine();
			if (tline.startsWith("#FORMAT:",0)) {
				theDataFields = tline.split(",");
			}
		}


		// Create an Object array foreach line in the datafile
		Object[][] theTestData = new Object[count][];

		Scanner input = new Scanner(inputTestCaseFile);
			count = 0;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				System.out.println("Here -> "+line);
				if (! line.startsWith("#", 0) || line.length() == 6) {
					String[] user = line.split(",");
					String os = user[0].trim();
					String browserType = user[1].trim();
					String browserVersion = user[2].trim();
					String width = user[3].trim();
					String height = user[4].trim();
					String osType = user[5].trim();
					
					System.out.println("The values are:  "+os+" "+browserType+" "+browserVersion+" "+width+" "+height+" "+osType);
					
					// Store the values in the Object array
					theTestData[count] = new Object[]{os, browserType, browserVersion, width, height, osType};
					//theTestData[count] = new Object[]{tcDescription, guid};

					//System.out.println("The value again is: "+theTestData[count]);
					count++;
				}
				//System.out.println(theTestData[count-1]);

			}
			file.close();
		return theTestData;

	}

}
