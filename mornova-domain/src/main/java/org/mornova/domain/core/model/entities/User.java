package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.model.objectValue.value.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class User extends BasedEntity<UserId>{

    private UserId id;
    private  String firstName;
    private String lastName;
    private Email email;
    private  List<Role> roles;

    private User(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        roles = builder.roles;
        email = builder.email;
        if(builder.userPD!=null){
            id=builder.userPD.id();
            this.initPersistenceDetail(builder.userPD);
        }

    }

    public void addRole(Role role) {
        roles.add(role);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public static Builder builder() {
        return new Builder();
    }










    public static final class Builder {
        private String firstName;
        private String lastName;
        private Email email;
        private List<Role> roles=new ArrayList<>();
        private PersistenceDetailBuilder.PersistenceDetail<UserId> userPD;

        private Builder() {
        }



        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }
        public Builder email(String val) {
            email = new Email(val);
            return this;
        }

        public Builder roles(List<Role> val) {
            roles = val;
            return this;
        }

        public User build() {
            if(email==null){
                throw new RuntimeException("email is required and cannot be null");
            }
            return new User(this);
        }
        public  Builder  persistenceDetail(Consumer<PersistenceDetailBuilder<UserId>> consumer ) {
            PersistenceDetailBuilder<UserId> userIdPersistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(userIdPersistenceDetailBuilder);
            userPD=userIdPersistenceDetailBuilder.build();
            return this;
        }


    }
}
