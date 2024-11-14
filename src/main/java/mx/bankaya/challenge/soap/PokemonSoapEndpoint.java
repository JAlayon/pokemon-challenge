package mx.bankaya.challenge.soap;

import com.bankaya.challenge.*;
import mx.bankaya.challenge.soap.service.PokemonService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonSoapEndpoint {

    private static final String NAMESPACE_URI = "http://bankaya.mx/challenge";

    private final PokemonService pokemonService;

    public PokemonSoapEndpoint(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonIdRequest")
    @ResponsePayload
    public GetPokemonIdResponse getPokemonId(@RequestPayload GetPokemonIdRequest request) {
        return pokemonService.getPokemonId(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonNameRequest")
    @ResponsePayload
    public GetPokemonNameResponse getPokemonName(@RequestPayload GetPokemonNameRequest request) {
        return pokemonService.getPokemonName(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonExperienceRequest")
    @ResponsePayload
    public GetPokemonExperienceResponse getPokemonBaseExperience(@RequestPayload GetPokemonExperienceRequest request) {
        return pokemonService.getPokemonBaseExperience(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonAbilitiesRequest")
    @ResponsePayload
    public GetPokemonAbilitiesResponse getPokemonAbilities(@RequestPayload GetPokemonAbilitiesRequest request) {
        return pokemonService.getPokemonAbilities(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonHeldItemsRequest")
    @ResponsePayload
    public GetPokemonHeldItemsResponse getPokemonHeldItems(@RequestPayload GetPokemonHeldItemsRequest request) {
        return pokemonService.getPokemonHeldItems(request.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonLocationAreaEncountersRequest")
    @ResponsePayload
    public GetPokemonLocationAreaEncountersResponse getPokemonLocationArea(
            @RequestPayload GetPokemonLocationAreaEncountersRequest request) {
        return pokemonService.getPokemonLocationArea(request.getName());
    }
}

