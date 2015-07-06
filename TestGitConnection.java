package org.eclipse.egit.github.core.client;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

public class TestGitConnection {

	public static void main(String[] args) {
		try{

		GitHubClient client = new GitHubClient();
		//client.setCredentials("saiy1", "password");
		System.out.println("authentication success");


		RepositoryService service = new RepositoryService();
		for (Repository repo : service.getRepositories("saiy1"))
		{
			System.out.println("owner="+repo.getOwner().getLogin());
		  System.out.println("reponame="+repo.getName());
		  System.out.println(" Watchers: " + repo.getWatchers());
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
