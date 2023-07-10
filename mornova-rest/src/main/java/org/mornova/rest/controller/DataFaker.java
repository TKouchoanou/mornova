package org.mornova.rest.controller;

import com.github.javafaker.Faker;
import org.mornova.domain.core.model.entities.*;
import org.mornova.domain.core.model.objectValue.refrerences.Currency;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectStatus;
import org.mornova.domain.core.model.objectValue.refrerences.ProjectType;
import org.mornova.domain.core.model.objectValue.refrerences.SkillLevel;
import org.mornova.domain.core.model.objectValue.value.Amount;
import org.mornova.domain.core.repository.ProfileRepository;
import org.mornova.domain.core.repository.ProjectPostRepository;
import org.mornova.domain.core.repository.RoleRepository;
import org.mornova.domain.core.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

@Component
public class DataFaker {
    Faker faker;
    static String[] roleNames={"admin","user","editor","superadmin"};
  static   ProjectStatus[] status=ProjectStatus.values();

  static ProjectType[] types=ProjectType.values();

  static SkillLevel[] levels=SkillLevel.values();


  static Currency[] currencies=Currency.values();

  UserRepository userRepository;
  RoleRepository roleRepository;

  ProjectPostRepository postRepository;
  ProfileRepository profileRepository;



    public DataFaker(UserRepository userRepository, RoleRepository roleRepository, ProjectPostRepository postRepository, ProfileRepository profileRepository) {
        this.faker = new Faker(Locale.CANADA);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
    }

    public User fakeUser(){
        User user=User.builder()
                .firstName("Malo")
                .lastName("Theophane")
                .email(faker.internet().emailAddress())
                .build();


        Role role=roleRepository.findByName("admin")
                .orElseGet(()->roleRepository.save(Role.builder().name("admin").build()));
        user.addRole(role);
        return user;
    }

    public User fakeUserPersistedUser(){
        return userRepository.save(fakeUser());
    }


    public User fakeUserWithRole(Role role){
        User user=User.builder().firstName(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .lastName(faker.name().lastName())
                .build();
        user.addRole(role);
        return user;
    }
    public Role fakePersistedRole(){
        int randomIndex=faker.random().nextInt(roleNames.length);
        Role role= Role.builder().name(roleNames[randomIndex]).build();
        return roleRepository.findByName(role.getName()).orElseGet(()->roleRepository.save(role));
    }
    public Role fakePersistedRole(String roleName){
        return Role.builder().name(roleName).build();
    }

    ProjectPost fackePost(User author){
       int typeIndex=faker.random().nextInt(types.length);
       int statusIndex=faker.random().nextInt(status.length);
    return     ProjectPost.Builder.builder()
                .author(author)
                .projectName(faker.company().name())
                .description(faker.lorem().sentence())
                .sector(faker.company().industry())
                .status(status[statusIndex])
                .type(types[typeIndex])
               .amountOfFunding(fakedAmount())
               .availableCapital(fakedAmount())
              .subSector(faker.company().industry())
            .launchDate(LocalDate.now().minusMonths(3))
                .build();
    }
    ProjectPost fakePost(){
      Role role= fakePersistedRole();
      User author=fakeUserWithRole(role);
      author=userRepository.save(author);
        return  fackePost(author);
    }
    Comment fakeComment(User author){
        return Comment.builder().author(author).content(faker.lorem().sentence(5)).build();
    }

    Like fakeLike(User liker){
        return Like.builder().liker(liker).build();
    }
    Amount fakedAmount(){
        int currencyIndex=faker.random().nextInt(currencies.length);
        BigDecimal value = new BigDecimal(faker.number().randomDouble(2, 0, 1000));
         return  new Amount(value, currencies[currencyIndex]);
    }
    Amount fakedAmount(Currency currency){
        BigDecimal value = new BigDecimal(faker.number().randomDouble(2, 0, 1000));
        return  new Amount(value, currency);
    }

    Profile fakeProfile(User owner){
        Profile profile=Profile.builder().owner(owner).build();
        for (int i = 0; i < 3; i++) {
            Experience experience = Experience.builder()
                    .title(faker.job().title())
                    .company(faker.company().name())
                    .build();
            profile.addExperience(experience);
        }
        for (int i = 0; i < 5; i++) {
            int skillIndex=faker.random().nextInt(levels.length);
            Skill skill = Skill.builder()
                    .name(faker.job().keySkills())
                    .level(levels[skillIndex])
                    .build();
            profile.addSkill(skill);
        }
        return profile;
    }
}
