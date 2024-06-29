pipeline {
    agent any

     stages {
        stage('Test') {
            steps {
                 env.testvariable = "${WORKSPACE}\SDET_Advanced_AssignmentsBDD\"
                dir('${env.testvariable') {
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
