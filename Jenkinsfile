pipeline {
    agent any
    tools {
        maven 'Maven-3.8.6'
        gradle 'Gradle'
    }
	parameters{
		choice choices: ['maven','gradle'], name: 'Build_Tool'
	    booleanParam 'PushNexus'
	}
    stages {
        stage('Maven') {
            when {
				expression{
					params.Build_Tool == 'maven'
				}
			}
			steps {
                script {
                    def script_mvn = load 'maven.groovy'
                    script_mvn.maven_build_test()                
                }
            }
        }
		stage('Gradle') {
            when {
				expression{
					params.Build_Tool == 'gradle'
				}
			}
			steps {
                script {
                    def script_gradle = load 'gradle.groovy' 
                    script_gradle.compile()
                    script_gradle.run()
                    script_gradle.test()
                }
            }
        }
        stage('Push to Nexus') {
           when {
				expression{
					params.PushNexus
				}
			}
		   steps {
                echo 'Nexus'
                nexusPublisher nexusInstanceId: 'Nexus-Repository', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]           
            }
        } 
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }
}