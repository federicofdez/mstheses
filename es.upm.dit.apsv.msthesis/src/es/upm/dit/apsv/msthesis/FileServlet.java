package es.upm.dit.apsv.msthesis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.upm.dit.apsv.msthesis.dao.MsThesisDAO;
import es.upm.dit.apsv.msthesis.dao.MsThesisDAOImpl;
import es.upm.dit.apsv.msthesis.model.MsThesis;

/**
 * 
 * @author Federico A. Fernández Moreno
 *
 */
public class FileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		MsThesis msthesis = dao.getMsThesis((String) req.getParameter("author"));

		if (msthesis != null && msthesis.getStatus() == 2) {
			Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory
					.getBlobstoreService().getUploads(req);
			List<BlobKey> blobKeys = blobs.get("file");
			if (blobKeys == null || blobKeys.isEmpty()
					|| blobKeys.get(0) == null) {
				resp.sendError(1200);
			}
			msthesis.setFile(blobKeys.get(0).getKeyString());
			msthesis.setStatus(3);
			dao.updateMsThesis(msthesis);
			resp.sendRedirect("/myMsTheses");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getUserPrincipal() == null) {
			resp.sendRedirect("/");
		}
		MsThesisDAO dao = MsThesisDAOImpl.getInstance();
		MsThesis msthesis = dao.getMsThesis((String) req.getParameter("author"));

		BlobKey blobKey = new BlobKey(msthesis.getFile());
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();

		blobstoreService.serve(blobKey, resp);
	}

}
