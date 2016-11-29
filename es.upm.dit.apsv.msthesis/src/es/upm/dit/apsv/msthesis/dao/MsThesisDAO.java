package es.upm.dit.apsv.msthesis.dao;

import java.util.List;

import es.upm.dit.apsv.msthesis.model.MsThesis;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public interface MsThesisDAO {
	
	//CREATE methods
	public MsThesis createMsThesis(String author, String title, String summary,
			String advisor, String secretary, String file, int status, boolean rejected);

	//READ methods
	public MsThesis getMsThesis(String author);
	
	public List<MsThesis> getAllMsTheses();
	
	public List<MsThesis> getMsThesesByAdvisor(String advisor);
	
	public List<MsThesis> getMsThesesBySecretary(String secretary);
	
	public List<MsThesis> getMsThesesByStatus(int status);
	
	public List<MsThesis> getMsThesesRejected();
	
	//UPDATE methods
	public void updateMsThesis(MsThesis MsThesis);
	
	//DELETE methods
	public void deleteMsThesis(MsThesis MsThesis);
	
}
