/*

  Simon Hartcher
  C3185790

  SENG2050 Assignment 2

 */

package game;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * GameController controls the state of the game
 * that is rendered to the user
 */
@WebServlet(urlPatterns = { "" })
public class GameController extends HttpServlet {

  /**
   * Standard servlet get method
   */
  public void doGet(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    //if there is no session object we need to start a new game
    HttpSession session = req.getSession();
    if (session.getAttribute("game") == null) doNewGame(session);

    //return the index view
    doIndexView(req, res);
  }

  /**
   * Standard servlet post method
   */
  public void doPost(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    //get the session object
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

    //render index view
    doIndexView(req, res);
  }

  /**
   * Renders the index view
   */
  private void doIndexView(HttpServletRequest req, HttpServletResponse res) 
    throws IOException, ServletException
  {
    //set the page title
    req.setAttribute("title", "Deal or No Deal");

    //get the jsp
    RequestDispatcher view = req.getRequestDispatcher("index.jsp");

    //forward the request
    view.forward(req, res);
  }

  /**
   * Creates a new game of DealOrNoDeal
   */
  private void doNewGame(HttpSession session) {
    DealOrNoDeal game = new DealOrNoDeal();
    session.setAttribute("game", game);
  }
}