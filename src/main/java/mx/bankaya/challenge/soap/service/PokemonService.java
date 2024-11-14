package mx.bankaya.challenge.soap.service;

import com.bankaya.challenge.*;
import jakarta.servlet.http.HttpServletRequest;
import mx.bankaya.challenge.event.EventLogService;
import mx.bankaya.challenge.event.enums.EventLogType;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import mx.bankaya.challenge.pokemon.dto.PokemonResponseDto;
import mx.bankaya.challenge.soap.mapper.PokemonMapper;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRestClient pokemonRestClient;
    private final HttpServletRequest httpServletRequest;
    private final PokemonMapper pokemonMapper;
    private final EventLogService eventLogService;

    public PokemonService(PokemonRestClient pokemonRestClient,
                          HttpServletRequest httpServletRequest,
                          PokemonMapper pokemonMapper, EventLogService eventLogService) {
        this.pokemonRestClient = pokemonRestClient;
        this.httpServletRequest = httpServletRequest;
        this.pokemonMapper = pokemonMapper;
        this.eventLogService = eventLogService;
    }

    public GetPokemonIdResponse getPokemonId(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonIdResponse();
        response.setId(pokemonDetailsResponse.id());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_ID, 100);
        return response;
    }

    public GetPokemonNameResponse getPokemonName(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonNameResponse();
        response.setName(pokemonDetailsResponse.name());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_NAME, 100);
        return response;
    }

    public GetPokemonExperienceResponse getPokemonBaseExperience(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonExperienceResponse();
        response.setBaseExperience(pokemonDetailsResponse.baseExperience());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_BASE_EXPERIENCE, 100);
        return response;
    }

    public GetPokemonLocationAreaEncountersResponse getPokemonLocationArea(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonLocationAreaEncountersResponse();
        response.setLocationAreaEncounters(pokemonDetailsResponse.locationAreaEncounters());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_LOCATION_AREA_ENCOUNTERS, 100);
        return response;
    }

    public GetPokemonAbilitiesResponse getPokemonAbilities(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonAbilitiesResponse();
        response.getAbilities().addAll(pokemonDetailsResponse.abilities()
                .stream().map(pokemonMapper::mapToAbilities).toList());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_ABILITIES, 100);
        return response;
    }

    public GetPokemonHeldItemsResponse getPokemonHeldItems(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonHeldItemsResponse();
        response.getHeldItems().addAll(pokemonDetailsResponse.heldItems()
                .stream().map(pokemonMapper::mapToHeldItems).toList());

        var clientIpAddress = getClientIp();
        eventLogService.saveRequest(clientIpAddress, EventLogType.GET_HELD_ITEMS, 100);
        return response;
    }

    private PokemonResponseDto getPokemonDetailsResponse(final String name) {
        return pokemonRestClient.getPokemonDetails(name);
    }


    private String getClientIp() {
        String ipAddress = httpServletRequest.getHeader("X-Forwarded-For");
        return (ipAddress != null && !ipAddress.isEmpty()) ? ipAddress : httpServletRequest.getRemoteAddr();
    }
}
