package mta.se.view;

import mta.se.interfaces.IController;
import mta.se.interfaces.IModelListeners;
import mta.se.interfaces.IView;
import mta.se.model.WeatherModel;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;



/**
 * Created by Cristian on 11/20/2014.
 */
public class WeatherView extends JFrame implements IModelListeners,IView {
    private static final long serialVersionUID = -5758555454500685115L;

    private WeatherModel nModel;

    // View Components
    private JTextField mWeatherTemp = new JTextField(6);
    private JLabel mWheaterLabel=new JLabel();
    private JTextField mWeatherWind = new JTextField(6);
    private JButton mRefreshBtn = new JButton("Refresh");

    // Layout values
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public WeatherView() {
        mWeatherTemp.setEditable(false);
        mWeatherWind.setEditable(false);
        mRefreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mWeatherTemp.setText(Integer.toString(randInt(-10, 40))+"℃");
                mWeatherWind.setText(Integer.toString(randInt(3, 70))+"km/h");
                /*mRefreshBtn.setActionCommand(IController.REFRESH);
                mRefreshBtn.setText("Refresh");*/

            }
        });

        // Layout the components.
        JPanel content = new JPanel();
        //content.add(new JLabel(new ImageIcon("D:\\poze\\Distractii\\DSC_0079.jpg")));
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Temperature"));
        content.add(mWeatherTemp);
        content.add(new JLabel("Wind"));
        content.add(mWeatherWind);
        content.add(mRefreshBtn);

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather Forecast!");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void addModel(WeatherModel model) {
        nModel = model;
        mWeatherTemp.setText(Integer.toString(model.getTemperature()));
        mWeatherWind.setText(Integer.toString(model.getWind()));
        mWheaterLabel.setText(model.getDescription());
    }

    public void addController(IController controller) {
        mRefreshBtn.setActionCommand(IController.REFRESH);
        mRefreshBtn.addActionListener(controller);
    }

    @Override
    public void onMessage(boolean isError, String message) {

    }
    public void onUpdate() {
        mWeatherWind.setText(Integer.toString(nModel.getWind()));
        mWeatherTemp.setText(Integer.toString(nModel.getTemperature()));
        mWheaterLabel.setText(nModel.getDescription());
    }
}
