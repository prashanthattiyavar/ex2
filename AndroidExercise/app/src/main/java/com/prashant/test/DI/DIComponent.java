package com.prashant.test.DI;

import com.prashant.test.UI.activity.ListFragment;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = DIModule.class)
public interface DIComponent {
    void inject(ListFragment content);

}
