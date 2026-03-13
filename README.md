# Player List Toggle

Tiny client-side Fabric mod for Minecraft 1.21.11.

It changes the player list key from hold-to-show into toggle-to-show.

## Behavior

- Press Tab once: player list opens
- Press Tab again: player list closes
- If the config is disabled, vanilla hold behavior is restored

## Config

The mod creates:

`config/playerlist-toggle.json`

Set:

```json
{
  "enabled": true
}
```

Change `enabled` to `false` to disable the toggle behavior.

## Notes

This project intentionally avoids extra config libraries and Mod Menu so it stays small.
