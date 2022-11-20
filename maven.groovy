def maven_build_test() {
    stage(){
        sh 'mvn clean install -e'
    }
}

return this;
