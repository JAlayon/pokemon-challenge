package mx.bankaya.challenge.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonRestClientIT extends BaseConfigIT{

    @Autowired
    private PokemonRestClient pokemonRestClient;

    @Test
    public void testGetPokemon() {
        var response = pokemonRestClient.getPokemon("ditto");

        assertNotNull(response);
        assertEquals("ditto", response.name());
        assertEquals(101, response.baseExperience());
    }
}
