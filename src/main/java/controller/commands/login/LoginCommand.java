package controller.commands.login;

import controller.commands.ICommand;
import controller.responce.ForwardViewDispatcher;
import controller.responce.IDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements ICommand {
    private static final String VIEW_PATH="/WEB-INF/view";
    private static final String VIEW_EXTENSION=".jsp";
    public static final String LOGIN_REG_VIEW = VIEW_PATH+"/loginPage"+VIEW_EXTENSION;

    @Override
    public IDispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ForwardViewDispatcher(LOGIN_REG_VIEW);
    }
}
