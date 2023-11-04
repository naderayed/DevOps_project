pipeline {
    agent any

    stages {
        stage("Git Pulling Stage") {
            steps {
                sh 'git checkout nader'
                sh 'git pull origin nader'
            }
        }

        stage("Cleaning Stage") {
            steps {
              
                    sh "mvn clean"
      
            }
        }
               stage("Testing Stage") {
            steps {
           
                    sh "mvn test"
       
            }
        }
         stage('SonarQube Scanning') {
            steps {
           
                    withSonarQubeEnv('sonarQubeServer') {
                        sh 'mvn sonar:sonar'
           
                 }
            }
        }
        
                 stage("Building Stage") {
            steps {
             
                    sh "mvn package"
          
            }
        }
             stage("Nexus Deploy Stage") {
            steps {
    
                    sh 'mvn deploy -DskipTests'
          
            }
        }
    }
}
