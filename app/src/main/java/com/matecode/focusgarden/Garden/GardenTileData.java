package com.matecode.focusgarden.Garden;

public class GardenTileData {
    private GardenTileStatusEnum status;

    public GardenTileData(GardenTileStatusEnum status) {
        this.status = status;
    }

    public void setStatus(GardenTileStatusEnum status) {
        this.status = status;
    }

    public GardenTileStatusEnum getStatus() {
        return this.status;
    }
}
