def gradle_build_test() {
    stage('build'){
        sh 'gradle build'
    }
}

return this;