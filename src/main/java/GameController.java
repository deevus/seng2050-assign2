package game;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = { "" })
public class GameController extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    HttpSession session = req.getSession();
    if (session.getAttribute("game") == null) {
      DealOrNoDeal game = new DealOrNoDeal();
      session.setAttribute("game", game);
    }

    doIndexView(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    HttpSession session = req.getSession();

    //get game board
    DealOrNoDeal game = (DealOrNoDeal)session.getAttribute("game");
    if (game.isOfferRound()) {

      boolean deal = new Boolean(req.getParameter("deal"));
      game.acceptOffer(deal);

    } 
    else {

      //get id
      int id = Integer.parseInt(req.getParameter("id"));

      //open briefcase
      game.open(id);

    }

    doIndexView(req, res);
  }

  private void doIndexView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    RequestDispatcher view = req.getRequestDispatcher("index.jsp");
    view.forward(req, res);
  }
}