
pipeline {
    agent any

     stages {
        stage('BDD Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssignmentsBDD"){
                 bat "mvn -D clean test"
                }
              } 
            stage('RestAssured Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssignmentsRestAssured"){
                 bat "mvn -D clean test"
                }
              } 
            stage('TestNG Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssginmentsTestNG"){
                 bat "mvn -D clean test"
                }
              } 
            post {
                 
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                        echo "Test Success"
                }
            }
        }
    }
}
