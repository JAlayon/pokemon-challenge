package mx.bankaya.challenge.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HeldItemDto(
        ItemDto item,
        @JsonProperty("version_details")
        List<VersionDetailDto> versionDetails
) {
}
