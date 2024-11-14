package mx.bankaya.challenge.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AbilityResponseDto(
        ItemDto ability,
        @JsonProperty("is_hidden")
        boolean isHidden,
        int slot
) {
}
