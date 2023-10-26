pipeline {

      agent any

      stages {
         stage("Git") {
      
             steps{
              sh 'git clone https://github.com/naderayed/DevOps_project.git' 
              sh 'git checkout main'
              sh 'git origin pull'      
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
