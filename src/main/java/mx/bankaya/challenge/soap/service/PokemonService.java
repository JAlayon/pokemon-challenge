package mx.bankaya.challenge.soap.service;

import com.bankaya.challenge.*;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import mx.bankaya.challenge.pokemon.dto.PokemonResponseDto;
import mx.bankaya.challenge.soap.mapper.PokemonMapper;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRestClient pokemonRestClient;
    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRestClient pokemonRestClient, PokemonMapper pokemonMapper) {
        this.pokemonRestClient = pokemonRestClient;
        this.pokemonMapper = pokemonMapper;
    }

    public GetPokemonIdResponse getPokemonId(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonIdResponse();
        response.setId(pokemonDetailsResponse.id());
        return response;
    }

    public GetPokemonNameResponse getPokemonName(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonNameResponse();
        response.setName(pokemonDetailsResponse.name());
        return response;
    }

    public GetPokemonExperienceResponse getPokemonBaseExperience(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonExperienceResponse();
        response.setBaseExperience(pokemonDetailsResponse.baseExperience());
        return response;
    }

    public GetPokemonLocationAreaEncountersResponse getPokemonLocationArea(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonLocationAreaEncountersResponse();
        response.setLocationAreaEncounters(pokemonDetailsResponse.locationAreaEncounters());
        return response;
    }

    public GetPokemonAbilitiesResponse getPokemonAbilities(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonAbilitiesResponse();
        response.getAbilities().addAll(pokemonDetailsResponse.abilities()
                .stream().map(pokemonMapper::mapToAbilities).toList());
        return response;
    }

    public GetPokemonHeldItemsResponse getPokemonHeldItems(final String name) {
        var pokemonDetailsResponse = getPokemonDetailsResponse(name);
        var response = new GetPokemonHeldItemsResponse();
        response.getHeldItems().addAll(pokemonDetailsResponse.heldItems()
                .stream().map(pokemonMapper::mapToHeldItems).toList());
        return response;
    }

    private PokemonResponseDto getPokemonDetailsResponse(final String name) {
        return pokemonRestClient.getPokemonDetails(name);
    }

}
