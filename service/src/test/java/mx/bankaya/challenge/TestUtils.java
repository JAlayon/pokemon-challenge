package mx.bankaya.challenge;

import mx.bankaya.challenge.pokemon.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUtils {

    public static PokemonResponseDto buildPokemonResponseDto(String name,
                                                             int baseExperience,
                                                             int numAbilities,
                                                             int numHeldItems) {
        return new PokemonResponseDto(new Random().nextInt(1000),
                (name == null)?"ditto":name,
                buildAbilities(numAbilities),
                (baseExperience == 0)?10:baseExperience,
                buildHeldItems(numHeldItems),
                "defaultLocations"
                );
    }

    public static List<AbilityResponseDto> buildAbilities(int numAbilities) {
        List<AbilityResponseDto> abilities = new ArrayList<>(numAbilities);

        for (int i = 0; i < numAbilities; i++) {
            abilities.add(new AbilityResponseDto(
                    new ItemDto("hab-" + (i + 1), "http://example.com/ability/" + (i + 1)),
                    false,
                    1 + new Random().nextInt(10)
            ));
        }

        return abilities;
    }
    public static List<HeldItemDto> buildHeldItems(int numHeldItems){
        List<HeldItemDto> items = new ArrayList<>(numHeldItems);

        for (int i = 0; i < numHeldItems; i++) {
            items.add(new HeldItemDto(
                    new ItemDto("item-" + (i + 1), "http://example.com/ability/" + (i + 1)),
                    List.of(new VersionDetailDto(
                            1 + new Random().nextInt(10),
                            new ItemDto("version-" + (i + 1), "http://example.com/version/" + (i + 1))
                    ))
            ));
        }

        return items;
    }
}
