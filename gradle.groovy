    def gradle_build_test() {
        stage(){
            sh 'gradle build'
        }
    }

    return this;