def maven_build_test() {
    stage('build'){
        sh 'mvn clean install -e'
    }
}

return this;
