package org.mornova.domain.core.model.entities;

import org.mornova.domain.core.model.objectValue.ids.MessageId;

import java.util.function.Consumer;


public class Message extends BasedEntity<MessageId> {


    MessageId id;

    private User sender;

    private User recipient;
    private String content;


    public Message() {
    }

    protected Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    private Message(Builder builder) {
        sender = builder.sender;
        recipient = builder.recipient;
        content = builder.content;
        if(builder.messagePD!=null){
            id=builder.messagePD.id();
            this.initPersistenceDetail(builder.messagePD);
        }
    }

    public static final class Builder {
        private User sender;
        private User recipient;
        private String content;
        PersistenceDetailBuilder.PersistenceDetail<MessageId> messagePD;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder sender(User val) {
            sender = val;
            return this;
        }

        public Builder recipient(User val) {
            recipient = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<MessageId>> consumer ) {
            PersistenceDetailBuilder<MessageId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            messagePD=persistenceDetailBuilder.build();
            return this;
        }
        public Message build() {
            return new Message(this);
        }
    }
}
