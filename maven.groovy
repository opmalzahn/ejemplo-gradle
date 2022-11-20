def maven_build_test() {
    stage('Build'){
        sh 'mvn clean install -e'
    }
}

dev mvn_sonar() {
    stage('Sonar'){
        withSonarQubeEnv('SonarTest') {
            sh './mvnw clean package sonar:sonar'
        }
    }
}
dev mvn_test() {
    stage('Test'){
        sh './mvnw clean test -e'
    }
}
dev mvn_package() {
    stage('Package'){
        sh './mvnw clean package -e'
    }
}
dev mvn_run() {
    stage('Run'){
        sh 'nohup bash ./mvnw spring-boot:run &'
    }
}

return this;
