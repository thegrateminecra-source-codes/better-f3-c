# Better F3 C

Makes F3+C copy a useful coordinate string instead of the vanilla one.

Four modes, cycle through them in game:

- Basic: `123 64 456`
- Advanced: `123 64 456 -90.00 0.00`
- TP Command: `/tp @s 123 64 456`
- Ultimate: `/execute in minecraft:overworld run tp @s 123.00 64.00 456.00 -90.00 0.00`

## Setup

Jar into your mods folder, built for Fabric and Forge. If you have
[Mod Menu](https://modrinth.com/mod/modmenu) (Fabric) there's a config button
on this mod's entry; on Forge it shows up on the mod's entry in the vanilla
mods list. No menu access? Edit `config/betterf3c.json` by hand instead —
`copyMode` is one of BASIC, ADVANCED, TP_COMMAND, ULTIMATE.

Keys:

- F3+C copies in the mode you've picked

Fabric builds land for every stable version from 1.21.1 up. Forge lands
too, except 1.21.2 (Forge skipped it) and 26.1, whose forge build refuses
to even boot.
The jar only loads where it was built for, so pick the one matching your
game - fabric jars are `betterf3c-<version>.jar`, forge jars are
`betterf3c-forge-<version>.jar`.

MIT license.
