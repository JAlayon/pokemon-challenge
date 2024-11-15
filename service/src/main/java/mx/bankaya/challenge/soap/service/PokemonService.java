package mx.bankaya.challenge.soap.service;

import mx.bankaya.challenge.*;
import mx.bankaya.challenge.event.enums.EventLogType;
import mx.bankaya.challenge.metrics.LogExecutionTime;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import mx.bankaya.challenge.pokemon.dto.PokemonResponseDto;
import mx.bankaya.challenge.soap.mapper.PokemonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRestClient pokemonRestClient;
    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRestClient pokemonRestClient,
                          PokemonMapper pokemonMapper) {
        this.pokemonRestClient = pokemonRestClient;
        this.pokemonMapper = pokemonMapper;
    }

    @LogExecutionTime(operation = EventLogType.GET_ID)
    public GetPokemonIdResponse getPokemonId(final String name) {
        logger.info("process=getPokemonId, status=started, name={}", name);
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonIdResponse();
        response.setId(pokemonDetailsResponse.id());
        logger.info("process=getPokemonId, status=finished, name={}", name);
        return response;
    }

    @LogExecutionTime(operation = EventLogType.GET_NAME)
    public GetPokemonNameResponse getPokemonName(final String name) {
        logger.info("process=getPokemonName, status=started, name={}", name);
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonNameResponse();
        response.setName(pokemonDetailsResponse.name());
        logger.info("process=getPokemonName, status=finished, name={}", name);
        return response;
    }

    @LogExecutionTime(operation = EventLogType.GET_BASE_EXPERIENCE)
    public GetPokemonExperienceResponse getPokemonBaseExperience(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        logger.info("process=getPokemonBaseExperience, status=started, name={}", name);
        var response = new GetPokemonExperienceResponse();
        response.setBaseExperience(pokemonDetailsResponse.baseExperience());
        logger.info("process=getPokemonBaseExperience, status=finished, name={}", name);
        return response;
    }

    @LogExecutionTime(operation = EventLogType.GET_LOCATION_AREA_ENCOUNTERS)
    public GetPokemonLocationAreaEncountersResponse getPokemonLocationArea(final String name) {
        logger.info("process=getPokemonLocationArea, status=started, name={}", name);
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonLocationAreaEncountersResponse();
        response.setLocationAreaEncounters(pokemonDetailsResponse.locationAreaEncounters());
        logger.info("process=getPokemonLocationArea, status=finished, name={}", name);
        return response;
    }

    @LogExecutionTime(operation = EventLogType.GET_ABILITIES)
    public GetPokemonAbilitiesResponse getPokemonAbilities(final String name) {
        logger.info("process=getPokemonAbilities, status=started, name={}", name);
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonAbilitiesResponse();
        response.getAbilities().addAll(pokemonDetailsResponse.abilities()
                .stream().map(pokemonMapper::mapToAbilities).toList());
        logger.info("process=getPokemonAbilities, status=finished, name={}", name);
        return response;
    }

    @LogExecutionTime(operation = EventLogType.GET_HELD_ITEMS)
    public GetPokemonHeldItemsResponse getPokemonHeldItems(final String name) {
        logger.info("process=getPokemonHeldItems, status=started, name={}", name);
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonHeldItemsResponse();
        response.getHeldItems().addAll(pokemonDetailsResponse.heldItems()
                .stream().map(pokemonMapper::mapToHeldItems).toList());
        logger.info("process=getPokemonAbilities, status=finished, name={}", name);
        return response;
    }

    private PokemonResponseDto getPokemonDetailsResponse(final String name) {
        return pokemonRestClient.getPokemonDetails(name);
    }
}
