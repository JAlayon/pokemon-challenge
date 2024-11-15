package mx.bankaya.challenge.soap;

import mx.bankaya.challenge.*;
import mx.bankaya.challenge.soap.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonSoapEndpointTest {

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonSoapEndpoint pokemonSoapEndpoint;


    @Test
    void getPokemonId_success() {
        // given
        var request = new GetPokemonIdRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonIdResponse();
        mockResponse.setId(100);
        when(pokemonService.getPokemonId(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonId(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(mockResponse.getId());
        verify(pokemonService).getPokemonId(request.getName());
    }

    @Test
    void getPokemonName_success() {
        // given
        var request = new GetPokemonNameRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonNameResponse();
        mockResponse.setName("charizard");
        when(pokemonService.getPokemonName(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonName(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(mockResponse.getName());
        verify(pokemonService).getPokemonName(request.getName());
    }

    @Test
    void getPokemonBaseExperience_success() {
        // given
        var request = new GetPokemonExperienceRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonExperienceResponse();
        mockResponse.setBaseExperience(100);
        when(pokemonService.getPokemonBaseExperience(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonBaseExperience(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getBaseExperience()).isEqualTo(mockResponse.getBaseExperience());
        verify(pokemonService).getPokemonBaseExperience(request.getName());
    }

    @Test
    void getPokemonAbilities_success() {
        // given
        var request = new GetPokemonAbilitiesRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonAbilitiesResponse();
        mockResponse.getAbilities().add(new AbilityList());
        when(pokemonService.getPokemonAbilities(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonAbilities(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getAbilities().size()).isEqualTo(mockResponse.getAbilities().size());
        verify(pokemonService).getPokemonAbilities(request.getName());
    }

    @Test
    void getPokemonHeldItems_success() {
        // given
        var request = new GetPokemonHeldItemsRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonHeldItemsResponse();
        mockResponse.getHeldItems().add(new HeldItemList());
        when(pokemonService.getPokemonHeldItems(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonHeldItems(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getHeldItems().size()).isEqualTo(mockResponse.getHeldItems().size());
        verify(pokemonService).getPokemonHeldItems(request.getName());
    }

    @Test
    void getPokemonLocationArea_success() {
        // given
        var request = new GetPokemonLocationAreaEncountersRequest();
        request.setName("charizard");

        var mockResponse = new GetPokemonLocationAreaEncountersResponse();
        mockResponse.setLocationAreaEncounters("area");
        when(pokemonService.getPokemonLocationArea(request.getName())).thenReturn(mockResponse);

        // When
        var response = pokemonSoapEndpoint.getPokemonLocationArea(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getLocationAreaEncounters()).isEqualTo(mockResponse.getLocationAreaEncounters());
        verify(pokemonService).getPokemonLocationArea(request.getName());
    }
}
