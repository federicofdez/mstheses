package es.upm.dit.apsv.msthesis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
@Entity
public class MsThesis implements Serializable {

	// since it is Serializable
	private static final long serialVersionUID = 13552091624950200L;

	private String author;
	private String title;
	private String summary;
	private String tutor;
	private String secretary;
	private String file;
	private int status;
	private boolean rejected;

	public MsThesis(String author, String title, String summary, String tutor,
			String secretary, String file, int status, boolean rejected) {
		this.author = author;
		this.title = title;
		this.summary = summary;
		this.tutor = tutor;
		this.secretary = secretary;
		this.file = file;
		this.status = status;
		this.rejected = rejected;
	}

	/**
	 * @return the author
	 */
	@Id
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @return the tutor
	 */
	public String getTutor() {
		return tutor;
	}

	/**
	 * @return the secretary
	 */
	public String getSecretary() {
		return secretary;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @return whether it's rejected
	 */
	public boolean isRejected() {
		return rejected;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @param tutor
	 *            the tutor to set
	 */
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	/**
	 * @param secretary
	 *            the secretary to set
	 */
	public void setSecretary(String secretary) {
		this.secretary = secretary;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @param rejected
	 * 			  the rejected to set
	 */
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

}