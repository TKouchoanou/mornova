CREATE TABLE user (
  created_at datetime NULL,
   updated_at datetime NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   id char(36) NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles (
  user_id char(36) NOT NULL,
   roles_id BIGINT NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE skill (
  created_at datetime NULL,
   updated_at datetime NULL,
   name VARCHAR(255) NULL,
   level INT NULL,
   id BIGINT NOT NULL,
   CONSTRAINT pk_skill PRIMARY KEY (id)
);

CREATE TABLE post (
  created_at datetime NULL,
   updated_at datetime NULL,
   content VARCHAR(255) NULL,
   project_name VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   status INT NULL,
   type INT NULL,
   amount_of_funding DECIMAL NULL,
   available_capital DECIMAL NULL,
   launch_date date NULL,
   sector VARCHAR(255) NULL,
   sub_sector VARCHAR(255) NULL,
   id char(36) NOT NULL,
   user_id char(36) NULL,
   dtype VARCHAR(31) NULL,
   CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE post_comments (
  post_id char(36) NOT NULL,
   comments_id BIGINT NOT NULL
);

CREATE TABLE post_likes (
  post_id char(36) NOT NULL,
   likes_id BIGINT NOT NULL
);

ALTER TABLE post_comments ADD CONSTRAINT uc_post_comments_comments UNIQUE (comments_id);

ALTER TABLE post_likes ADD CONSTRAINT uc_post_likes_likes UNIQUE (likes_id);

ALTER TABLE post ADD CONSTRAINT FK_POST_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE post_comments ADD CONSTRAINT fk_poscom_on_comment FOREIGN KEY (comments_id) REFERENCES comment (id);

ALTER TABLE post_comments ADD CONSTRAINT fk_poscom_on_post FOREIGN KEY (post_id) REFERENCES post (id);

ALTER TABLE post_likes ADD CONSTRAINT fk_poslik_on_like FOREIGN KEY (likes_id) REFERENCES `like` (id);

ALTER TABLE post_likes ADD CONSTRAINT fk_poslik_on_post FOREIGN KEY (post_id) REFERENCES post (id);


CREATE TABLE profile (
  created_at datetime NULL,
   updated_at datetime NULL,
   id char(36) NOT NULL,
   user_id char(36) NULL,
   CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE profile_experiences (
  profile_id char(36) NOT NULL,
   experiences_id BIGINT NOT NULL
);

CREATE TABLE profile_skills (
  profile_id char(36) NOT NULL,
   skills_id BIGINT NOT NULL
);

ALTER TABLE profile_experiences ADD CONSTRAINT uc_profile_experiences_experiences UNIQUE (experiences_id);

ALTER TABLE profile_skills ADD CONSTRAINT uc_profile_skills_skills UNIQUE (skills_id);

ALTER TABLE profile ADD CONSTRAINT FK_PROFILE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE profile_experiences ADD CONSTRAINT fk_proexp_on_experience FOREIGN KEY (experiences_id) REFERENCES experience (id);

ALTER TABLE profile_experiences ADD CONSTRAINT fk_proexp_on_profile FOREIGN KEY (profile_id) REFERENCES profile (id);

ALTER TABLE profile_skills ADD CONSTRAINT fk_proski_on_profile FOREIGN KEY (profile_id) REFERENCES profile (id);

ALTER TABLE profile_skills ADD CONSTRAINT fk_proski_on_skill FOREIGN KEY (skills_id) REFERENCES skill (id);
CREATE TABLE message (
  created_at datetime NULL,
   updated_at datetime NULL,
   content VARCHAR(255) NULL,
   sender_id char(36) NULL,
   recipient_id char(36) NULL
);

ALTER TABLE message ADD CONSTRAINT FK_MESSAGE_ON_RECIPIENT FOREIGN KEY (recipient_id) REFERENCES user (id);

ALTER TABLE message ADD CONSTRAINT FK_MESSAGE_ON_SENDER FOREIGN KEY (sender_id) REFERENCES user (id);

CREATE TABLE media (
  created_at datetime NULL,
   updated_at datetime NULL,
   type INT NULL,
   file BLOB NULL,
   `path` VARCHAR(255) NULL
);
CREATE TABLE `like` (
  created_at datetime NULL,
   updated_at datetime NULL,
   id BIGINT NOT NULL,
   user_id char(36) NULL,
   CONSTRAINT pk_like PRIMARY KEY (id)
);

ALTER TABLE `like` ADD CONSTRAINT FK_LIKE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE experience (
  created_at datetime NULL,
   updated_at datetime NULL,
   title VARCHAR(255) NULL,
   company VARCHAR(255) NULL,
   years_of_experience INT NOT NULL,
   id BIGINT NOT NULL,
   CONSTRAINT pk_experience PRIMARY KEY (id)
);

CREATE TABLE comment (
  created_at datetime NULL,
   updated_at datetime NULL,
   content VARCHAR(255) NULL,
   id BIGINT NOT NULL,
   user_id char(36) NULL,
   CONSTRAINT pk_comment PRIMARY KEY (id)
);

ALTER TABLE comment ADD CONSTRAINT FK_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);











