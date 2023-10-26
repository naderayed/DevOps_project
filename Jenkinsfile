pipeline {

      agent any

      stages {
         stage("Git") {
      
             steps{

            def workspacePath = pwd() // Get the current workspace path
            if (fileExists("${workspacePath}/DevOps_project")) {
            echo 'Workspace already exists. Skipping Git clone.'
            } else {
            sh 'git clone https://github.com/naderayed/DevOps_project.git' 
            }
             sh 'git checkout main'
             sh 'git origin pull main'    
            }
          }

  stage("MVN Clean") {
      
             steps{
             sh 'mvn clean'
            }
          }

  stage("MVN Compile") {
      
             steps{
             sh 'mvn compile'
            }
          }
      }
}  
