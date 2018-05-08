package controller;

//import org.apache.log4j.Logger;

import controller.commands.ICommand;
import controller.responce.IDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet{
  //  private static final Logger logger=Logger.getLogger(FrontController.class);
    private static String deployPath;

    @Override
    public void init(){
        deployPath=getServletContext().getContextPath();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (Exception e){
    //        logger.error(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (Exception e){
    //        logger.error(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = request.getMethod() +":"+request.getRequestURI();

            /*remove contextPath from requested url*/
            url= url.replace(deployPath,"");

    //        logger.info("URI requested " + url);

            ICommand command = CommandFabric.getInstance().getCommand(url);
            IDispatcher dispatcher = command.execute(request, response);

            dispatcher.dispatch(request, response);
        }
        catch (ServletException|IOException e){
    //        logger.error("Dispatch error occurred. "+e.toString());
            throw e;
        }
    }
}
