package org.eclipse.egit.github.core.client;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static FileWriter fileWriter = null;

	public static void createCSVFile(String fullFilePath, String fileHeader) {
		if (fileWriter == null) {

			try {
				fileWriter = new FileWriter(fullFilePath);
				fileWriter.append(fileHeader);
			} catch (IOException e) {
				System.out.println("unable to create filewriter");
				e.printStackTrace();
			}
		}
	}

	public static void addUserDataLineToFile(String userId, String repoName,
			String ownerName) {


		try {

			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(userId);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(repoName);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(ownerName);

			fileWriter.flush();

		} catch (Exception e) {

			System.out.println(" CsvFileWriter exception");

			e.printStackTrace();

		}

	}

	public static void addTeamDataLineToFile(String repoCount, String teamName,
			String accessLevel) {


		try {

			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(repoCount);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(teamName);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(accessLevel);

			fileWriter.flush();

		} catch (Exception e) {

			System.out.println(" CsvFileWriter exception");

			e.printStackTrace();

		}

	}

	public static void closeCSVFile() {
		try {
			if (fileWriter != null) {
				fileWriter.flush();
				fileWriter.close();
			}

		} catch (Exception e) {

			System.out.println("Error while closing filewriter");

			e.printStackTrace();

		}
	}

}
