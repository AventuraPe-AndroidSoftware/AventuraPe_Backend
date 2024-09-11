package com.upc.aventurape.platform.publication.interfaces.rest.transform;

import com.upc.aventurape.platform.publication.domain.model.commands.CreatePublicationCommand;
import com.upc.aventurape.platform.publication.interfaces.rest.resources.CreatePublicationResource;

public class CreatePublicationCommandFromResourceAssembler {

    public static CreatePublicationCommand toCommandFromResource(CreatePublicationResource resource) {
        return new CreatePublicationCommand(
                resource.nameActivity(),
                resource.description(),
                resource.timeDuration(),
                resource.image(),
                resource.cantPeople(),
                resource.cost()
        );
    }







}