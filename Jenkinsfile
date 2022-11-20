pipeline {
    agent any

    tools {
        gradle 'Gradle'
        maven 'Maven-3.8.6'
    }
    stages {
        stage('build&test') {
            steps {
                sh 'gradle build'
            }
        }
        stage('sonar') {
            steps {
                withSonarQubeEnv(credentialsId: 'tokenTest') {
                    sh '-Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                }
            }
        }
        stage('run&test') {
            steps {
                sh 'gradle bootRun &'
                sh 'curl -X GET \'http://localhost:8081/rest/mscovid/test?msg=testing\''
            }
        }
        stage('nexus') {
            steps {
                nexusPublisher nexusInstanceId: 'NexusServer', nexusRepositoryId: 'devops-usach-nexus', 
			packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]],
		        mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.0']]]
            }
        }
    }
}