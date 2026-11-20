# The good parts of [Mycelium](https://github.com/TesseractLHY/Mycelium)

Podzol is a Minecraft 1.7.10 Forge mod that reimplements a few of Mycelium's tweaks with improvements and with plain Forge events and
standard mixins, without Mycelium's custom hook system and Lua scripting.

## Features

- **Creative noclip**: fly through blocks in creative mode.
  - Screen overlays (block, water, fire) are hidden while noclipping.
  - Blocks can be placed inside you while noclipping.
  - Fixes vanilla rendering the block around your camera inside-out, which otherwise leaves "holes" in the world
    while you move through terrain.
- **Long-range pick block**: in creative, middle-click picks blocks up to 256 blocks away (configurable up to 1024)
  when nothing is in normal reach.
- **Fast language switching**: changing language only reloads language files instead of every resource, so it's
  near-instant even in large modpacks.

## Configuration

Everything can be toggled in `config/podzol.cfg`, or in game via **Mods → Podzol → Config**. Changes apply
immediately.

| Option                  | Default | Description                                                                 |
|-------------------------|---------|-----------------------------------------------------------------------------|
| `creativeNoclip`        | `true`  | Noclip while flying in creative.                                            |
| `noclipHideOverlays`    | `true`  | Hide block, water and fire overlays while noclipping.                       |
| `noclipPlaceInside`     | `true`  | Allow placing blocks inside noclipping players.                             |
| `noclipHideInsideFaces` | `true`  | Don't render the block around the camera inside-out while noclipping.       |
| `pickBlockRange`        | `256`   | Creative pick block range when nothing is in normal reach (0–1024, 0 = vanilla). |
| `fastLang`              | `true`  | Only reload language files when switching language.                         |

The `noclip*` options apply whenever a player is noclipping, including noclip enabled by other mods.

On servers, install Podzol on both the client and the server, and keep `creativeNoclip` and `noclipPlaceInside`
the same on both sides. Otherwise the server will rubber-band noclipping players or reject their placements.

## Requirements

- Minecraft 1.7.10 with Forge 10.13.4.1614
- [UniMixins](https://github.com/LegacyModdingMC/UniMixins)

## Building

```sh
./gradlew build
```

The mod jar is written to `build/libs/`. Use `./gradlew runClient` or `./gradlew runServer` to start a development
instance.

## License

[LGPL-2.1](LICENSE), the same license as Mycelium.
