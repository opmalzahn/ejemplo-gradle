def mvn_build() {
    stage('Build'){
        sh 'mvn clean install -e'
    }
}

def mvn_sonar() {
    stage('Sonar'){
        withSonarQubeEnv('SonarTest') {
            sh './mvnw clean package sonar:sonar'
        }
    }
}
def mvn_test() {
    stage('Test'){
        sh './mvnw clean test -e'
    }
}
def mvn_package() {
    stage('Package'){
        sh './mvnw clean package -e'
    }
}
def mvn_run() {
    stage('Run'){
        sh 'nohup bash ./mvnw spring-boot:run &'
    }
}

return this;
