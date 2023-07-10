package org.mornova.domain.core.model.entities;

import lombok.Getter;
import org.mornova.domain.core.model.objectValue.ids.PostId;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectStatus;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectType;
import org.mornova.domain.core.model.objectValue.value.Amount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
@Getter
public class ProjectPost extends Post{


    private List<Media> mediaList;
    private String projectName;
    private String description;
    ProjectStatus status; //il y en a par defaut ou il doit être fournit à la création?
    ProjectType type; // il y en a par defaut ou il doit être fournit à la création?

    Amount amountOfFunding;

    Amount availableCapital;
    LocalDate launchDate;
    String sector;
    String subSector;

    public ProjectPost() {
    }

    public ProjectPost(User user, String projectName, String description) {
        super(user);
        this.projectName = projectName;
        this.description = description;
    }

    private ProjectPost(Builder builder) {
        author = builder.author;
        comments = builder.comments;
        likes = builder.likes;
        projectName = builder.projectName;
        description = builder.description;
        status = builder.status;
        type = builder.type;
        amountOfFunding = builder.amountOfFunding;
        availableCapital = builder.availableCapital;
        launchDate = builder.launchDate;
        mediaList = builder.mediaList;
        sector = builder.sector;
        subSector = builder.subSector;

        if(builder.postPD!=null){
            id=builder.postPD.id();
            this.initPersistenceDetail(builder.postPD);
        }
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    @Override
    public String getContent() {
        return description;
    }

    public static final class Builder {
        private User author;
        private List<Comment> comments=new ArrayList<>();
        private List<Like> likes=new ArrayList<>();
        private String projectName;
        private String description;
        private ProjectStatus status;
        private ProjectType type;
        private Amount amountOfFunding;
        private Amount availableCapital;
        private LocalDate launchDate;
        private List<Media> mediaList=new ArrayList<>();
        private String sector;
        private String subSector;
        PersistenceDetailBuilder.PersistenceDetail<PostId> postPD;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder author(User val) {
            author = val;
            return this;
        }

        public Builder comments(List<Comment> val) {
            comments = val;
            return this;
        }

        public Builder likes(List<Like> val) {
            likes = val;
            return this;
        }

        public Builder projectName(String val) {
            projectName = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder status(ProjectStatus val) {
            status = val;
            return this;
        }

        public Builder type(ProjectType val) {
            type = val;
            return this;
        }

        public Builder amountOfFunding(Amount val) {
            amountOfFunding = val;
            return this;
        }

        public Builder availableCapital(Amount val) {
            availableCapital = val;
            return this;
        }

        public Builder launchDate(LocalDate val) {
            launchDate = val;
            return this;
        }

        public Builder mediaList(List<Media> val) {
            mediaList = val;
            return this;
        }

        public Builder sector(String val) {
            sector = val;
            return this;
        }

        public Builder subSector(String val) {
            subSector = val;
            return this;
        }
        public Builder persistenceDetail(Consumer<PersistenceDetailBuilder<PostId>> consumer ) {
            PersistenceDetailBuilder<PostId> persistenceDetailBuilder=new PersistenceDetailBuilder<>();
            consumer.accept(persistenceDetailBuilder);
            postPD=persistenceDetailBuilder.build();
            return this;
        }
        public ProjectPost build() {
            //ce qui est required pour la création
            assertUserIsDefined();
            assertUserDescriptionIsDefined();
            assertProjectNameIsDefined();
            //ce qui est optionnel et suporter par les uses case
            //uses case command
            //1- creation d'un post avec autheur et contenu
            //2- like d'un post (post id et liker id)
            //3-comment d'un post ( content comment, post id, commentator id)
            //4- remove like or add unlike
            return new ProjectPost(this);
        }

        public void  assertUserIsDefined(){
            if(author ==null){
                throw new RuntimeException("author is required to create a post");
            }
        }
        public void  assertProjectNameIsDefined(){
            if(projectName ==null){
                throw new RuntimeException("projectName is required to create a post");
            }
        }
        public void  assertUserDescriptionIsDefined(){
            if(description ==null){
                throw new RuntimeException("description is required to create a post");
            }
        }
    }
}
