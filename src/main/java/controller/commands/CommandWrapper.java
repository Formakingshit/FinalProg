package controller.commands;

import controller.commands.helper.IParamExtractor;
import controller.commands.helper.RequestParamExtractor;
import controller.exception.ControllerException;
import controller.responce.ErrorViewDispatcher;
import controller.responce.IDispatcher;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static controller.constants.JspPathsConst.ERROR_VIEW;


public abstract class CommandWrapper implements ICommand {

    protected IParamExtractor paramExtractor=new RequestParamExtractor();
    protected IParamExtractor mappedParamExtractor=new RequestParamExtractor();

    private final static IDispatcher DEFAULT_ERROR_DISPATCHER =
            new ErrorViewDispatcher(ERROR_VIEW);

    private final static Logger logger=Logger.getLogger(CommandWrapper.class);


    protected abstract IDispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    @Override
    public IDispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            return  processExecute(request,response);
        }
        catch (ApplicationException e) {
            processApplicationException(e, request);
        }
        catch (Exception e) {
            processException(e,request);
        }

        return DEFAULT_ERROR_DISPATCHER;

    }

    /**
     * Returns offset for book search
     * @param request requestObject
     * @param limit limit for book search
     * @return offset for book search
     */
    protected int getOffsetFromRequest(HttpServletRequest request,String offsetParamName,int limit) {
        return Optional.ofNullable(paramExtractor.getIntParamOrNull(request,"page"))
                .map(page->(page-1)*limit)
                .orElse(0);
    }

    /**
     * Returns limit for book search
     * @param request requestObject
     * @return limit for book search
     */
    protected int getLimitFromRequest(HttpServletRequest request,String limitParamName,int defaultLimitValue) {
        return Optional.ofNullable(paramExtractor.getIntParamOrNull(request,limitParamName))
                .orElse(defaultLimitValue);
    }

    /**
     * Returns int value from URI (param is last "/{param} "at URI )
     * @param request request Object
     * @return paramValue
     */
    protected int getMappedIntParamFromRequest(HttpServletRequest request){
        final String LOG_MESSAGE_PARSING_ERROR_INTEGER_PARAMETER_FORMAT
                = "Can't parse Integer parameter";

        String tempParts[]=request.getRequestURI().split("\\?")[0].split("/");
        String intVal=tempParts[tempParts.length-1];

        try {
            return Integer.parseInt(intVal);

        }catch (NumberFormatException e){
            throw new ControllerException()
                    .addMessageKey(LOG_MESSAGE_PARSING_ERROR_INTEGER_PARAMETER_FORMAT)
                    .addLogMessage(LOG_MESSAGE_PARSING_ERROR_INTEGER_PARAMETER_FORMAT);
        }
    }

    private void processException(Exception e, HttpServletRequest request ) {
        logger.error(e.getMessage());
        request.setAttribute("error_message", "Unknown error occurred! "+e.toString());
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        logger.error(e.getLogMessage());
        logger.error(e.toString());
        request.setAttribute("error_message", e.getMessageKey());
        request.setAttribute("error_additional_message", e.getAdditionalMessage());
    }
}
