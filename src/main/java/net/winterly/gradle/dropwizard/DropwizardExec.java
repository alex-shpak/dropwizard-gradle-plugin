package net.winterly.gradle.dropwizard;

import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.ApplicationPluginConvention;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.JavaExec;

import java.util.Optional;

public class DropwizardExec extends JavaExec {

    private final Dropwizard dropwizard;

    public DropwizardExec() {
        dropwizard = getProject().getExtensions().getByType(Dropwizard.class);

        setGroup("dropwizard");
        dependsOn("classes");

        setClasspath(getRuntimeClasspath());
        setMain(getMainClass());
    }

    private FileCollection getRuntimeClasspath() {
        String name = Optional.ofNullable(dropwizard.getClasspath()).orElse("main");

        return getPlugin(JavaPluginConvention.class)
                .getSourceSets()
                .getByName(name)
                .getRuntimeClasspath();
    }

    private String getMainClass() {
        if (dropwizard.getMain() != null) {
            return dropwizard.getMain();
        }

        ApplicationPluginConvention application = getPlugin(ApplicationPluginConvention.class);
        if (application != null) {
            return application.getMainClassName();
        }

        return null;
    }

    private <T> T getPlugin(Class<T> type) {
        return getProject().getConvention().findPlugin(type);
    }
}
