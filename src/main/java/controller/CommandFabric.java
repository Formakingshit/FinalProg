package controller;

import controller.commands.GoInvalidUrlCommand;
import controller.commands.ICommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static controller.constants.UrlsConst.ADMIN_REPORT;
import static controller.constants.UrlsConst.MAPPED_STATIC;

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
        //get not mapped command
        ICommand command = getSimpleCommand(url);

        if (command != null)
            return command;

        //get mapped command
        command = getMappedCommand(url);

        if (command != null)
            return command;

        //possible GET params passed
        return invalidUrlCommand;

    }

    /**
     * Initialize CommandFabric object
     */
  /*  private void initMappedCommand() {
        mappedCommands.put(GET_PATH + ADMIN_REPORT, new AdminUpdateBookCommand());
        mappedCommands.put(GET_PATH + MAPPED_STATIC, new GetStaticFileCommand());
    }
*/
    private ICommand getSimpleCommand(String url) {
        return commands.get(url.split("\\?")[0]);
    }

    private ICommand getMappedCommand(String url) {
        Set<String> mappedCommandsKeys = mappedCommands.keySet();
        String nakedUrl = url.split("\\?")[0];

        return mappedCommandsKeys.parallelStream()
                .filter(commandKey -> nakedUrl.matches(commandKey.replaceAll("\\{.*}", ".*")))
                .findAny()
                .map(mappedCommands::get).orElse(null);
    }

}
