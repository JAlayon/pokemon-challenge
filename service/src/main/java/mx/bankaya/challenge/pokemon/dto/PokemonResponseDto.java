package mx.bankaya.challenge.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PokemonResponseDto(
        int id,
        String name,
        List<AbilityResponseDto> abilities,
        @JsonProperty("base_experience")
        int baseExperience,
        @JsonProperty("held_items")
        List<HeldItemDto> heldItems,
        @JsonProperty("location_area_encounters")
        String locationAreaEncounters
) {
}
