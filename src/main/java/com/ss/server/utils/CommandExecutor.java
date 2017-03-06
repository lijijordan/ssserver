package com.ss.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/2
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class CommandExecutor {

    private static final Logger log = LoggerFactory.getLogger(CommandExecutor.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CommandExecutor obj = new CommandExecutor();
        //in mac oxs
//        String command = "ping -c 2 " + domainName;
//        String command = "./client_darwin_386 -r \"106.185.35.79:1980\" -l \":9999\" -mode fast2";
        //in windows
        String command = "./server_darwin_386 -t 127.0.0.1:10896 -l :1980 -mode fast2";
//        String command = "pwd";
        obj.executeCommand(command);
    }

    /**
     * Execute command.
     *
     * @param command the command
     */
    public static void executeCommand(String command) {
        log.info("创建可执行线程");
        new Thread(() -> execute(command)).start();
        log.info("创建线程结束");
    }

    private static String execute(String command) {
        log.info("执行命令:" + command);
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
