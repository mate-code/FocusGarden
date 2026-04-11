package com.matecode.focusgarden.Garden;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GardenViewModel extends ViewModel {
    private List<GardenTileData> gardenMap;

    private final MutableLiveData<Integer> positionToPlant = new MutableLiveData<Integer>();

    private GardenTileStatusEnum plantStatus;
    private final int rows;
    private final int cols;
    private final int size;

    public GardenViewModel(int rows, int cols) {
        positionToPlant.setValue(0);
        plantStatus = GardenTileStatusEnum.EMPTY;
        
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;

        gardenMap = new ArrayList<GardenTileData>();

        // START: for test purpose
        Random random = new Random();
        GardenTileStatusEnum[] values = GardenTileStatusEnum.values();
        // STOP

        for (int i = 0; i < size; i++) {
            //GardenTileStatusEnum randomTile = values[random.nextInt(values.length)];
            gardenMap.add(new GardenTileData(i, GardenTileStatusEnum.EMPTY));
        }
    }

    public GardenTileStatusEnum getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(GardenTileStatusEnum plantStatus) {
        this.plantStatus = plantStatus;
    }

    public MutableLiveData<Integer> getPositionToPlant() {
        return positionToPlant;
    }

    public void setPositionToPlant(int position) {
        this.positionToPlant.setValue(position);
    }


    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getSize() {
        return this.size;
    }

    public List<GardenTileData> getGardenMap() {
        return this.gardenMap;
    }
}
