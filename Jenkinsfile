pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }

        stage('Analyse de code avec SonarQube') {
            steps {
                    withSonarQubeEnv('SonarqubeServer') {
                        sh 'mvn sonar:sonar'
                    }
            }
        }
    }
}
