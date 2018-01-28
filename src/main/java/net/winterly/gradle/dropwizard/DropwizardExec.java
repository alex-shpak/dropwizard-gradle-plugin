package net.winterly.gradle.dropwizard;

import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.ApplicationPluginConvention;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.JavaExec;

public class DropwizardExec extends JavaExec {

    private final Dropwizard dropwizard = getProject().getExtensions().getByType(Dropwizard.class);

    public DropwizardExec() {
        setGroup("dropwizard");
        dependsOn("classes");

        setClasspath(getRuntimeClasspath());
        setMain(getMainClass());
    }

    private FileCollection getRuntimeClasspath() {
        return getPlugin(JavaPluginConvention.class)
                .getSourceSets()
                .getByName(dropwizard.classpath)
                .getRuntimeClasspath();
    }

    private String getMainClass() {
        if (dropwizard.main != null) {
            return dropwizard.main;
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
