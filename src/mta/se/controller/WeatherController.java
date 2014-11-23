package mta.se.controller;

import mta.se.exceptions.InputException;
import mta.se.interfaces.IController;
import mta.se.interfaces.IView;
import mta.se.model.WeatherModel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Cristian on 11/20/2014.
 */
public class WeatherController implements IController {

    private WeatherModel nWModel;
    private List<IView> nWViews;

    public WeatherController() {
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(REFRESH)) {
            // Reset the model to its default state
            if (nWModel != null) {
                try {
                    nWModel.setValue(randInt(-10, 40), randInt(3, 70));
                } catch (Exception e) {
                    notifyViews(true, e.getMessage());
                }
            }
        }
    }

    public void addView(IView view) {
        if (nWViews == null) {
            nWViews = new ArrayList<IView>();
        }

        nWViews.add(view);
    }

    public void addModel(WeatherModel model) {
        nWModel = model;
    }

    private void notifyViews(boolean isError, String message) {
        if (nWViews != null && !nWViews.isEmpty()) {
            for (IView view : nWViews) {
                view.onMessage(isError, message);
            }
        }
    }
}
