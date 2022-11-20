def script_mvn
def script_gradle

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
        stage('Load Scripts') {   
            steps {
                script {
                    script_mvn = load 'maven.groovy'
                    script_gradle = load 'gradle.groovy' 
                }
            }         
        }
        stage('Build mvn') {
            when {
				expression{
					params.Build_Tool == 'maven'
				}
			}
			steps {
                script {
                    script_mvn.maven_build_test()                
                }
            }
        }
		stage('Build gradle') {
            when {
				expression{
					params.Build_Tool == 'gradle'
				}
			}
			steps {
                script {
                    gradle_script.gradle_build_test()
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