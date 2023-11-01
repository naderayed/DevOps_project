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
          
            sh "mvn clean "
      
            }
          }

  stage("MVN Compile") {
      
             steps{
             
            sh "mvn clean compile"
      
            }
          }

}  
