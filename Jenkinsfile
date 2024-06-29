
pipeline {
    agent any

     stages {
        
        stage('TestNG Execution') {
            steps {
                 dir("${env.WORKSPACE}/SDET_Advanced_AssignmentsTestNG"){
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
