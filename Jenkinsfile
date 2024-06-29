pipeline {
    agent any
   
     stages {
        stage('Test') {
            steps {
                  
                    script {
    echo 'Entering Stage - Nexus Upload'
    def artefactPath = "${WORKSPACE}/SDET_Advanced_AssignmentsBDD/"   
    echo "printing the path ${artefactPath}"

                }
               sh  "${artefactPath}"
                     bat "mvn -D clean test"
                
               
            }
 
            post {
                 
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                        cucumber buildStatus: 'null', 
                        customCssFiles: '', 
                        customJsFiles: '', 
                        failedFeaturesNumber: -1, 
                        failedScenariosNumber: -1, 
                        failedStepsNumber: -1, 
                        fileIncludePattern: '**/*.json', 
                        pendingStepsNumber: -1, 
                        skippedStepsNumber: -1, 
                        sortingMethod: 'ALPHABETICAL', 
                        undefinedStepsNumber: -1
                }
            }
        }
    }
}
