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
                withMaven(maven: 'mvn') {
                    sh "mvn clean"
                }
            }
        }

        stage("MVN Compile") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn clean compile"
                }
            }
        }

        stage("Run Tests") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn test"
                }
            }
        }
        
        stage('Analyse de code avec SonarQube') {
            steps {
                // Change the working directory to the 'kaddem' subdirectory
                
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                
            }
        }
    }
}
