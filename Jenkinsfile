pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }

        stage("MVN Clean") {
            steps {
              
                    sh "mvn clean"
               
            }
        }

        stage("MVN Compile") {
            steps {
               
                    sh "mvn clean compile"
                
            }
        }

        stage("Run Tests") {
            steps {
               
                    sh "mvn test"
                
            }
        }

        stage('Analyse de code avec SonarQube') {
            steps {
                    withSonarQubeEnv('SonarQubeServer') {
                        sh 'mvn sonar:sonar'
                    }
                
            }
        }
    }
}
