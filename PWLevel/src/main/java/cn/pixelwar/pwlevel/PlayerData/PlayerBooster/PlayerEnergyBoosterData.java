package cn.pixelwar.pwlevel.PlayerData.PlayerBooster;

public class PlayerEnergyBoosterData {
    private int EnergyBoosterTime;
    private double EnergyMultiple;

    public int getEnergyBoosterTime() {
        return EnergyBoosterTime;
    }


    public double getEnergyMultiple() {
        return EnergyMultiple;
    }


    public void setEnergyBoosterTime(int energyBoosterTime) {
        EnergyBoosterTime = energyBoosterTime;
    }



    public void setEnergyMultiple(double energyMultiple) {
        EnergyMultiple = energyMultiple;
    }


    public PlayerEnergyBoosterData(int energyBoosterTime, double energyMultiple) {
        this.EnergyBoosterTime = energyBoosterTime;
        this.EnergyMultiple = energyMultiple;
    }
}
