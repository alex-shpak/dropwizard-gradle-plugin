package net.winterly.gradle.dropwizard;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

@NonNullApi
public class DropwizardPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions()
                .create("dropwizard", Dropwizard.class, project);
    }
}
