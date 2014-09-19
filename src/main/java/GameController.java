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
    if (session.getAttribute("game") == null) doNewGame(session);

    doIndexView(req, res);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    HttpSession session = req.getSession();

    //game is over - player has requested new game
    if (req.getParameterValues("newgame") != null || session.getAttribute("game") == null) {

      doNewGame(session);

    }
    else {

      //get game board
      DealOrNoDeal game = (DealOrNoDeal)session.getAttribute("game");

      //if its an offer round
      if (game.isOfferRound() && (req.getParameterValues("id") == null)) {

        try {
          boolean deal = new Boolean(req.getParameter("deal"));
          game.acceptOffer(deal);
        }
        catch (Exception e) {
          //we don't need to do anything here
          //game board will not be changed
        }

      } 
      //normal round
      else {

        try {

          //get id
          int id = Integer.parseInt(req.getParameter("id"));

          //open briefcase
          game.open(id);

        }
        catch (Exception e) {
          //we don't need to do anything here
          //game board will not be changed
        }

      }

    }

    doIndexView(req, res);
  }

  private void doIndexView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    req.setAttribute("title", "Deal or No Deal");
    RequestDispatcher view = req.getRequestDispatcher("index.jsp");
    view.forward(req, res);
  }

  private void doNewGame(HttpSession session) {
    DealOrNoDeal game = new DealOrNoDeal();
    session.setAttribute("game", game);
  }
}