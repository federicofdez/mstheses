package es.upm.dit.apsv.msthesis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.apsv.msthesis.model.MsThesis;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public class MsThesisDAOImpl implements MsThesisDAO {
	
	private static MsThesisDAOImpl instance;
	
	private MsThesisDAOImpl() {
		
	}
	
	public static MsThesisDAOImpl getInstance(){
		if (instance==null)
			instance = new MsThesisDAOImpl();
		return instance;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#createMsThesis(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public MsThesis createMsThesis(String author, String title, String summary,
			String tutor, String secretary, String file, int status, boolean rejected) {
		EntityManager em = EMFService.get().createEntityManager();
		
		MsThesis MsThesis = new MsThesis(author, title, summary, tutor, secretary, file, status, rejected);
		em.persist(MsThesis);
		
		em.close();
		return MsThesis;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#getMsThesis(java.lang.String)
	 */
	@Override
	public MsThesis getMsThesis(String author) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i where author = :author", MsThesis.class);
		q.setParameter("author", author);
		List<MsThesis> MsTheses = q.getResultList();
		
		em.close();
		
		if (MsTheses.size() != 0)
			return MsTheses.get(0);
		else return null;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#getAllMsTheses()
	 */
	@Override
	public List<MsThesis> getAllMsTheses() {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i", MsThesis.class);
		List<MsThesis> MsTheses = q.getResultList();
		
		em.close();
		
		return MsTheses;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#getMsThesesByTutor(java.lang.String)
	 */
	@Override
	public List<MsThesis> getMsThesesByTutor(String tutor) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i where tutor = :tutor", MsThesis.class);
		q.setParameter("tutor", tutor);
		List<MsThesis> MsTheses = q.getResultList();
		
		em.close();
		
		return MsTheses;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#getMsThesesBySecretary(java.lang.String)
	 */
	@Override
	public List<MsThesis> getMsThesesBySecretary(String secretary) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i where secretary = :secretary", MsThesis.class);
		q.setParameter("secretary", secretary);
		List<MsThesis> MsTheses = q.getResultList();
		
		em.close();
		
		return MsTheses;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#getMsThesesByStatus(int)
	 */
	@Override
	public List<MsThesis> getMsThesesByStatus(int status) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i where status = :status", MsThesis.class);
		q.setParameter("status", status);
		List<MsThesis> MsTheses = q.getResultList();
		
		em.close();
		
		return MsTheses;
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#updateMsThesis(es.upm.dit.isst.MsThesis.model.MsThesis)
	 */
	@Override
	public void updateMsThesis(MsThesis MsThesis) {
		EntityManager em = EMFService.get().createEntityManager();
		em.merge(MsThesis);
		em.close();
	}

	/* (non-Javadoc)
	 * @see es.upm.dit.isst.MsThesis.dao.MsThesisDAO#deleteMsThesis(es.upm.dit.isst.MsThesis.model.MsThesis)
	 */
	@Override
	public void deleteMsThesis(MsThesis MsThesis) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT i from MsThesis i where author = :author", MsThesis.class);
		q.setParameter("author", MsThesis.getAuthor());
		MsThesis storedMsThesis = (MsThesis)(q.getResultList().get(0));
		em.remove(storedMsThesis);
		em.close();
	}

}
