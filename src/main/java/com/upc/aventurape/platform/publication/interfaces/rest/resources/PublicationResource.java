package com.upc.aventurape.platform.publication.interfaces.rest.resources;

public record PublicationResource(
       Long Id,
        String  nameActivity,
        String  description,
        Integer  timeDuration,
        String image,
        Integer cantPeople,
        Integer cost
) {
}