package es.upm.dit.apsv.msthesis.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Federico A. Fernandez Moreno
 *
 */
public class EMFService {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("transactions-optional");

	private EMFService() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}