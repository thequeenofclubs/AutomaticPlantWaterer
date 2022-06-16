package AutoPlantWater;

import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class AutoPlantWater {
    public static void main(String[] args) throws IOException, InterruptedException{
        var myGroveBoard = new FirmataDevice("/dev/cu.usbserial-0001");
        myGroveBoard.start();
        System.out.println("Board Started");
        myGroveBoard.ensureInitializationIsDone();

        I2CDevice i2cObject = myGroveBoard.getI2CDevice((byte) 0x3C);
        SSD1306 theOledObject = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);

        theOledObject.init();
        var moistureSensor = myGroveBoard.getPin(15);
        var pumpPin = myGroveBoard.getPin(7);
        var button = myGroveBoard.getPin(6);
        moistureSensor.setMode(Pin.Mode.ANALOG);
        button.setMode(Pin.Mode.INPUT);



        var mainTimer = new Timer();

        mainTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                theOledObject.clear();
                double moistureDecimal = (((moistureSensor.getValue() - 520.00) / (1023 - 520) * 100));
                int moisturePercentage = (100) - ((int) (moistureDecimal));
                String moistureValue = "Moisture: " + (String.valueOf(moisturePercentage)) + "%";


                System.out.println(moistureSensor.getValue());
                var overrideState = button.getValue();
                if (moistureSensor.getValue() > 725 || overrideState==1) {
                    try {
                        theOledObject.getCanvas().drawString(0, 0, moistureValue);
                        if (overrideState==1){
                            System.out.println("Manual Button Override is Active!");
                            theOledObject.getCanvas().drawString(0, 10, "Override: True");

                        }

                        theOledObject.getCanvas().drawString(0, 20, "Pumping: True");
                        theOledObject.display();
                        pumpPin.setValue(1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        pumpPin.setValue(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    theOledObject.getCanvas().drawString(0, 0, moistureValue);
                    theOledObject.getCanvas().drawString(0, 10, "Override: False");
                    theOledObject.getCanvas().drawString(0, 20, "Pumping: False");
                    theOledObject.display();
                }
            }
        }, 0, 380);


    }
}
