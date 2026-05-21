package de.josan.backrooms.entity;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.entity.custom.DeerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<DeerEntity> DEER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Backrooms.MOD_ID, "deer"),
            EntityType.Builder.create(DeerEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 1f).build());


    public static void registerModEntities() {
        Backrooms.LOGGER.info("Register Entities for: " + Backrooms.MOD_ID);
    }
}
