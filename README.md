# AlwaysDropPlus - Minecraft Forge 1.12.2 Mod

A feature-rich mod that enhances mob drops and experience mechanics in Minecraft 1.12.2.

## Features

### 1. Maximum Item Drops
- Mobs drop the maximum quantity of items they can naturally yield in a single kill
- Respects standard drop pools and rules
- Intelligently scales drop quantities

### 2. Looting Enchantment Scaling
- Implements a sophisticated scaling system for Looting enchantment
- Each Looting level provides appropriate quantity increases
- Seamlessly integrates with vanilla Looting mechanics
- Configurable multiplier for fine-tuning

### 3. Always Drop Experience
- Guarantees all mobs drop experience orbs upon death
- Works regardless of death cause (fall, fire, suffocation, etc.)
- Can be killed by any damage source and still drop XP
- Configurable XP multiplier

### 4. Full Configuration Support
- Comprehensive configuration file at `config/AlwaysDropPlus.cfg`
- Enable/disable individual features
- Fine-tune multipliers and scaling values
- Entity blacklist for mobs to exclude
- Debug mode for troubleshooting

## Configuration Options

The mod creates a configuration file at `config/AlwaysDropPlus.cfg` with the following options:

### General Settings
- `enableMaxDrops`: Enable/disable maximum item drops (default: true)
- `enableLootingScaling`: Enable/disable Looting enchantment scaling (default: true)
- `enableAlwaysXp`: Enable/disable guaranteed XP drops (default: true)
- `enableDebug`: Enable debug logging to console (default: false)

### Scaling Settings
- `lootingMultiplier`: Base multiplier for Looting enchantment per level (default: 1.0, range: 0.5-5.0)
- `xpMultiplier`: Multiplier for experience drops (default: 1.0, range: 0.1-10.0)

### Blacklist Settings
- `entityBlacklist`: List of entity types to exclude from mod mechanics (default: empty)

## Compatibility

- **Minecraft Version**: 1.12.2
- **Forge Version**: 14.23.5.2855 and later
- **No dependencies** - Works with vanilla Minecraft
- **Mod-agnostic** - Compatible with other mods

## Installation

1. Download the JAR file from the releases section
2. Place it in your `mods` folder
3. Launch Minecraft with Forge 1.12.2
4. (Optional) Customize `config/AlwaysDropPlus.cfg` to your preferences

## Building from Source

Requirements:
- Java 8 or higher
- Git

```bash
# Clone the repository
git clone https://github.com/TheBestTerrarian/EZXP.git
cd EZXP

# Build the mod
./gradlew build
```

The compiled JAR will be in `build/libs/AlwaysDropPlus-1.0.0.jar`

## Features in Detail

### Maximum Drops
When enabled, all mobs will drop increased quantities of their items, doubling the standard amount (capped at 64 per stack).

### Looting Scaling
With a Looting enchantment weapon:
- **Looting I**: 1x additional multiplier
- **Looting II**: 2x additional multiplier
- **Looting III**: 3x additional multiplier

The configurable multiplier allows you to adjust the intensity of these bonuses.

### Always Drop XP
Guarantees that all mobs drop their experience regardless of:
- How they die (fall damage, fire, suffocation, etc.)
- Whether killed by a player or mob
- Any other vanilla condition

The XP amount is configurable and can be multiplied by the `xpMultiplier` setting.

## Credits

Inspired by and adapted from:
- [mobs-always-drop](https://github.com/ivangeevo/mobs-always-drop) by ivangeevo
- [AlwaysXp](https://github.com/cputnam-a11y/AlwaysXp) by cputnam-a11y
- [Always Drop Loot](https://github.com/sargunv/always-drop-loot) by sargunv
- [Playerless Continued](https://github.com/oirehm/playerless-continued) by oirehm

## License

MIT License - Feel free to use, modify, and distribute

## Support

For issues or questions, please open an issue on the GitHub repository.
