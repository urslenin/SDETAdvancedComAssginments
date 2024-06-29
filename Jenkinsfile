
pipeline {
    agent any

     stages {
        stage('BDD Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssignmentsBDD"){
                 bat "mvn -D clean test"
                }
              } 
        }
        stage('RestAssured Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssignmentsRestAssured"){
                 bat "mvn -D clean test"
                }
              }
        }
        stage('TestNG Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssginmentsTestNG"){
                 bat "mvn -D clean test"
                }
              } 
            post {
              success{
                 echo "Test Success"
                }
            }
        }
    }
}
