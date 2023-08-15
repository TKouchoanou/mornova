package org.mornova.command;

import org.mornova.command.command.Command;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.validator.CommandValidator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class CommandManagerImpl implements CommandManager{
    CommandValidator commandValidator;

    CommandRouting commandRouting;

    PlatformTransactionManager platformTransactionManager;

    public CommandManagerImpl(CommandValidator commandValidator, CommandRouting commandRouting, PlatformTransactionManager platformTransactionManager) {
        this.commandValidator = commandValidator;
        this.commandRouting = commandRouting;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public <T extends Command> T process(T command) {
        System.out.println("command "+command.getClass().getName()+" is received for treatement");

        commandValidator.validBeforeHandling(command);

        TransactionStatus transactionStatus=getTransactionStatus(command);
        List<CommandHandler> commandHandlerList=getCommandHandlerList(command);
        CommandHandler.HandlingContext context= new CommandHandler.HandlingContext();
        boolean isSequential= isSequentialHandling(command);

        try {
             if(isSequential){
                 handleSequentially(commandHandlerList,command,context);
             }else{
                 handleParallel(commandHandlerList,command,context);
             }
             commandValidator.validAfterHandling(command);
             context.getOnSuccessActions().forEach(Runnable::run);//action avant ou apres le commit?
             platformTransactionManager.commit(transactionStatus);
        }catch (Exception exception){
          platformTransactionManager.rollback(transactionStatus);
        }
        return command;
    }

    public void handleParallel(List<CommandHandler> commandHandlerList, Command command, CommandHandler.HandlingContext context){
            CompletableFuture<?>[] workers= commandHandlerList
                    .stream()
                    .map(handler -> CompletableFuture.runAsync(()->handler.handle(command,context)))
                    .toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(workers).join();

    }
    public void handleSequentially(List<CommandHandler> commandHandlerList,Command command,CommandHandler.HandlingContext context){
            for (CommandHandler commandHandler:commandHandlerList) {
                commandHandler.handle(command,context);
            }
    }

    TransactionStatus getTransactionStatus(Command command){
        var transactionDefinition=new DefaultTransactionDefinition();
        transactionDefinition.setIsolationLevel(commandRouting.getIsolation(command).value());
        return platformTransactionManager.getTransaction(transactionDefinition);
    }

   List<CommandHandler> getCommandHandlerList(Command command){
      return   commandRouting.getHandlers(command);
   }

   boolean isSequentialHandling(Command command){
     return commandRouting.isParallelHandling(command);
   }
}
