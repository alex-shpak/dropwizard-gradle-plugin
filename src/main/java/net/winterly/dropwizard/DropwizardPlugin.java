package net.winterly.dropwizard;

import org.gradle.api.NonNullApi;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

@NonNullApi
public class DropwizardPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        Dropwizard dropwizard = new Dropwizard(project);
        project.getExtensions().add("dropwizard", dropwizard);

        project.afterEvaluate(it -> {
            dropwizard.command("check").args("check", dropwizard.configuration);
            dropwizard.command("migrate").args("db", "migrate", dropwizard.configuration);
            dropwizard.command("dropAll").args("db", "drop-all", "--confirm-delete-everything", dropwizard.configuration);
        });
    }
}
