package net.hneu.ei.weatherapp.model.api;

/**
 * Interface to give abstraction of repo access.
 * Created on 1/14/16.
 *
 * @author Evgenii Kanivets
 */
public interface WeatherRepoProvider {
    WeatherRepo getRepo();
}