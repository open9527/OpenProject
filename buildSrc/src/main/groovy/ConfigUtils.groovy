import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Project
import org.gradle.api.ProjectEvaluationListener
import org.gradle.api.ProjectState
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

class ConfigUtils {

    static addBuildListener(Gradle g) {
        g.addBuildListener(new BuildListener() {
            @Override
            void buildStarted(Gradle gradle) {
//                GLog.d("buildStarted")
            }

            @Override
            void settingsEvaluated(Settings settings) {
//                GLog.d("settingsEvaluated")
                includeModule(settings)

            }

            @Override
            void projectsLoaded(Gradle gradle) {
//                GLog.d("projectsLoaded")
                gradle.addProjectEvaluationListener(new ProjectEvaluationListener() {
                    @Override
                    void beforeEvaluate(Project project) {
//                        GLog.d("beforeEvaluate")
//                        GLog.d("name: " + project.name)
                        if (project.subprojects.isEmpty()) {// 定位到具体 project
                            if (project.name.endsWith("_app")) {
                                GLog.l(project.toString() + " applies buildApp.gradle")
                                project.apply {
                                    from "${project.rootDir.path}/buildApp.gradle"
                                }
                            } else {
                                GLog.l(project.toString() + " applies buildLib.gradle")
                                project.apply {
                                    from "${project.rootDir.path}/buildLib.gradle"
                                }
                            }
                        }

                    }

                    @Override
                    void afterEvaluate(Project project, ProjectState projectState) {
//                        GLog.d("afterEvaluate")
                    }
                }

                )
            }

            @Override
            void projectsEvaluated(Gradle gradle) {
//                GLog.d("projectsEvaluated")
            }

            @Override
            void buildFinished(BuildResult buildResult) {
//                GLog.d("buildFinished")
            }
        }

        )
    }

    static def modules = [
            ':lib:page',
            ':lib:dialog',
            ':lib:permission',
            ':lib:crash',
            ':lib:recycleview',
            ':lib:okhttp',
            ':lib:glide',
            ':lib:base',
            ':lib:common',
            ':lib:titlebar',

            ':feature:launcher:app',
            ':feature:mock',
//
            ':feature:permission:export',
            ':feature:permission:pkg',
            ':feature:permission:app',

            ':feature:okhttp:export',
            ':feature:okhttp:pkg',
            ':feature:okhttp:app',

            ':feature:wanandroid:export',
            ':feature:wanandroid:pkg',
            ':feature:wanandroid:app',

            ':feature:image:export',
            ':feature:image:pkg',
            ':feature:image:app',

            ':feature:annotation:export',
            ':feature:annotation:pkg',
            ':feature:annotation:app',

            ':feature:appmanager:export',
            ':feature:appmanager:pkg',
            ':feature:appmanager:app',

            ':feature:webview:export',
            ':feature:webview:pkg',
            ':feature:webview:app',

    ]

    private static includeModule(Settings settings) {
        modules.each {
            def unionPath = getUnionPath(it)
            settings.include unionPath
            settings.project(unionPath).projectDir = new File(it.substring(1).replace(":", "/"))
        }
    }


    private static def getUnionPath(String path) {
        return ":" + path.substring(1).replace(":", "_")
    }

}