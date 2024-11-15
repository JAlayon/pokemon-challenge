package mx.bankaya.challenge.pokemon;

import mx.bankaya.challenge.BaseConfigIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PokemonRestClientIT extends BaseConfigIT {

    @Autowired
    private PokemonRestClient pokemonRestClient;

    @Test
    void testGetPokemon() {
        var response = pokemonRestClient.getPokemonDetails("ditto");

        assertNotNull(response);
        assertEquals("ditto", response.name());
        assertEquals(101, response.baseExperience());
    }
}
