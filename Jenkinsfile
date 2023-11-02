pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout aymen_pipeline'
                sh 'git pull origin aymen_pipeline'
            }
        }

        stage("Build") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn package"
                }
            }
        }

        stage('Analyse de code avec SonarQube') {
            steps {
                withMaven(maven: 'mvn') {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                        }
                    }
                }
            }
        }
    }
}
