package com.nashss.se.rentalcarservice.dependency;

import com.nashss.se.rentalcarservice.activity.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    SearchCarsActivity provideCarsActivity();

    GetCarActivity provideGetCarActivity();
}
