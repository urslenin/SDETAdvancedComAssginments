pipeline {
    agent any

     stages {
        stage('Test') {
            steps {script{
                sh 'pwd > workspace'
                workspace = readFile('workspace').trim()
            
                dir('workspace') {
                    bat "mvn -D clean test"
                }
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
