package gg.bit.utils.matchData.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gg.bit.utils.matchData.dao.ChampionDao;
import gg.bit.utils.matchData.dao.ChampionDaoMongo;
import gg.bit.utils.matchData.vo.ChampionVo;

@WebServlet(name="ChampionData", urlPatterns="/champ" )
public class ChampionServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ChampionDao dao = new ChampionDaoMongo();
		List<ChampionVo> list = dao.getList();

		req.setAttribute("list", list);

		RequestDispatcher rd = 
				getServletContext()
				.getRequestDispatcher("/WEB-INF/views/champ/index.jsp");
		rd.forward(req, resp);
	}
}




