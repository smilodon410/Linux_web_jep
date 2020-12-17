package gg.bit.utils.matchData.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gg.bit.utils.matchData.dao.TeamDao;
import gg.bit.utils.matchData.dao.TeamDaoMongo;
import gg.bit.utils.matchData.vo.TeamVo;

// Controller 역할을 담당하는 Servlet
// web.xml 내에 servlet 과 servlet-mapping 을 등록한 것과 동일한 효과
@WebServlet(name="TeamMatchDataList", urlPatterns="/team")
public class TeamDataServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String action = req.getParameter("a");
	
		if ("winner".equals(action)) {
			TeamDao dao = new TeamDaoMongo();
			List<TeamVo> list = dao.getList("winner");
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = 
					getServletContext()
						.getRequestDispatcher("/team/plot");
			rd.forward(req, resp);
			
		} else if ("loser".equals(action)) {
			TeamDao dao = new TeamDaoMongo();
			List<TeamVo> list = dao.getList("loser");
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = 
					getServletContext()
						.getRequestDispatcher("/team/plot");
			rd.forward(req, resp);
		} else {
			TeamDao dao = new TeamDaoMongo();
			List<TeamVo> list = dao.getList("winner");
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = 
					getServletContext()
						.getRequestDispatcher("/WEB-INF/views/winner/index.jsp");
			rd.forward(req, resp);
		}
	}
	
}
