package com.upc.aventurape.platform.profiles.interfaces.rest;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.ProfileAdventurer;
import com.upc.aventurape.platform.profiles.domain.model.aggregates.ProfileEntrepreneur;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileAdventurerCommand;
import com.upc.aventurape.platform.profiles.domain.model.commands.CreateProfileEntrepreneurCommand;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetAllProfilesAdventurerQuery;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetAllProfilesEntrepreneurQuery;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetProfileAdventurerByIdQuery;
import com.upc.aventurape.platform.profiles.domain.model.queries.GetProfileEntrepreneurByIdQuery;
import com.upc.aventurape.platform.profiles.domain.services.ProfileAdventureCommandService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileAdventureQueryService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileEntrepreneurCommandService;
import com.upc.aventurape.platform.profiles.domain.services.ProfileEntrepreneurQueryService;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.CreateProfileAdventurerResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.CreateProfileEntrepreneurResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.ProfileAdventurerResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.resources.ProfileEntrepreneurResource;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.CreateProfileAdventurerCommandFromResourceAssembler;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.CreateProfileEntrepreneurCommandFromResourceAssembler;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.ProfileAdventurerResourceFromEntityAssembler;
import com.upc.aventurape.platform.profiles.interfaces.rest.transform.ProfileEntrepreneurResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")

public class ProfilesController {

    private final ProfileAdventureCommandService profileAdventureCommandService;
    private final ProfileEntrepreneurCommandService profileEntrepreneurCommandService;
    private final ProfileAdventureQueryService profileAdventureQueryService;
    private final ProfileEntrepreneurQueryService profileEntrepreneurQueryService;

    public ProfilesController(ProfileAdventureCommandService profileAdventureCommandService, ProfileEntrepreneurCommandService profileEntrepreneurCommandService,ProfileAdventureQueryService profileAdventureQueryService, ProfileEntrepreneurQueryService profileEntrepreneurQueryService ) {
        this.profileAdventureCommandService = profileAdventureCommandService;
        this.profileEntrepreneurCommandService = profileEntrepreneurCommandService;
        this.profileAdventureQueryService = profileAdventureQueryService;
        this.profileEntrepreneurQueryService = profileEntrepreneurQueryService;
    }

    @PostMapping("/adventurer")
    public ResponseEntity<ProfileAdventurerResource> createProfileAdventurer(@RequestBody CreateProfileAdventurerResource createProfileAdventurerResource) {
        var createProfileCommand = CreateProfileAdventurerCommandFromResourceAssembler.toCommandFromResource( createProfileAdventurerResource );
        var profileAdventurer = profileAdventureCommandService.handle( createProfileCommand );
        if (profileAdventurer.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var profileAdventurerResource = ProfileAdventurerResourceFromEntityAssembler.toResourceFromEntity( profileAdventurer.get() );
        return new ResponseEntity<>(profileAdventurerResource, HttpStatus.CREATED);
    }

    @PostMapping("/entrepreneur")
    public ResponseEntity<ProfileEntrepreneurResource> createProfileEntrepreneur(@RequestBody CreateProfileEntrepreneurResource createProfileEntrepreneurResource) {
        var createProfileCommand = CreateProfileEntrepreneurCommandFromResourceAssembler.toCommandFromResource( createProfileEntrepreneurResource );
        var profileEntrepreneur = profileEntrepreneurCommandService.handle( createProfileCommand );
        if(profileEntrepreneur.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var profileEntrepreneurResource = ProfileEntrepreneurResourceFromEntityAssembler.toResourceFromEntity(profileEntrepreneur.get());
        return new ResponseEntity<>(profileEntrepreneurResource, HttpStatus.CREATED);
    }

    @GetMapping("/adventurer/{profileId}")
    public ResponseEntity<ProfileAdventurerResource> getProfileAdventurerById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileAdventurerByIdQuery(profileId);
        var profileAdventurer = profileAdventureQueryService.handle(getProfileByIdQuery);
        if(profileAdventurer.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var profileAdventurerResource = ProfileAdventurerResourceFromEntityAssembler.toResourceFromEntity( profileAdventurer.get() );
        return new ResponseEntity<>(profileAdventurerResource, HttpStatus.OK);
    }

    @GetMapping("/entrepreneur/{profileId}")
    public ResponseEntity<ProfileEntrepreneurResource> getProfileEntrepreneurById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileEntrepreneurByIdQuery(profileId);
        var profileAdventurer = profileEntrepreneurQueryService.handle(getProfileByIdQuery);
        if(profileAdventurer.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var profileEntrepreneurResource = ProfileEntrepreneurResourceFromEntityAssembler.toResourceFromEntity( profileAdventurer.get() );
        return new ResponseEntity<>(profileEntrepreneurResource, HttpStatus.OK);
    }

    @GetMapping("/adventurer")
    public ResponseEntity<List<ProfileAdventurerResource>> getAllProfileAdventurers() {
        var getAllProfilesAdventurerQuery = new GetAllProfilesAdventurerQuery();
        var profilesAdventurer = profileAdventureQueryService.handle(getAllProfilesAdventurerQuery);
        var profileResources = profilesAdventurer.stream()
                .map(ProfileAdventurerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(profileResources, HttpStatus.OK);
    }

    @GetMapping("/entrepreneur")
    public ResponseEntity<List<ProfileEntrepreneurResource>> getAllProfileEntrepreneurs() {
        var getAllProfilesEntrepreneurQuery = new GetAllProfilesEntrepreneurQuery();
        var profilesEntrepreneur = profileEntrepreneurQueryService.handle(getAllProfilesEntrepreneurQuery);
        var profileResources = profilesEntrepreneur.stream()
                .map(ProfileEntrepreneurResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(profileResources, HttpStatus.OK);
    }
}
