package controller;

import controller.commands.GoInvalidUrlCommand;
import controller.commands.ICommand;
import controller.commands.login.LoginCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandFabric {

    private final static String GET_PATH = "GET:";
    private final static String POST_PATH = "POST:";
    private final ICommand invalidUrlCommand;
    private final Map<String, ICommand> commands;
    private final Map<String, ICommand> mappedCommands;

    /**
     * Must be private
     */
    private CommandFabric() {
        this.invalidUrlCommand = new GoInvalidUrlCommand();
        commands = new HashMap<>();
        mappedCommands = new HashMap<>();
        //   initRegularCommand();
        //   initMappedCommand();
    }

    /**
     * Lazy instance holder
     */
    private static class InstanceHolder {
        static CommandFabric INSTANCE = new CommandFabric();
    }

    /**
     * Static method
     *
     * @return singleton instance
     */
    public static CommandFabric getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Returns Command
     *
     * @param url requested url
     * @return appropriate Command object
     */
    ICommand getCommand(String url) {
        return new LoginCommand();
    }
}
