pipeline {

      agent any

      stages {
         stage("Git") {
      
             steps{
             sh 'git checkout main'
             sh 'git pull origin main'    
            }
          }

  stage("MVN Clean") {
      
             steps{
             withMaven(maven: 'mvn') {
            sh "mvn clean "
        }
            }
          }

  stage("MVN Compile") {
      
             steps{
              withMaven(maven: 'mvn') {
            sh "mvn clean compile"
        }
            }
          }
      }
}  
