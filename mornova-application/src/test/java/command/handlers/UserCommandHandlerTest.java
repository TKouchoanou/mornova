package command.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mornova.command.command.user.CreateUserCommand;
import org.mornova.command.handler.CommandHandler;
import org.mornova.command.handler.user.CreateUserCommandHandler;
import org.mornova.command.mapper.UserMapper;
import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

//@SpringBootTest(classes = Main.class)
//@Import(CreateUserCommandHandler.class)
public class UserCommandHandlerTest {


    CreateUserCommandHandler createUserCommandHandler;
    //@Mock
    UserRepository userRepositoryMock;
    CreateUserCommand command;
    User persistedUser;
    User unPersistedUser;
    UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.openMocks(this);
        userRepositoryMock = mock(UserRepository.class);
        String firstName="theophane";
        String lastName="malo";
        String email="theophane.malo@example.com";
        command = spy(CreateUserCommand.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build());
       // when(command.getLastName()).thenReturn("kouchoanou");

        User.Builder userBuilder= User.builder()
                .email(email)
                .lastName(lastName)
                .firstName(firstName);

        unPersistedUser=userBuilder.build();

        persistedUser=userBuilder
                .persistenceDetail(
                        pd->pd.id(new UserId(UUID.randomUUID()))
                                .updatedAt(LocalDateTime.now().plusDays(4))
                                .createdAt(LocalDateTime.now())
                ).build();
       userMapper= mock(UserMapper.class);
        when(userMapper.toDomain(any(CreateUserCommand.class))).thenReturn(User
                .builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .build());

        createUserCommandHandler = new CreateUserCommandHandler(userRepositoryMock,userMapper,mock(ApplicationEventPublisher.class));
        //

        /*when(command.getEmail()).thenReturn(email);
      when(command.getLastName()).thenReturn(lastName);
      when(command.getFirstName()).thenReturn(firstName);*/

    }

    @Test
   void  createUser(){
        Assertions.assertThrows(RuntimeException.class,
                ()->createUserCommandHandler.handle(command, new CommandHandler.HandlingContext()));
        // Vérifier que la méthode save du UserRepository a été appelée avec le bon utilisateur
        verify(userRepositoryMock, times(1)).save(any(User.class));

    }
    @Test
    void  createUserCommandHandlingIsSatisfied(){
        when(userRepositoryMock.save(any(User.class))).thenReturn(persistedUser);
        createUserCommandHandler.handle(command, new CommandHandler.HandlingContext());
        Assertions.assertTrue(command.isSatisfied());
        verify(command,times(1)).setId(any(String.class));

    }
    @Test
    void  createUserCommandHandlingIsNotSatisfied(){
        when(userRepositoryMock.save(any(User.class))).thenReturn(unPersistedUser);
        Assertions.assertThrows(RuntimeException.class,
                ()->createUserCommandHandler.handle(command, new CommandHandler.HandlingContext()));

        verify(command,times(0)).setId(any(String.class));
        Assertions.assertFalse(command.isSatisfied());

    }
}
