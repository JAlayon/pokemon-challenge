package mx.bankaya.challenge.soap;

import com.bankaya.challenge.GetPokemonRequest;
import com.bankaya.challenge.GetPokemonResponse;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemon(@RequestPayload GetPokemonRequest request) {
        return pokemonService.getPokemonDetails(request.getName());
    }
}

