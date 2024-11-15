package mx.bankaya.challenge.soap.service;

import mx.bankaya.challenge.TestUtils;
import mx.bankaya.challenge.pokemon.PokemonRestClient;
import mx.bankaya.challenge.soap.mapper.PokemonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRestClient pokemonRestClient;

    @Spy
    private PokemonMapper pokemonMapper;

    @InjectMocks
    private PokemonService pokemonService;


    @Test
    void getPokemonId_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonId(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(pokemonResponse.id());
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

    @Test
    void getPokemonName_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonName(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(pokemonResponse.name());
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

    @Test
    void getPokemonBaseExperience_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonBaseExperience(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getBaseExperience()).isEqualTo(pokemonResponse.baseExperience());
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

    @Test
    void getPokemonLocationArea_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonLocationArea(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getLocationAreaEncounters()).isEqualTo(pokemonResponse
                .locationAreaEncounters());
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

    @Test
    void getPokemonAbilities_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 10, 1);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonAbilities(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getAbilities()).hasSameSizeAs(pokemonResponse.abilities());
        assertNotNull(response.getAbilities().getFirst().getAbility());
        assertThat(response.getAbilities().getFirst().getSlot()).isPositive();
        assertFalse(response.getAbilities().getFirst().isIsHidden());
        assertNotNull(response.getAbilities().getFirst().getAbility().getName());
        assertNotNull(response.getAbilities().getFirst().getAbility().getUrl());
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }

    @Test
    void getPokemonHeldItems_success() {
        // given
        var pokemonName = "ditto";
        var pokemonResponse = TestUtils.buildPokemonResponseDto(pokemonName,
                100, 1, 10);

        when(pokemonRestClient.getPokemonDetails(pokemonName)).thenReturn(pokemonResponse);

        // when
        var response = pokemonService.getPokemonHeldItems(pokemonName);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getHeldItems()).hasSameSizeAs(pokemonResponse.heldItems());
        var versionDetails = response.getHeldItems().getFirst().getVersionDetails().getFirst();
        assertNotNull(versionDetails.getVersion());
        assertThat(versionDetails.getRarity()).isPositive();
        verify(pokemonRestClient).getPokemonDetails(pokemonName);
    }


}
