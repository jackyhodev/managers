/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2020.
 */
package dev.galasa.cicsts;

import javax.validation.constraints.NotNull;

import dev.galasa.zos3270.ITerminal;

/**
 * CICS/TS Command-level Interpreter (CECI) Interface.
 *
 */
public interface ICeci {
    
    /**
     * Issue a CECI command. The command will be stored and executed from a CECI variable. 
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param command a {@link String} containing the CECI command
     * @return an {@link ICeciResponse} object containing the command's response and output values.
     * @throws CeciException 
     */
    public ICeciResponse issueCommand(@NotNull ITerminal ceciTerminal, @NotNull String command) throws CeciException;
    
    /**
     * Issue a CECI command. The command will be stored and executed from a CECI variable. 
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param command a {@link String} containing the CECI command
     * @param parseOutput parse the command output and store in {@link ICeciResponse}. Setting to false can improve performance on commands
     * that contain a lot of output fields, e.g. <code>ASSIGN</code>.<br><br>
     * The following examples shows how to retrieve a specific returned value:<br><code>
     * issueCommand(ITerminal, "ASSIGN USERID(&VAR)", false)<br>
     * retrieveVariableText(ITerminal, "ASSIGN USERID(&VAR)", false)
     * </code>
     * @return an {@link ICeciResponse} object containing the command's response.
     * @throws CeciException 
     */
    public ICeciResponse issueCommand(@NotNull ITerminal ceciTerminal, @NotNull String command, boolean parseOutput) throws CeciException;

    /**
     * Define a CECI text variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value text string with a maximum length of 32767 characters.
     * @return the length of the defined variable
     * @throws CeciException
     */
    public int defineVariableText(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull String value) throws CeciException;

    /**
     * Define a CECI binary variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value binary char array with a maximum length of 32767 characters.
     * @return the length of the defined variable
     * @throws CeciException
     */
    public int defineVariableBinary(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull char[] value) throws CeciException;
    
    /**
     * Define a double word CECI variable. 
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value a long representing a double word (8 bytes) with a decimal integer value from -9223372036854775808 to +9223372036854775807
     * @return the length of the defined variable
     * @throws CeciException
     */
    public int defineVariableDoubleWord(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull long value) throws CeciException;
    
    /**
     * Define a full word CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value an integer representing a full word (4 bytes) with a decimal value from -2147483648 to +2147483647 ({@link Integer.MIN_VALUE} to {@link Integer.MAX_VALUE})
     * @return the length of the defined variable 
     * @throws CeciException
     */
    public int defineVariableFullWord(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull int value) throws CeciException;
    
    /**
     * Define a half word CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value an integer representing a full word (2 bytes) with a decimal value from -32768 to +32767
     * @return the length of the defined variable 
     * @throws CeciException
     */
    public int defineVariableHalfWord(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull int value) throws CeciException;
    
    /**
     * Define a packed decimal CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @param value an integer representing a full word (2 bytes) with a decimal value from -9999999 to +9999999 (0x9999999D to 0x9999999C)
     * @return the length of the defined variable 
     * @throws CeciException
     */
    public int defineVariablePacked(@NotNull ITerminal ceciTerminal, @NotNull String name, @NotNull int value) throws CeciException;

    /**
     * Retrieve a CECI text variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public String retrieveVariableText(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Retrieve a CECI binary variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public char[] retrieveVariableBinary(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Retrieve a double word CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public long retrieveVariableDoubleWord(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Retrieve a full word CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public int retrieveVariableFullWord(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Retrieve a half word CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public int retrieveVariableHalfWord(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Retrieve a packed decimal CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @return variable value
     * @throws CeciException
     */
    public int retrieveVariablePacked(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;
    
    /**
     * Delete a single CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param name variable name. CECI variable names have a maximum length of 10 characters including leading {@literal &}.
     * @throws CeciException
     */
    public void deleteVariable(@NotNull ITerminal ceciTerminal, @NotNull String name) throws CeciException;

    /**
     * Delete all variables in this CECI session.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @throws CeciException
     */
    public void deleteAllVariables(@NotNull ITerminal ceciTerminal) throws CeciException;

    /**
     * Retrieve the content of the current EXEC Interface Block (EIB)
     * @param terminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @return the {@link IExecInterfaceBlock} 
     * @throws CeciException
     */
    public IExecInterfaceBlock getEIB(@NotNull ITerminal ceciTerminal) throws CeciException;

    /**
     * EXEC CICS LINK to a PROGRAM.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param programName the name of the PROGRAM
     * @param commarea a string representing the COMMAREA. If null, COMMAREA will be omitted from the command. Can be CECI variable name populated with
     *  (<b>&</b>name set via {@link #defineVariableText(ITerminal, String, String)}) or the actual data. The value of DATALENGTH in the command will be 
     * be allowed to default.
     * @param sysid the system name where the CICS region where the link request is to be routed. If null, SYSID will be omitted from the command.
     * @param transid the name of the mirror transaction on the remote region. If null, TRANSID will be omitted from the command.
     * @param synconreturn the remote system should take a sync point at program end. If false, SYNCONRETURN will be omitted from the command.
     * @return an {@link ICeciResponse} object containing the command's response.
     * @throws CeciException
     */
    public ICeciResponse linkProgram(@NotNull ITerminal ceciTerminal, @NotNull String programName, String commarea, String sysid, String transid, boolean synconreturn) throws CeciException;

    /**
     * EXEC CICS LINK to a PROGRAM with a CHANNEL. Use {@link #putContainer(ITerminal, String, String, String)} to create the container(s) on the CHANNEL 
     * and {@link #getContainer(ITerminal, String, String, String)} to retrieve the content after the LINK.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>  
     * @param programName the name of the PROGRAM
     * @param channelName the name of the CHANNEL.
     * @throws CeciExceptionan an {@link ICeciResponse} object containing the command's response.
     */
    public ICeciResponse linkProgramWithChannel(@NotNull ITerminal ceciTerminal, @NotNull String programName, @NotNull String channelName, String sysid, String transid, boolean synconreturn) throws CeciException;
    
    /**
     * Puts data in a CONTAINER with an associated CHANNEL.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * @param channelName the CHANNELNAME
     * @param containerName the COTAINER name
     * @param content a string representing the container contents. Can be CECI variable name populated with (<b>&</b>name set via {@link #defineVariableText(ITerminal, String, String)}) 
     * or the actual data. The value of FLENGTH in the command will be set to the data length.
     * @param dataType BIT or CHAR. If null, DATATYPE will be omitted from the command.
     * @param fromCcsid provides a value for FROMCCSID. If null, will be omitted from the command.
     * @param fromCodepage provides a value for FROMCODEPAGE. If null, will be omitted from the command.
     * @return an {@link ICeciResponse} object containing the command's response.
     * @throws CeciException
     */
    public ICeciResponse putContainer(@NotNull ITerminal ceciTerminal, @NotNull String channelName, @NotNull String containerName, @NotNull String content, String dataType, String fromCcsid, String fromCodepage) throws CeciException;
    
    /**
     * Gets the data in a CONTAINER with an associated CHANNEL into a CECI variable.
     * @param ceciTerminal an {@link ITerminal} object logged on to the CICS region and in an active CECI session.
     * If mixed case is required, the terminal should be presented with no upper case translate status. 
     * For example, the test could first issue <code>CEOT TRANIDONLY</code>
     * @param channelName the CHANNELNAME
     * @param containerName the CONTAINER name
     * @param variableName the CECI variable name. Data can be retrieved using {@link #retrieveVariableText(ITerminal, String)} or {@link #retrieveVariableHex(ITerminal, String)}
     * @param dataType BIT or CHAR. If null, DATATYPE will be omitted from the command. 
     * @param intoCcsid provides a value for INTOCCSID. If null, will be omitted from the command.
     * @param intoCodepage provides a value for INTOCODEPAGE. If null, will be omitted from the command.
     * @return an {@link ICeciResponse} object containing the command's response.
     * @throws CeciException
     */
    public ICeciResponse getContainer(@NotNull ITerminal ceciTerminal, @NotNull String channelName, @NotNull String containerName, @NotNull String variableName, String intoCcsid, String intoCodepage) throws CeciException;

}

