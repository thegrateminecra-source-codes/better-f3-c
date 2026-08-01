# Better F3 C

Replaces the default F3+C debug copy output with configurable coordinate formatting.

## Features

When you press F3+C (or your rebound key), instead of the default Minecraft debug copy, you get clean, configurable output:

| Mode | Output Example |
|------|---------------|
| **Basic** | `123 64 456` |
| **Advanced** | `123 64 456 -90.00 0.00` |
| **TP Command** | `/tp @s 123 64 456` |
| **Ultimate** | `/execute in minecraft:overworld run tp @s 123.00 64.00 456.00 -90.00 0.00` |

## Configuration

Double-click the mod in Mod Menu to open the config screen (requires [Mod Menu](https://modrinth.com/mod/modmenu)). Cycle through modes with the button — your selection is saved automatically to `config/betterf3c.json`.

You can also edit `config/betterf3c.json` directly without Mod Menu installed.

## Installation

1. Install [Fabric Loader](https://fabricmc.net/) for a supported version
2. Install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop the JAR into your `mods` folder

### Optional

- [Mod Menu](https://modrinth.com/mod/modmenu) — adds a config screen accessible from the mods list

## Compatibility

- **Minecraft**: 1.21 - 26.2
- **Loader**:
- **Mod Menu**:

## License

MIT
