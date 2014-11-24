package com.hp.devops.demoapp;

import org.json.JSONArray;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 23/11/14
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class RestServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		DataManager.init(servletContext);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathNodes = Utils.nodify(request.getRequestURI());
		if (pathNodes.length == 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "path too short");
		} else if (pathNodes[0].compareTo("bands") == 0) {
			serveBands(pathNodes, response);
		}
	}

	private void serveBands(String[] pathNodes, HttpServletResponse response) throws IOException {
		JSONArray resBody = new JSONArray();
		response.setContentType("application/json");
		try {
			if (pathNodes.length == 1) {
				for (Band band : DataManager.getAll()) {
					resBody.put(band.toJSON());
				}
			}
			response.getOutputStream().print(resBody.toString());
			response.flushBuffer();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void destroy() {
	}
}
