package mta.se.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mta.se.interfaces.IModelListeners;

/**
 * Created by Cristian on 11/20/2014.
 */
public class WeatherModel {
    static String imgPath;
    static String description;
    static int description_id;
    static int temperature;
    static int wind;
    private List<IModelListeners> mListeners;

    public WeatherModel() {
    }

    public void setValue(int temp, int winds) {
        temperature = temp;
        wind = winds;
        verifytemp();
        verifyWind();
        choosePicture();
        notifyListeners();
    }

    public static void verifytemp() {
        if (temperature < 0) {
            description = "Snowing";
            description_id = 0;
        } else if (temperature >= 0 && temperature < 15) {
            description = "Raining";
            description_id = 1;
        } else if (temperature >= 15 && temperature < 25) {
            description = "Cloudy";
            description_id = 2;
        } else {
            description = "Sunny";
            description_id = 3;
        }
        description += " and ";

    }

    public static void verifyWind() {
        if (wind < 10) {
            description += "Slow wind";
        } else if (wind >= 10 && wind < 25) {
            description += "Moderate wind";
        } else if (wind >= 25 && wind < 40) {
            description += "Fast wind";
        } else {
            description += "Very fast wind";
        }
    }
    public static void choosePicture()
    {
        switch (description_id)
        {
            case '0': imgPath = "C:\\Users\\Cristian\\Pictures\\snowing.jpg";
                break;
            case '1': imgPath = "C:\\Users\\Cristian\\Pictures\\raining.jpg";
                break;
            case '2': imgPath = "C:\\Users\\Cristian\\Pictures\\cloudy.jpg";
                break;
            case '3': imgPath = "C:\\Users\\Cristian\\Pictures\\sunny.jpg";
                break;
            default:break;
        }
    }

    public static String getImgPath() {
        return imgPath;
    }

    public static int getTemperature() {

        return temperature;
    }

    public static int getWind() {
        return wind;
    }

    public static String getDescription() {
        return description;
    }

    public void addModelListener(IModelListeners listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListeners>();
        }

        mListeners.add(listener);
    }

    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListeners listener : mListeners)
                listener.onUpdate();
        }
    }
}
