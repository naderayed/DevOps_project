pipeline {
    agent any

      stages {

  stage("MVN Clean") {
      
             steps{
             sh 'mvn clean'

            }
        }


  stage("Compiling project") {
      
             steps{
             sh 'mvn compile'

            }
        }
  stage("runing unit tests") {
      
             steps{
             sh 'mvn test'
            }
        }
    }
}
