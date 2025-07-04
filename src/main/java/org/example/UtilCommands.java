package org.example;

import java.util.Set;

public class UtilCommands {
    public  final static Set<String> COMMANDS= Set.of( "add", "delete", "list", "summary", "update" ),
            TOKENS_ADD = Set.of( "--description", "--amount"),
            TOKENS_UPDATE = Set.of( "--id", "--description", "--amount"),
            TOKENS_SUMMARY = Set.of( "--month", "--year" );

    public static boolean validateCommands(String[] arguments) {
        if(COMMANDS.contains(arguments[0])) {
            return true;
        } else {
            System.out.println("Invalid command. Available commands: " + COMMANDS);
            return false;
        }
    }
    public static boolean validateTokensAdd(String[] argumentsAdd) {
        if(!TOKENS_ADD.containsAll(Set.of(argumentsAdd[1], argumentsAdd[3]))) {
            System.out.println("Invalid tokens. Available tokens for add: " + TOKENS_ADD);
            return false;
        }
        return true;
    }
    public static boolean validateTokensUpdate(String[] argumentsUpdate) {
        if(!TOKENS_UPDATE.containsAll(Set.of(argumentsUpdate[1], argumentsUpdate[3], argumentsUpdate[5]))) {
            System.out.println("Invalid tokens. Available tokens for update: " + TOKENS_UPDATE);
            return false;
        }
        return true;
    }
    public static boolean validateTokensSummary(String[] argumentsSummary) {
        if(!TOKENS_SUMMARY.contains(Set.of(argumentsSummary))) {
            return false;
        }
        return true;
    }
}
