def grdl_compile() {
    stage('grdl compile'){
        sh 'gradle build'
    }
}

def grdl_run() {
    stage('grdl run'){
        sh 'gradle build'
    }
}

def grdl_test() {
    stage('grdl test'){
        sh 'curl -X GET \'http://localhost:8081/rest/mscovid/test?msg=testing\''
    }
}

return this;