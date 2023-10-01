package org.mornova.query.query;

import lombok.*;

import java.util.List;
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPostQuery {
    List<String> userIds;
    List<String> postsId;
}
/*
public List<Long> convertUserIdsToLongList() {
        List<Long> userIdsAsLong = new ArrayList<>();
        for (String userId : userIds) {
            try {
                long id = Long.parseLong(userId);
                userIdsAsLong.add(id);
            } catch (NumberFormatException e) {
                // Handle parsing error if needed
            }
        }
        return userIdsAsLong;
    }

    public List<UUID> convertPostsIdToUUIDList() {
        List<UUID> postsIdAsUUID = new ArrayList<>();
        for (String postId : postsId) {
            try {
                UUID id = UUID.fromString(postId);
                postsIdAsUUID.add(id);
            } catch (IllegalArgumentException e) {
                // Handle parsing error if needed
            }
        }
        return postsIdAsUUID;
    }
}
Dans cet exemple, nous avons ajouté deux méthodes de conversion dans la classe UserPostQuery. convertUserIdsToLongList() convertit la liste d'identifiants d'utilisateurs en une liste de nombres longs. convertPostsIdToUUIDList() convertit la liste d'identifiants de messages en une liste d'UUID.

Vous pouvez ensuite utiliser ces méthodes pour effectuer les conversions nécessaires dans votre application. Assurez-vous d'ajouter des gestionnaires d'exceptions appropriés pour gérer les erreurs de conversion.






*
* */