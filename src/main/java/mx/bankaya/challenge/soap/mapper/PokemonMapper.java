package mx.bankaya.challenge.soap.mapper;

import com.bankaya.challenge.AbilityList;
import com.bankaya.challenge.HeldItemList;
import com.bankaya.challenge.Item;
import com.bankaya.challenge.VersionDetails;
import mx.bankaya.challenge.pokemon.dto.AbilityResponseDto;
import mx.bankaya.challenge.pokemon.dto.HeldItemDto;
import mx.bankaya.challenge.pokemon.dto.ItemDto;
import mx.bankaya.challenge.pokemon.dto.VersionDetailDto;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public AbilityList mapToAbilities(AbilityResponseDto abilityResponseDto) {
        var abilityList = new AbilityList();
        abilityList.setIsHidden(abilityResponseDto.isHidden());
        abilityList.setSlot(abilityResponseDto.slot());
        abilityList.setAbility(mapToItem(abilityResponseDto.ability()));
        return abilityList;
    }

    public HeldItemList mapToHeldItems(HeldItemDto heldItemDto) {
        var heldItemList = new HeldItemList();
        heldItemList.setItem(mapToItem(heldItemDto.item()));
        heldItemList.getVersionDetails().addAll(heldItemDto.versionDetails().stream()
                .map(this::mapToVersionDetails).toList());
        return heldItemList;
    }

    private Item mapToItem(ItemDto itemDto) {
        var item = new Item();
        item.setName(itemDto.name());
        item.setUrl(itemDto.url());
        return item;
    }

    private VersionDetails mapToVersionDetails(VersionDetailDto versionDetailDto) {
        var versionDetails = new VersionDetails();
        versionDetails.setVersion(mapToItem(versionDetailDto.version()));
        versionDetails.setRarity(versionDetailDto.rarity());
        return versionDetails;
    }
}
