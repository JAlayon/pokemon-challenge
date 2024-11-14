package mx.bankaya.challenge.soap.service;

import com.bankaya.challenge.GetPokemonResponse;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRestClient pokemonRestClient;

    public PokemonService(PokemonRestClient pokemonRestClient) {
        this.pokemonRestClient = pokemonRestClient;
    }

    public GetPokemonResponse getPokemonDetails(final String name) {
        var pokemonDetailsResponse = pokemonRestClient.getPokemonDetails(name);

        GetPokemonResponse response = new GetPokemonResponse();
        response.setName(pokemonDetailsResponse.name());
        response.setId(pokemonDetailsResponse.id());
        response.setBaseExperience(pokemonDetailsResponse.baseExperience());
        response.setLocationAreaEncounters(pokemonDetailsResponse.locationAreaEncounters());
        // Map abilities and held_items as needed

        return response;
    }
}
