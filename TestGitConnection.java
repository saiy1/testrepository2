package org.eclipse.egit.github.core.client;

import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.Team;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.service.OrganizationService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.TeamService;

public class TestGitConnection {
	private static final String USER_DATA_HEADER = "USER_ID,REPO,OWNER,ACCESS_LEVEL";
	private static final String REPOSITORY_DATA_HEADER = "REPOSITORY_NAME,TEAM,ACCESS_LEVEL";
	public static String outputFileUserData="C://home//github_report_users.txt";
	public static String outputFileTeamData="C://home//github_report_team_data.txt";

	public static void main(String[] args) {

		if(args.length>0)
		{
			outputFileUserData=args[0];
			System.out.println("output file is = "+outputFileUserData);
		}
		else
		{
			System.out.println("output file is not passed, writing to "+outputFileUserData);
		}

		GitHubClient client = new GitHubClient("https://github.com/");
		client.setCredentials("user", "password");
		System.out.println("authentication success");
		// UserService userService = new UserService(client);

		OrganizationService orgService = new OrganizationService(client);
		TestGitConnection gitCOnnector=new TestGitConnection();
		gitCOnnector.createUserData(outputFileUserData,orgService);
		//gitCOnnector.createTeamData(outputFileTeamData,orgService,client);

	}

	private void createUserData(String userDataFile,OrganizationService orgService)
	{
		try {

			String userId = "";
			String repoName = "";
			String ownerName = "";


			// get users of organization
			List<User> userList = orgService.getMembers("wells");
			WriteToFile.createCSVFile(userDataFile,USER_DATA_HEADER);// create csv file
			for (User user : userList) {
				userId = user.getName();
				RepositoryService service = new RepositoryService();
				// get repositories of each user
				List<Repository> repList = service.getRepositories(userId);
				for (Repository repo : repList) {
					repoName = repo.getName();
					ownerName = repo.getOwner().getName();
					TeamService teamService=new TeamService();
					
				}
				// write line to file
				WriteToFile.addUserDataLineToFile(userId, repoName, ownerName);
			}

			// close file
			WriteToFile.closeCSVFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createTeamData(String userDataFile,OrganizationService orgService,GitHubClient client)
	{
		try {


			String repoCount = "";
			String teamName = "";
			String accessLevel = "";
			WriteToFile.createCSVFile(userDataFile,REPOSITORY_DATA_HEADER);// create csv file
				RepositoryService service = new RepositoryService();
					TeamService teamService=new TeamService(client);
					// get team list
					List<Team> teamList=teamService.getTeams("wells");
					for (Team team : teamList) {
						teamName=team.getName();
						accessLevel=team.getPermission();
						repoCount=team.getReposCount()+"";

					}
					// write line to file
					WriteToFile.addTeamDataLineToFile(repoCount, teamName, accessLevel);

			// close file
			WriteToFile.closeCSVFile();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
