package com.upc.aventurape.platform.profiles.interfaces.rest.transform;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.Profile;
import com.upc.aventurape.platform.profiles.domain.model.aggregates.ProfileAdventurer;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.ProfileAdventurerResource;

public class ProfileAdventurerResourceFromEntityAssembler {
    public static ProfileAdventurerResource toResourceFromEntity(ProfileAdventurer entity){
        return new ProfileAdventurerResource(
                entity.getId(),
                entity.getFullName(),
                entity.getGender(),
                entity.getEmailAddress(),
                entity.getStreetAddress()
        );
    }
}