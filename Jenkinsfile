pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout aymen_pipeline'
                sh 'git pull origin aymen_pipeline'
            }
        }

        stage("Test maven") {
            steps {
                sh 'mvn test'
            }
        }
         stage("SonarQube analysis") {
            steps {
              withSonarQubeEnv('sonarQubeServer') {
                sh 'mvn sonar:sonar'
              }
            }
       
        }

        stage("Build artifact") {
            steps {
                sh 'mvn package'
            }
        }
    }
}

