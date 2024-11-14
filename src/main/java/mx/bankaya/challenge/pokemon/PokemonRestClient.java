package mx.bankaya.challenge.pokemon;

import mx.bankaya.challenge.pokemon.dto.PokemonResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "pokemonRestClient", url = "${pokemon.url}")
public interface PokemonRestClient {

    @GetMapping(value = "pokemon/{name}")
    PokemonResponseDto getPokemonDetails(@PathVariable String name);
}
