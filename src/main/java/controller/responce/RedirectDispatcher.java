package controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectDispatcher implements IDispatcher {
    private String pageUrl;

    public RedirectDispatcher(String page) {
        pageUrl = page;
    }

    public RedirectDispatcher addGetParam(String param,String value) {
        pageUrl += "?"+param+"="+escapeCharactersInValue(value);
        return this;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath()+ pageUrl);
    }

    private static String escapeCharactersInValue(String val){
        return val.replace(" ","%20");
    }
}
