package command.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mornova.command.command.Command;
import org.mornova.command.validator.CommandValidator;
import org.mornova.command.validator.CommandValidatorImpl;
import org.mornova.domain.core.model.objectValue.refrerences.EmailRegex;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CommandValidatorImplTest {
@Getter
@Setter
     static class TestCommand implements Command{
       static final String NAME_LENGTH_MESSAGE_ERROR= "taille du nom doit être compris entre 2 et 100 caractères";
       static final String EMAIL_MESSAGE_ERROR= "invalid email";
       static final String AGE_MESSAGE_ERROR= "taille du nom doit être compris entre 2 et 100 caractères";
    @NotBlank
         @Size(min = 2,max = 100,message = NAME_LENGTH_MESSAGE_ERROR)
         String name;

         @Email(regexp = EmailRegex.value)
         String email;

         @Max(100)
         @Min(16)
         Short age;

         Long id;


         @Override
         public boolean isSatisfied() {
             return !isNull(TestCommand::getId) && !isEquals(TestCommand::getId,0L) ;
         }

         public boolean  isNull(Function<TestCommand,Object>  mapper){
             return Objects.isNull(mapper.apply(this));
         }
      public <T>  boolean isEquals(Function<TestCommand,T>  mapper,T v){
        return  Objects.equals(mapper.apply(this),v);
      }
     }

    private CommandValidator commandValidator;
    private Validator validatorMock;
    ConstraintViolation<Command>  invalidNameConstraintViolation;
    ConstraintViolation<Command>  invalidEmailConstraintViolation;
    ConstraintViolation<Command>  invalidAgeConstraintViolation;

    @BeforeEach
    void setUp() {
        validatorMock = mock(Validator.class);
        commandValidator = new CommandValidatorImpl(validatorMock);
        invalidAgeConstraintViolation=(ConstraintViolation<Command>)mock(ConstraintViolation.class);
        invalidEmailConstraintViolation=(ConstraintViolation<Command>)mock(ConstraintViolation.class);
        invalidNameConstraintViolation=(ConstraintViolation<Command>)mock(ConstraintViolation.class);

        when(invalidNameConstraintViolation.getMessage()).thenReturn(TestCommand.NAME_LENGTH_MESSAGE_ERROR);
        when(invalidEmailConstraintViolation.getMessage()).thenReturn(TestCommand.EMAIL_MESSAGE_ERROR);
        when(invalidAgeConstraintViolation.getMessage()).thenReturn(TestCommand.AGE_MESSAGE_ERROR);

    }




    @Test
    void validBeforeHandling_WithViolations_ThrowsException() {
        Command commandWithViolations = createCommand(true,true,true);
        Set<ConstraintViolation<Command>> violations = Set.of(invalidEmailConstraintViolation, invalidNameConstraintViolation,invalidAgeConstraintViolation);
        when(validatorMock.validate(commandWithViolations)).thenReturn(violations);
        Assertions.assertThrows(RuntimeException.class,
                () -> commandValidator.validBeforeHandling(commandWithViolations));
    }

    @Test
    void validBeforeHandling_WithoutViolations_DoesNotThrowException() {
        Command commandWithoutViolations = createValidCommand(false);

        when(validatorMock.validate(commandWithoutViolations)).thenReturn(Collections.emptySet());

        Assertions.assertDoesNotThrow(() -> commandValidator.validBeforeHandling(commandWithoutViolations));
    }

    @Test
    void validAfterHandling_WithNotSatisfied_ThrowsException() {
        Command commandWithViolations =createValidCommand(false);

        when(validatorMock.validate(commandWithViolations)).thenReturn(Collections.emptySet());

        Assertions.assertThrows(RuntimeException.class,
                () -> commandValidator.validAfterHandling(commandWithViolations));
    }

    @Test
    void validAfterHandling_WithoutViolationsAndSatisfied_DoesNotThrowException() {
        Command commandSatisfied =createValidCommand(true);

        when(validatorMock.validate(commandSatisfied)).thenReturn(Collections.emptySet());

        Assertions.assertDoesNotThrow(() -> commandValidator.validAfterHandling(commandSatisfied));
    }



    private TestCommand createCommand(boolean invalidEmail, boolean invalidName, boolean invalidAge) {
        TestCommand command = new TestCommand();
        command.setName(invalidName ? "t" : "Stefan");
        command.setEmail(invalidEmail ? "invalid_email" : "AlfonsoLorenz@gmail.com");
        command.setAge(invalidAge ? (short) 120 : (short) 30);
        return command;
    }
     private Command createValidCommand(boolean satisfied){
          TestCommand command= createCommand(false,false,false);
               command.setId(satisfied?1000L:0);

               return  command;
     }
}
