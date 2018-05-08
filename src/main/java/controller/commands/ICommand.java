package controller.commands;

import controller.responce.IDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand{
    IDispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
