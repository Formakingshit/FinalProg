package controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardViewDispatcher implements IDispatcher{
    private final String VIEW;

    public ForwardViewDispatcher(String view) {
        VIEW = view;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request,response);
    }
}
