package mx.bankaya.challenge.soap.service;

import mx.bankaya.challenge.TestUtils;
import mx.bankaya.challenge.cache.TestCacheConfiguration;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import mx.bankaya.challenge.soap.mapper.PokemonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Import;

import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Import(TestCacheConfiguration.class)
@SpringBootTest(classes = {PokemonService.class, PokemonMapper.class})
class PokemonServiceIT {

    @MockBean
    private PokemonRestClient pokemonRestClient;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PokemonService pokemonService;


    @BeforeEach
    void setUp() {
        cacheManager.getCacheNames().forEach(name ->
                Objects.requireNonNull(cacheManager.getCache(name))
                        .clear());
    }

    @Test
    void getPokemonId_cache_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonId(pokemonName);
        var responseFromCache = pokemonService.getPokemonId(pokemonName);

        // then
        assertCache("getPokemonId", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }

    @Test
    void getPokemonName_cache_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonName(pokemonName);
        var responseFromCache = pokemonService.getPokemonName(pokemonName);

        // then
        assertCache("getPokemonName", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }

    @Test
    void getPokemonBaseExperience_cache_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonBaseExperience(pokemonName);
        var responseFromCache = pokemonService.getPokemonBaseExperience(pokemonName);

        // then
        assertCache("getPokemonBaseExperience", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }

    @Test
    void getPokemonLocationArea_cache_success() {
        // given
        var pokemonName = "charmander";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonLocationArea(pokemonName);
        var responseFromCache = pokemonService.getPokemonLocationArea(pokemonName);

        // then
        assertCache("getPokemonLocationArea", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }

    @Test
    void getPokemonAbilities_cache_success() {
        // given
        var pokemonName = "snorlax";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonAbilities(pokemonName);
        var responseFromCache = pokemonService.getPokemonAbilities(pokemonName);

        // then
        assertCache("getPokemonAbilities", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }

    @Test
    void getPokemonHeldItems_cache_success() {
        // given
        var pokemonName = "zapdos";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var responseFromCall = pokemonService.getPokemonHeldItems(pokemonName);
        var responseFromCache = pokemonService.getPokemonHeldItems(pokemonName);

        // then
        assertCache("getPokemonHeldItems", "pokemon", pokemonName,
                responseFromCall, responseFromCache);
    }



    private void assertCache(String methodName, String cacheName,
                             String pokemonName, Object responseFromCall, Object responseFromCache) {
        var cache = cacheManager.getCache(cacheName);
        assertNotNull(cache);
        var cacheKey = new SimpleKey(PokemonService.class.getSimpleName(),
                methodName,
                new Object[]{pokemonName});
        var cacheValue = cache.get(cacheKey);
        assertNotNull(cacheValue);
        assertEquals(responseFromCall, responseFromCache);
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

}
