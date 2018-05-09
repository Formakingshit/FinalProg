package controller.commands;

import controller.responce.ErrorViewDispatcher;
import controller.responce.RedirectDispatcher;
import controller.responce.IDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.constants.JspPathsConst.ERROR_VIEW;

public class GoInvalidUrlCommand implements ICommand {


    private static final String REQUESTED_UNSUPPORTED_URI = "Requested unsupported URI.";

    @Override
    public IDispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            String redirectPage = request.getContextPath() + "/login";
            return new RedirectDispatcher(redirectPage);
        }

        request.setAttribute("error", REQUESTED_UNSUPPORTED_URI);

        return new ErrorViewDispatcher(ERROR_VIEW);
    }
}
