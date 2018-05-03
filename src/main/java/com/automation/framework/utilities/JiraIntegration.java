package com.automation.framework.utilities;

import java.io.File;
import org.testng.ITestResult;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

/**
 * @author shwetankvashishtha
 *
 */
public class JiraIntegration {

	PropertyManager propertyManager = new PropertyManager();

	String JiraUsername = propertyManager.getResourceBundle.getProperty("JIRA_USERNAME");
	String JiraPassword = propertyManager.getResourceBundle.getProperty("JIRA_PASSWORD");
	String JiraUrl = propertyManager.getResourceBundle.getProperty("JIRA_URL");
	BasicCredentials creds = new BasicCredentials(JiraUsername, JiraPassword);
	JiraClient jira = new JiraClient(JiraUrl, creds);

	public void createNewJiraIssue(ITestResult result, String projectName, String defectType, String defectSummary,
			String defectDescription, String defectReporter, String defectAssignee) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {

				/* Create new issue */
				Issue newIssue = jira.createIssue(projectName, defectType).field(Field.SUMMARY, defectSummary)
						.field(Field.DESCRIPTION, defectDescription).field(Field.REPORTER, defectReporter)
						.field(Field.ASSIGNEE, defectAssignee).execute();
			}
		} catch (JiraException ex) {
			System.err.println(ex.getMessage());

			if (ex.getCause() != null)
				System.err.println(ex.getCause().getMessage());
		}
	}

	public void voteJiraIssue(String JiraTicketId) {
		try {
			Issue issue = jira.getIssue(JiraTicketId);

			/* Vote Jira Issue */
			issue.vote();
		} catch (JiraException ex) {
			System.err.println(ex.getMessage());

			if (ex.getCause() != null)
				System.err.println(ex.getCause().getMessage());
		}
	}

	public void openJiraIssue(String JiraTicketId, String defectAssignee) {
		try {
			Issue issue = jira.getIssue(JiraTicketId);

			/* Open Jira Issue */
			issue.transition().field(Field.ASSIGNEE, defectAssignee).execute("Open");
		} catch (JiraException ex) {
			System.err.println(ex.getMessage());

			if (ex.getCause() != null)
				System.err.println(ex.getCause().getMessage());
		}
	}

	public void closeJiraIssue(String JiraTicketId) {
		try {
			Issue issue = jira.getIssue(JiraTicketId);

			/* Close Jira Issue */
			issue.transition().execute("Close");
		} catch (JiraException ex) {
			System.err.println(ex.getMessage());

			if (ex.getCause() != null)
				System.err.println(ex.getCause().getMessage());
		}
	}

	public void addAttachmentToIssue(ITestResult result, String JiraTicketId, String defectAttachment) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				Issue issue = jira.getIssue(JiraTicketId);
				issue = jira.getIssue(JiraTicketId);
				File file = new File(defectAttachment);

				/* Add attachment to Jira Issue */
				issue.addAttachment(file);
			}
		} catch (JiraException ex) {
			System.err.println(ex.getMessage());

			if (ex.getCause() != null)
				System.err.println(ex.getCause().getMessage());
		}
	}
}
