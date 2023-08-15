package command.manager;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mornova.command.CommandManager;
import org.mornova.command.CommandManagerImpl;
import org.mornova.command.CommandRouting;
import org.mornova.command.command.Command;
import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.handler.user.CreateUserCommandHandler;
import org.mornova.command.validator.CommandValidator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.DefaultTransactionStatus;

import java.util.List;

import static org.mockito.Mockito.*;

public class CommandManagerTest {
    CommandManager commandManagerWithSuccess;
    CommandManager commandManagerWithFailed;
    CommandValidator commandValidator;

    CommandHandler commandHandlerWithSuccess;
    CommandHandler commandHandlerWithFailed;

    CommandRouting commandRoutingWithSuccessHandlers;
    CommandRouting commandRoutingWithFailureHandlers;
    Command command;

    PlatformTransactionManager platformTransactionManager;

    Validator validator(boolean invalid){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
           // return factory.getValidator();
        Validator validator=  mock(Validator.class);
        if (invalid)
        when(validator.validate(any())).thenThrow(new RuntimeException("invalide bean"));
        else
         when(validator.validate(any()));
        return validator;
    }

    Command spyCommand(){
        String firstName="theophane";
        String lastName="malo";
        String email="theophane.malo@example.com";
        return spy(CreateUserCommand.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build());
    }

    CommandRouting getCommandRouting(boolean withSucces){
        CommandRouting  commandRouting =mock(CommandRouting.class);
        when(commandRouting.getIsolation(command)).thenReturn(Isolation.DEFAULT);
        when(commandRouting.isParallelHandling(command)).thenReturn(false);
        if(withSucces){
            commandHandlerWithSuccess=mock(CreateUserCommandHandler.class);
            when(commandRouting.getHandlers(command)).thenReturn(List.of(commandHandlerWithSuccess));

        }else{
           commandHandlerWithFailed=mock(CreateUserCommandHandler.class);
            doThrow(new RuntimeException(" command process failed simulation"))
                    .when(commandHandlerWithFailed)
                    .handle(any(Command.class),any(CommandHandler.HandlingContext.class));
            when(commandRouting.getHandlers(command)).thenReturn(List.of(commandHandlerWithFailed));
        }
        return commandRouting;
    }
    CommandRouting getCommandRouting3(boolean withSucces) {
        CommandRouting commandRouting = mock(CommandRouting.class);
        when(commandRouting.getIsolation(any())).thenReturn(Isolation.DEFAULT);
        when(commandRouting.isParallelHandling(any())).thenReturn(false);

        if (withSucces) {
            commandHandlerWithSuccess = mock(CreateUserCommandHandler.class);
            when(commandRouting.getHandlers(any())).thenReturn(List.of(commandHandlerWithSuccess));
        } else {
            commandHandlerWithFailed = mock(CreateUserCommandHandler.class);
            doThrow(new RuntimeException("command process failed simulation"))
                    .when(commandHandlerWithFailed).handle(any(), any());
            when(commandRouting.getHandlers(any())).thenReturn(List.of(commandHandlerWithFailed));
        }

        return commandRouting;
    }
    @BeforeEach
    public void setUp() {
        command=spyCommand();
        //validator
        commandValidator=mock(CommandValidator.class);
       // platformTransactionManager
        platformTransactionManager =mock(PlatformTransactionManager.class);
        when(platformTransactionManager.getTransaction(any(TransactionDefinition.class))).thenReturn(getTransaction());

        //command manager with succes
        commandRoutingWithSuccessHandlers =getCommandRouting(true);
        commandManagerWithSuccess = new CommandManagerImpl(commandValidator,commandRoutingWithSuccessHandlers, platformTransactionManager);

        //command manager with failure
        commandRoutingWithFailureHandlers =getCommandRouting(false);
        commandManagerWithFailed =new CommandManagerImpl(commandValidator,commandRoutingWithFailureHandlers, platformTransactionManager);


    }

    TransactionStatus getTransaction(){
        return new DefaultTransactionStatus(true,true,true,true,true,true);
    }
    @Test
    public void processSuccessTest(){
        commandManagerWithSuccess.process(command);
        verify(commandValidator,times(1)).validBeforeHandling(command);
        verify(commandValidator,times(1)).validAfterHandling(command);

        verify(commandRoutingWithSuccessHandlers,times(1)).getHandlers(command);
        verify(commandRoutingWithSuccessHandlers,times(1)).getIsolation(command);
        verify(commandRoutingWithSuccessHandlers,times(1)).isParallelHandling(command);

        verify(commandHandlerWithSuccess,times(1)).handle(any(Command.class),any(CommandHandler.HandlingContext.class));

        verify(platformTransactionManager,times(1)).commit(any(TransactionStatus.class));

    }


    @Test
    public void processFailTest(){
        commandManagerWithFailed.process(command);
        verify(commandValidator,times(1)).validBeforeHandling(command);
        verify(commandValidator,times(0)).validAfterHandling(command);

        verify(commandRoutingWithFailureHandlers,times(1)).getHandlers(command);
        verify(commandRoutingWithFailureHandlers,times(1)).getIsolation(command);
        verify(commandRoutingWithFailureHandlers,times(1)).isParallelHandling(command);

        verify(commandHandlerWithFailed,times(1)).handle(any(Command.class),any(CommandHandler.HandlingContext.class));

        verify(platformTransactionManager,times(0)).commit(any(TransactionStatus.class));

        verify(platformTransactionManager,times(1)).rollback(any(TransactionStatus.class));

    }
}
