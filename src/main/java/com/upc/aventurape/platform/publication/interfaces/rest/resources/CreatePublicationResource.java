package com.upc.aventurape.platform.publication.interfaces.rest.resources;

public record CreatePublicationResource(
        Long EntrepreneurId,
        String  nameActivity,
        String  description,
        Integer  timeDuration,
        String image,
        Integer cantPeople,
        Integer cost
) {
}
