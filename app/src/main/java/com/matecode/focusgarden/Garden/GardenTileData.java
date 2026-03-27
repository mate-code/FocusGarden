package com.matecode.focusgarden.Garden;

import android.graphics.Point;

public class GardenTileData {
    private int position;
    private GardenTileStatusEnum status;

    public GardenTileData(int position, GardenTileStatusEnum status) {
        this.position = position;
        this.status = status;
    }

    public int getPosition() {
        return this.position;
    }

    public void setStatus(GardenTileStatusEnum status) {
        this.status = status;
    }

    public GardenTileStatusEnum getStatus() {
        return this.status;
    }
}
